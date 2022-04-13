package com.westerndigital.keyinsight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoadDatabase implements CommandLineRunner {

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
            String issueNumber;
            allProjects = myJiraClient.getAllProject();
            for (BasicProject basicProject : allProjects) {
                JiraProject project = new JiraProject();
                String projectKey = basicProject.getKey();
                Project singleProject = myJiraClient.getProject(projectKey);
                String projectName = singleProject.getName();
                String productLeadName = singleProject.getLead().getName();
                User projectLead = myJiraClient.getUser(productLeadName);
                String projectLeadDisplayName = projectLead.getDisplayName();
                project.setName(projectName);
                project.setTeamLead(projectLeadDisplayName);
                project.setTeamLeadAvatarUrl(projectLead.getAvatarUri().toString());
                allIssues = myJiraClient.getAllIssues(projectName, issueCount);
                Long sizeOfAllIssues = StreamSupport.stream(allIssues.spliterator(), false).count();
                while (sizeOfAllIssues != 0 && sizeOfAllIssues <= 1000) {
                    for (Issue singleIssue : allIssues) {
                        issueNumber = singleIssue.getKey();
                        issueNumber = issueNumber.substring(issueNumber.indexOf('-') + 1);
                        System.out.println(issueNumber);
                        JiraIssue issue = new JiraIssue();
                        issue.setId(Integer.parseInt(issueNumber));
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
                    allIssues = myJiraClient.getAllIssues(projectName, issueCount);
                    sizeOfAllIssues = StreamSupport.stream(allIssues.spliterator(), false).count();
                }
                project.setNumIssues(issueCount);
                projectRepository.save(project);
            }

            String teamType = "CAD";

            String closed = "Closed";
            String wip = "In Progress";
            String notStarted = "Waiting";
            String reopened = "Reopened";

            String bug = "Bug";
            
            String criticalPriority = "Critical";

            String completed = "Completed";

            String cancelled = "Project cancelled";

          
            int totalTeamTypeJiraIssueCount = issueRepository.totalTeamTypeJiraIssueCount(teamType);
            Float totalTeamTypeJiraIssueStoryPoint = issueRepository.totalTeamTypeJiraIssueStoryPoint(teamType).orElse(0.0f);

            int totalTeamTypeJiraClosedIssueCount = issueRepository.totalTeamTypeJiraStatusIssueCount(teamType, closed);
            Float totalTeamTypeJiraClosedIssueStoryPoint = issueRepository.totalTeamTypeJiraStatusIssueStoryPoint(teamType, closed).orElse(0.0f);

            int totalTeamTypeJiraWIPIssueCount = issueRepository.totalTeamTypeJiraStatusIssueCount(teamType, wip);
            Float totalTeamTypeJiraWIPIssueStoryPoint = issueRepository.totalTeamTypeJiraStatusIssueStoryPoint(teamType, wip).orElse(0.0f);

            int totalTeamTypeJiraNotStartedIssueCount = issueRepository.totalTeamTypeJiraStatusIssueCount(teamType, notStarted);
            Float totalTeamTypeJiraNotStartedIssueStoryPoint = issueRepository.totalTeamTypeJiraStatusIssueStoryPoint(teamType, notStarted).orElse(0.0f);

            int totalTeamTypeJiraBugIssueCount = issueRepository.totalTeamTypeJiraSubTypeIssueCount(teamType, bug);

            int totalTeamTypeJiraReopenedIssueCount = issueRepository.totalTeamTypeJiraStatusIssueCount(teamType, reopened);

            int totalTeamTypeJiraCriticalIssueCount = issueRepository.totalTeamTypeJiraPriorityIssueCount(teamType, criticalPriority);

            int totalTeamTypeJiraCriticalCompletedIssueCount = issueRepository.totalTeamTypeJiraPriorityResolutionIssueCount(teamType, criticalPriority, completed);

            int totalTeamTypeJiraCriticalCancelledIssueCount = issueRepository.totalTeamTypeJiraPriorityResolutionIssueCount(teamType, criticalPriority, cancelled);

            int totalTeamTypeJiraCancelledIssueCount = issueRepository.totalTeamTypeJiraResolutionIssueCount(teamType, cancelled);
            
            System.out.println("finished");

            System.out.println("The query for totalTeamTypeJiraIssueCount returns: " + totalTeamTypeJiraIssueCount);
            System.out.println("The query for totalTeamTypeJiraIssueStoryPoint returns: " + totalTeamTypeJiraIssueStoryPoint);

            System.out.println("The query for totalTeamTypeJiraClosedIssueCount returns: " + totalTeamTypeJiraClosedIssueCount);
            System.out.println("The query for totalTeamTypeJiraClosedIssueStoryPoint returns: " + totalTeamTypeJiraClosedIssueStoryPoint);
            System.out.println("The percentage between totalTeamTypeJiraClosedIssueCount and totalTeamTypeJiraIssueStoryPoint is " + (totalTeamTypeJiraClosedIssueStoryPoint/totalTeamTypeJiraIssueStoryPoint) * 100.0f + "%");

            System.out.println("The query for totalTeamTypeJiraWIPIssueCount returns: " + totalTeamTypeJiraWIPIssueCount);
            System.out.println("The query for totalTeamTypeJiraWIPIssueStoryPoint returns: " + totalTeamTypeJiraWIPIssueStoryPoint);
            System.out.println("The percentage between totalTeamTypeJiraWIPIssueStoryPoint and totalTeamTypeJiraIssueStoryPoint is " + (totalTeamTypeJiraWIPIssueStoryPoint/totalTeamTypeJiraIssueStoryPoint) * 100.0f + "%");

            System.out.println("The query for totalTeamTypeJiraNotStartedIssueCount returns: " + totalTeamTypeJiraNotStartedIssueCount);
            System.out.println("The query for totalTeamTypeJiraNotStartedIssueStoryPoint returns: " + totalTeamTypeJiraNotStartedIssueStoryPoint);
            System.out.println("The percentage between totalTeamTypeJiraNotStartedIssueStoryPoint and totalTeamTypeJiraIssueStoryPoint is " + (totalTeamTypeJiraNotStartedIssueStoryPoint/totalTeamTypeJiraIssueStoryPoint) * 100.0f + "%");

            System.out.println("The query for totalTeamTypeJiraBugIssueCount returns: " + totalTeamTypeJiraBugIssueCount);
            System.out.println("The percentange between totalTeamTypeJiraBugIssueCount and totalTeamTypeJiraIssueCount is " + ((float)totalTeamTypeJiraBugIssueCount/totalTeamTypeJiraIssueCount) * 100.0f + "%");

            System.out.println("The query for totalTeamTypeJiraReopenedIssueCount returns: " + totalTeamTypeJiraReopenedIssueCount);
            System.out.println("The percentage between totalTeamTypeJiraReopenedIssueCount and totalTeamTypeJiraIssueCount is " + ((float)totalTeamTypeJiraReopenedIssueCount/totalTeamTypeJiraIssueCount) * 100 + "%");

            System.out.println("The query for totalTeamTypeJiraCriticalIssueCount returns: " + totalTeamTypeJiraCriticalIssueCount);
            System.out.println("The percentage between totalTeamTypeJiraCriticalIssueCount and totalTeamTypeJiraIssueCount is " + ((float)totalTeamTypeJiraCriticalIssueCount/totalTeamTypeJiraIssueCount) * 100 + "%");

            System.out.println("The query for totalTeamTypeJiraCriticalCompletedIssueCount returns: " + totalTeamTypeJiraCriticalCompletedIssueCount);
            System.out.println("The percentage between totalTeamTypeJiraCriticalCompletedIssueCount and totalTeamTypeJiraIssueCount is " + ((float)totalTeamTypeJiraCriticalCompletedIssueCount/totalTeamTypeJiraIssueCount) * 100 + "%");

            System.out.println("The query for totalTeamTypeJiraCriticalCancelledIssueCount returns: " + totalTeamTypeJiraCriticalCancelledIssueCount);
            System.out.println("The percentage between totalTeamTypeJiraCriticalCancelledIssueCount and totalTeamTypeJiraIssueCount is " + ((float)totalTeamTypeJiraCriticalCancelledIssueCount/totalTeamTypeJiraIssueCount) * 100 + "%");

            System.out.println("The query for totalTeamTypeJiraCancelledIssueCount returns: " + totalTeamTypeJiraCancelledIssueCount);
            System.out.println("The percentage between totalTeamTypeJiraCancelledIssueCount and totalTeamTypeJiraIssueCount is " + ((float)totalTeamTypeJiraCancelledIssueCount/totalTeamTypeJiraIssueCount) * 100 + "%");

        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
