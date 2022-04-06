package com.westerndigital.keyinsight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.westerndigital.keyinsight.JavaIssue.JavaIssueRepository;
import com.westerndigital.keyinsight.JavaIssue.JavaIssue;
import com.westerndigital.keyinsight.JavaProject.JavaProjectRepository;
import com.westerndigital.keyinsight.JavaProject.JavaProject;
import com.westerndigital.keyinsight.NotificationSettings.NotificationSettingsRepository;
import com.westerndigital.keyinsight.Server.ServerRepository;
import com.westerndigital.keyinsight.User.UserRepository;
import com.westerndigital.keyinsight.JiraRestJavaClient.JiraRestJavaClient;

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
    private UserRepository userRepository;

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private JavaProjectRepository projectRepository;

    @Autowired
    private JavaIssueRepository issueRepository;

    @Autowired
    private NotificationSettingsRepository notificationSettingsRepository;

    
    private JiraRestJavaClient myJiraClient;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        serverRepository.deleteAll();
        notificationSettingsRepository.deleteAll();
        projectRepository.deleteAll();
        issueRepository.deleteAll();

        try {
            Dotenv dotenv = Dotenv.load();
            myJiraClient = new JiraRestJavaClient(dotenv.get("JIRA_USERNAME"),
                    dotenv.get("JIRA_PASSWORD"), dotenv.get("JIRA_URL"));
            Iterable<BasicProject> allProjects = myJiraClient.getAllProject();
        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
        }
        try {
            HashMap<String, String> fieldValues = new HashMap<String, String>();
            int issueCount = 0;
            Iterable<BasicProject> allProjects = myJiraClient.getAllProject();
            for (BasicProject basicProject : allProjects) {
                JavaProject project = new JavaProject();
                String productUrl = basicProject.getKey();
                Project singleProject = myJiraClient.getProject(productUrl);
                String projectName = singleProject.getName();
                String productLeadName = singleProject.getLead().getName();
                User projectLead = myJiraClient.getUser(productLeadName);
                String projectLeadDisplayName = projectLead.getDisplayName();
                project.setName(projectName);
                project.setTeam_lead(projectLeadDisplayName);
                project.setTeam_lead_avatar_url(projectLead.getAvatarUri().toString());
                String issueNumber = "10";
                while (Long.parseLong(issueNumber) != 1) {
                    Iterable<Issue> allIssues = myJiraClient.getAllIssues(projectName, issueCount);
                    for (Issue singleIssue : allIssues) {
                        issueNumber = singleIssue.getKey();
                        issueNumber = issueNumber.substring(issueNumber.indexOf('-') + 1);
                        System.out.println(issueNumber);
                        JavaIssue issue = new JavaIssue();
                        issue.setId(Long.parseLong(issueNumber));
                        issue.setName(singleIssue.getKey());
                        issue.setProject_name(projectName);
                        issue.setTeam_type(singleIssue.getIssueType().getName());
                        issue.setStatus(singleIssue.getStatus().getName());

                        if (issueCount == 0) {
                            Iterable<IssueField> allIssueFields = singleIssue.getFields();
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

                        if (singleIssue.getField(fieldValues.get("Story Points")).getValue() == null) {
                            issue.setStory_point(null);
                        } else if (singleIssue.getField(fieldValues.get("Story Points")).getValue() != null) {
                            float storyPoint = Float
                                    .parseFloat(singleIssue.getField(fieldValues.get("Story Points")).getValue()
                                            .toString());
                            issue.setStory_point(storyPoint);
                        }

                        if (singleIssue.getField(fieldValues.get("Type")).getValue() == null) {
                            issue.setSub_type(null);
                        } else if (singleIssue.getField(fieldValues.get("Type")).getValue() != null) {
                            String secondaryTypeValueJsonString = singleIssue.getField(fieldValues.get("Type"))
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
                projectRepository.save(project);
            }
            System.out.println("finished");

        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
