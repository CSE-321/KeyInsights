package com.westerndigital.keyinsight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.westerndigital.keyinsight.JiraIssue.JiraIssueRepository;
import com.westerndigital.keyinsight.JiraProject.JiraProject;
import com.westerndigital.keyinsight.JiraProject.JiraProjectRepository;
import com.westerndigital.keyinsight.JiraIssue.JiraIssue;
import com.westerndigital.keyinsight.NotificationSettings.NotificationSettingsRepository;
import com.westerndigital.keyinsight.JiraRestJavaClient.JiraRestJavaClient;
import com.westerndigital.keyinsight.JiraServer.JiraServerRepository;
import com.westerndigital.keyinsight.JiraUser.JiraUserRepository;

import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UpdateDatabase {
    // https://stackoverflow.com/questions/42246301/spring-scheduled-task-does-not-start-on-application-startup
    // inject repositories
    @Autowired
    private JiraUserRepository userRepository;

    @Autowired
    private JiraServerRepository serverRepository;

    @Autowired
    private JiraProjectRepository projectRepository;

    @Autowired
    private JiraIssueRepository issueRepository;

    @Autowired
    private NotificationSettingsRepository notificationSettingsRepository;

    private JiraRestJavaClient myJiraClient;

    private Iterable<BasicProject> allProjects;

    private Iterable<Issue> allIssues;

    private Iterable<IssueField> allIssueFields;

    @Scheduled(initialDelay = 30 * 60 * 1000, fixedRate = 30 * 60 * 1000)
    public void scheduledWork() throws Exception {
        try {
            Dotenv dotenv = Dotenv.load();
            myJiraClient = new JiraRestJavaClient(dotenv.get("JIRA_USERNAME"),
                    dotenv.get("JIRA_PASSWORD"), dotenv.get("JIRA_URL"));
            User user = myJiraClient.getUser(dotenv.get("JIRA_USERNAME"));
        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
        }
        try {
            System.out.println("In Updating Database");
            HashMap<String, String> fieldValues = new HashMap<String, String>();
            allProjects = myJiraClient.getAllProject();
            for (BasicProject basicProject : allProjects) {
                String projectKey = basicProject.getKey();
                Project singleProject = myJiraClient.getProject(projectKey);
                String projectName = singleProject.getName();
                String productLeadName = singleProject.getLead().getName();
                User projectLead = myJiraClient.getUser(productLeadName);
                String projectLeadDisplayName = projectLead.getDisplayName();
                JiraProject project = projectRepository.findByName(projectName);
                project.setName(projectName);
                project.setTeamLead(projectLeadDisplayName);
                project.setTeamLeadAvatarUrl(projectLead.getAvatarUri().toString());
                int issueCount = 0;
                int newlyCreatedIssueCount = 0;
                allIssues = myJiraClient.getAllNewCreatedOrUpdatedLast30MinutesIssues(projectName, issueCount);
                Long sizeOfAllIssues = StreamSupport.stream(allIssues.spliterator(), false).count();
                while (sizeOfAllIssues != 0 && sizeOfAllIssues <= 1000) {
                    for (Issue singleIssue : allIssues) {
                        String issueNumber = singleIssue.getKey();
                        issueNumber = issueNumber.substring(issueNumber.indexOf('-') + 1);
                        System.out.println(issueNumber);
                        JiraIssue issue = issueRepository.findById(Integer.parseInt(issueNumber))
                                .orElse(new JiraIssue());
                        if (issue.getId() == null) {
                            newlyCreatedIssueCount += 1;
                        }
                        issue.setName(singleIssue.getKey());
                        issue.setProjectName(projectName);
                        issue.setTeamType(singleIssue.getIssueType().getName());
                        issue.setStatus(singleIssue.getStatus().getName());

                        if (issueCount == 0) {
                            allIssueFields = singleIssue.getFields();
                            for (IssueField issueField : allIssueFields) {
                                fieldValues.put(issueField.getName(), issueField.getId());
                            }

                        }

                        String createCreationDate = String.format("%d-%d-%d",
                                singleIssue.getCreationDate().getYear(),
                                singleIssue.getCreationDate().getMonthOfYear(),
                                singleIssue.getCreationDate().getDayOfMonth());

                        issue.setCreationDate(createCreationDate);

                        if (issue.getId() == 1) {
                            project.setCreatedDate(issue.getCreationDate());
                        }

                        String createCreationTime = String.format("%d:%d",
                                singleIssue.getCreationDate().getHourOfDay(),
                                singleIssue.getCreationDate().getMinuteOfHour());

                        issue.setCreationTime(createCreationTime);

                        String updatedDate = String.format("%d-%d-%d",
                                singleIssue.getUpdateDate().getYear(),
                                singleIssue.getUpdateDate().getMonthOfYear(),
                                singleIssue.getUpdateDate().getDayOfMonth());

                        issue.setUpdatedDate(updatedDate);

                        String updatedTime = String.format("%d:%d",
                                singleIssue.getUpdateDate().getHourOfDay(),
                                singleIssue.getUpdateDate().getMinuteOfHour());

                        issue.setUpdatedTime(updatedTime);

                        if (singleIssue.getDueDate() == null) {
                            issue.setDueDate(null);
                            issue.setDueTime(null);
                        } else if (singleIssue.getDueDate() != null) {
                            String dueDate = String.format("%d-%d-%d", singleIssue.getDueDate().getYear(),
                                    singleIssue.getDueDate().getMonthOfYear(),
                                    singleIssue.getDueDate().getDayOfMonth());

                            issue.setDueDate(dueDate);

                            String dueTime = String.format("%d:%d", singleIssue.getDueDate().getHourOfDay(),
                                    singleIssue.getDueDate().getMinuteOfHour());

                            issue.setDueTime(dueTime);
                        }

                        String storyPointField = fieldValues.get("Story Points");

                        if (singleIssue.getField(storyPointField).getValue() == null) {
                            issue.setStoryPoint(null);
                        } else if (singleIssue.getField(storyPointField).getValue() != null) {
                            float storyPoint = Float
                                    .parseFloat(singleIssue.getField(storyPointField).getValue()
                                            .toString());
                            issue.setStoryPoint(storyPoint);
                        }

                        String typeField = fieldValues.get("Type");

                        if (singleIssue.getField(typeField).getValue() == null) {
                            issue.setSubType(null);
                        } else if (singleIssue.getField(typeField).getValue() != null) {
                            String secondaryTypeValueJsonString = singleIssue.getField(typeField)
                                    .getValue()
                                    .toString();
                            ObjectMapper mapper = new ObjectMapper();
                            JsonNode node = mapper.readTree(secondaryTypeValueJsonString);
                            String secondaryTypeValue = node.get("value").asText();
                            // https://
                            // stackoverflow.com/questions/5245840/how-to-convert-jsonstring-to-jsonobject-in-java
                            issue.setSubType(secondaryTypeValue);
                        }

                        // found out that Cancelled Projects are under resolution so
                        // singleIssue.getResolution
                        if (singleIssue.getResolution() == null) {
                            issue.setResolution(null);
                        } else if (singleIssue.getResolution() != null) {
                            issue.setResolution(singleIssue.getResolution().getName());
                        }

                        if (singleIssue.getPriority() == null) {
                            issue.setPriority(null);
                        } else if (singleIssue.getPriority() != null) {
                            issue.setPriority(singleIssue.getPriority().getName());
                        }

                        if (singleIssue.getAssignee() == null) {
                            issue.setAssignee(null);
                            issue.setAssigneeAvatarUrl(null);
                        } else if (singleIssue.getAssignee() != null) {
                            issue.setAssignee(singleIssue.getAssignee().getDisplayName());
                            issue.setAssigneeAvatarUrl(singleIssue.getAssignee().getAvatarUri().toString());
                        }
                        issueRepository.save(issue);
                        issueCount += 1;
                    }
                    allIssues = myJiraClient.getAllNewCreatedOrUpdatedLast30MinutesIssues(projectName, issueCount);
                    sizeOfAllIssues = StreamSupport.stream(allIssues.spliterator(), false).count();
                }
                if (newlyCreatedIssueCount != 0) {
                    int currentIssueNumbers = project.getNumIssues();
                    project.setNumIssues(currentIssueNumbers + newlyCreatedIssueCount);
                }
                projectRepository.save(project);
                System.out.println("Had " + issueCount + " issues");
                System.out.println("Had " + newlyCreatedIssueCount + " newly Issues Created");
            }
            System.out.println("finished, please wait 30 minutes for the next update");

        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

}
