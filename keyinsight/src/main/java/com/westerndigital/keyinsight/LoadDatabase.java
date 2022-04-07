package com.westerndigital.keyinsight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.westerndigital.keyinsight.JiraIssue.JiraIssueRepository;
import com.westerndigital.keyinsight.JiraProject.JiraProject;
import com.westerndigital.keyinsight.JiraProject.JiraProjectRepository;
import com.westerndigital.keyinsight.JiraIssue.JiraIssue;
import com.westerndigital.keyinsight.NotificationSettings.NotificationSettingsRepository;
import com.westerndigital.keyinsight.Server.ServerRepository;
import com.westerndigital.keyinsight.JiraRestJavaClient.JiraRestJavaClient;
import com.westerndigital.keyinsight.JiraUser.JiraUserRepository;
import com.westerndigital.keyinsight.JiraUser.JiraUser;

import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoadDatabase implements CommandLineRunner {

    // inject repositories
    @Autowired
    private JiraUserRepository userRepository;

    @Autowired
    private ServerRepository serverRepository;

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

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        serverRepository.deleteAll();
        notificationSettingsRepository.deleteAll();
        projectRepository.deleteAll();
        issueRepository.deleteAll();
        notificationSettingsRepository.deleteAll();

        // get data from JIRA REST client and load them to the database

        try {
            Dotenv dotenv = Dotenv.load();
            myJiraClient = new JiraRestJavaClient(dotenv.get("JIRA_USERNAME"),
                    dotenv.get("JIRA_PASSWORD"), dotenv.get("JIRA_URL"));
            User user = myJiraClient.getUser(dotenv.get("JIRA_USERNAME"));
        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
        }
        try {
            HashMap<String, String> fieldValues = new HashMap<String, String>();
            int issueCount = 0;
            allProjects = myJiraClient.getAllProject();
            for (BasicProject basicProject : allProjects) {
                JiraProject project = new JiraProject();
                String productUrl = basicProject.getKey();
                Project singleProject = myJiraClient.getProject(productUrl);
                String projectName = singleProject.getName();
                String productLeadName = singleProject.getLead().getName();
                User projectLead = myJiraClient.getUser(productLeadName);
                String projectLeadDisplayName = projectLead.getDisplayName();
                project.setName(projectName);
                project.setTeam_lead(projectLeadDisplayName);
                project.setTeam_lead_avatar_url(projectLead.getAvatarUri().toString());
                project.setCategory(singleProject.getKey());
                String issueNumber = "10";
                while (Integer.parseInt(issueNumber) != 1) {
                    allIssues = myJiraClient.getAllIssues(projectName, issueCount);
                    for (Issue singleIssue : allIssues) {
                        issueNumber = singleIssue.getKey();
                        issueNumber = issueNumber.substring(issueNumber.indexOf('-') + 1);
                        System.out.println(issueNumber);
                        JiraIssue issue = new JiraIssue();
                        issue.setId(Integer.parseInt(issueNumber));
                        issue.setName(singleIssue.getKey());
                        issue.setProject_name(projectName);
                        issue.setTeam_type(singleIssue.getIssueType().getName());
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

                        issue.setCreation_date(createCreationDate);

                        String createCreationTime = String.format("%d:%d",
                                singleIssue.getCreationDate().getHourOfDay(),
                                singleIssue.getCreationDate().getMinuteOfHour());

                        issue.setCreation_time(createCreationTime);

                        String updatedDate = String.format("%d-%d-%d",
                                singleIssue.getUpdateDate().getYear(),
                                singleIssue.getUpdateDate().getMonthOfYear(),
                                singleIssue.getUpdateDate().getDayOfMonth());

                        issue.setUpdated_date(updatedDate);

                        String updatedTime = String.format("%d:%d",
                                singleIssue.getUpdateDate().getHourOfDay(),
                                singleIssue.getUpdateDate().getMinuteOfHour());

                        issue.setUpdated_time(updatedTime);

                        if (singleIssue.getDueDate() == null) {
                            issue.setDue_date(null);
                            issue.setDue_time(null);
                        } else if (singleIssue.getDueDate() != null) {
                            String dueDate = String.format("%d-%d-%d", singleIssue.getDueDate().getYear(),
                                    singleIssue.getDueDate().getMonthOfYear(),
                                    singleIssue.getDueDate().getDayOfMonth());

                            issue.setDue_date(dueDate);

                            String dueTime = String.format("%d:%d", singleIssue.getDueDate().getHourOfDay(),
                                    singleIssue.getDueDate().getMinuteOfHour());

                            issue.setDue_time(dueTime);
                        }

                        String storyPointField = fieldValues.get("Story Points");

                        if (singleIssue.getField(storyPointField).getValue() == null) {
                            issue.setStory_point(null);
                        } else if (singleIssue.getField(storyPointField).getValue() != null) {
                            float storyPoint = Float
                                    .parseFloat(singleIssue.getField(storyPointField).getValue()
                                            .toString());
                            issue.setStory_point(storyPoint);
                        }

                        String typeField = fieldValues.get("Type");

                        if (singleIssue.getField(typeField).getValue() == null) {
                            issue.setSub_type(null);
                        } else if (singleIssue.getField(typeField).getValue() != null) {
                            String secondaryTypeValueJsonString = singleIssue.getField(typeField)
                                    .getValue()
                                    .toString();
                            ObjectMapper mapper = new ObjectMapper();
                            JsonNode node = mapper.readTree(secondaryTypeValueJsonString);
                            String secondaryTypeValue = node.get("value").asText();
                            // https://
                            // stackoverflow.com/questions/5245840/how-to-convert-jsonstring-to-jsonobject-in-java
                            issue.setSub_type(secondaryTypeValue);
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
                            issue.setAssignee_avatar_url(null);
                        } else if (singleIssue.getAssignee() != null) {
                            issue.setAssignee(singleIssue.getAssignee().getDisplayName());
                            issue.setAssignee_avatar_url(singleIssue.getAssignee().getAvatarUri().toString());
                        }
                        issueRepository.save(issue);
                        issueCount += 1;
                    }
                }
                project.setNum_issues(issueCount);
                JiraIssue tmpissue = issueRepository.findById(1).get();
                project.setCreated_at(tmpissue.getCreation_date());
                projectRepository.save(project);
            }
            System.out.println("finished");

        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
