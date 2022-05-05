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

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.util.HashMap;
import java.util.stream.StreamSupport;

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
                Dotenv dotenv = Dotenv.load();
                // This block of code underneath just deletes every entry in the database during
                // startup
                // ------------------------------------------
                // userRepository.deleteAll();
                // serverRepository.deleteAll();
                // notificationSettingsRepository.deleteAll();
                // projectRepository.deleteAll();
                // issueRepository.deleteAll();
                // notificationSettingsRepository.deleteAll();
                // -------------------------------------------

                // This block of code attempts to use the username, password, and server url to
                // create a
                // Jira Rest Java Client(JRJC) that connects to the server
                // That JRJC allows us to interact with the Jira Server and grab the information
                // we need
                // The authenication doesn't happen until the client attempts grab some kind of
                // information
                // This whole try catch block is in case an exeception occurs
                // when extracting the information into the PostgreSQL database
                // -------------------------------------------------------------------------------
                try {
                        myJiraClient = new JiraRestJavaClient(dotenv.get("JIRA_USERNAME"),
                                        dotenv.get("JIRA_PASSWORD"), dotenv.get("JIRA_URL"));

                        allProjects = myJiraClient.getAllProject(); // grabs all the projects that are within the Jira
                                                                    // Server

                        // Iterating through all the projects that was grabbed in the line of code above
                        for (BasicProject basicProject : allProjects) {

                                // This block of code is just extracting information from the Project
                                // using getters from the class and JRJC
                                // ------------------------------------------------------------
                                String projectKey = basicProject.getKey();
                                Project singleProject = myJiraClient.getProject(projectKey);
                                String projectName = singleProject.getName();
                                projectName = projectName.trim();
                                String productLeadName = singleProject.getLead().getName();
                                User projectLead = myJiraClient.getUser(productLeadName);
                                String projectLeadDisplayName = projectLead.getDisplayName();
                                Long projectId = basicProject.getId();
                                String projectUniqueId = dotenv.get("JIRA_URL") + projectId;
                                OffsetDateTime projectCreationDateTime = null;
                                // ------------------------------------------------------------

                                // this line of code attempts to locate a project with that name
                                // or else just creates a new jiraproject object
                                // ----------------------------------------------------------------------------------------
                                // JiraProject project = projectRepository.findByName(projectName).orElse(new
                                // JiraProject());
                                JiraProject project = projectRepository.findById(projectUniqueId)
                                                .orElse(new JiraProject());
                                // ----------------------------------------------------------------------------------------

                                // This block of code is setting all the information from the code above
                                // and storing it in the Java Project Object
                                // -------------------------------------------------------------------
                                project.setId(projectUniqueId);
                                project.setName(projectName);
                                project.setTeamLead(projectLeadDisplayName);
                                project.setTeamLeadAvatarUrl(projectLead.getAvatarUri().toString());
                                projectRepository.save(project);
                                // -------------------------------------------------------------------

                                // This block of code is to get ready to go through all the issues that the Jira
                                // Project has
                                // -----------------------------------------------------------------------------------
                                int issueCount = 0;
                                HashMap<String, String> fieldValues = new HashMap<String, String>();
                                allIssues = myJiraClient.getAllIssues(projectName, issueCount); // if I have issueCount
                                                                                                // = 0, the first
                                                                                                // Issue that I grab is
                                                                                                // B8X4-10282 not
                                                                                                // B8X4-1
                                Long allIssuesCount = StreamSupport.stream(allIssues.spliterator(), false).count();
                                // -----------------------------------------------------------------------------------

                                // This while loop just runs as long as the allIssuesCount isn't 0
                                // -----------------------------------------------------------------------------
                                while (allIssuesCount > 0) {

                                        // As long as the allIssuesCount isn't 0, we have to iterate through all of the
                                        // issues stored in the Iterable
                                        for (Issue singleIssue : allIssues) {

                                                // This block of code is just grabbing the issueNumber after the dash
                                                // Example B8X4-10282,this block of code just grabs 10282
                                                // ------------------------------------------------------------------
                                                String issueNumber = singleIssue.getKey();
                                                issueNumber = issueNumber.substring(issueNumber.indexOf('-') + 1);
                                                System.out.println(issueNumber);
                                                // ------------------------------------------------------------------

                                                JiraIssue issue = issueRepository
                                                                .findById(Integer.parseInt(issueNumber))
                                                                .orElse(new JiraIssue()); // finds an issue in the
                                                                                          // database with that
                                                                                          // issueNumber
                                                                                          // if it doesn't exist, create
                                                                                          // a new Java Issue Object

                                                // Only needs to run on the FIRST iteration, we need to grab all the
                                                // fields that
                                                // an issue could potentially have and store it in the hashmap to use
                                                // later
                                                // ----------------------------------------------------------------
                                                if (issueCount == 0) {
                                                        allIssueFields = singleIssue.getFields();
                                                        for (IssueField issueField : allIssueFields) {
                                                                fieldValues.put(issueField.getName(),
                                                                                issueField.getId());
                                                        }
                                                }
                                                // ----------------------------------------------------------------

                                                // This block of code is just getting the
                                                // the creation date and time for each issue
                                                // Currently, these values are never null;
                                                // However, I am not sure if that is always the case
                                                // -----------------------------------------------------
                                                Instant creationInstant = Instant.ofEpochMilli(
                                                                singleIssue.getCreationDate().getMillis());
                                                OffsetDateTime creationDateTime = OffsetDateTime.ofInstant(
                                                                creationInstant,
                                                                ZoneId.of(singleIssue.getCreationDate().getZone()
                                                                                .getID()));
                                                projectCreationDateTime = creationDateTime;
                                                // -------------------------------------------------------

                                                // This block of code is just getting
                                                // the updated date and time for each issue
                                                // Currently, these values are never null;
                                                // However, I am not sure if that is always the case
                                                // -------------------------------------------------------
                                                Instant updatedInstant = Instant
                                                                .ofEpochMilli(singleIssue.getUpdateDate().getMillis());
                                                OffsetDateTime updatedDateTime = OffsetDateTime.ofInstant(
                                                                updatedInstant,
                                                                ZoneId.of(singleIssue.getUpdateDate().getZone()
                                                                                .getID()));
                                                // -------------------------------------------------------

                                                // This block of code is just formatting
                                                // the due date and time for each issue
                                                // Currently, some values are null;
                                                // so I need to use if statements to handle that
                                                // ---------------------------------------------------------------------------
                                                OffsetDateTime dueDateTime = null;
                                                if (singleIssue.getDueDate() != null) {
                                                        Instant dueInstant = Instant.ofEpochMilli(
                                                                        singleIssue.getDueDate().getMillis());
                                                        dueDateTime = OffsetDateTime.ofInstant(dueInstant,
                                                                        ZoneId.of(singleIssue.getDueDate().getZone()
                                                                                        .getID()));
                                                }
                                                // ---------------------------------------------------------------------------

                                                // This block of code is just getting
                                                // the resolution date and time for each issue
                                                // Currently, some values are null;
                                                // so I need to use if statements to handle that
                                                // ---------------------------------------------------------------------------
                                                String resolvedDateTimeField = fieldValues.get("Resolved");
                                                OffsetDateTime resolutionDateTime = null;
                                                if (singleIssue.getField(resolvedDateTimeField).getValue() != null) {
                                                        String resolutionDateTimeString = singleIssue
                                                                        .getField(resolvedDateTimeField).getValue()
                                                                        .toString();
                                                        DateTimeFormatter formatter = DateTimeFormatter
                                                                        .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                                                        resolutionDateTime = OffsetDateTime
                                                                        .parse(resolutionDateTimeString, formatter);
                                                }
                                                // ----------------------------------------------------------------------------

                                                // This block of code is grabbing the story points per issue if they
                                                // have them
                                                // This is one location where the hashmap comes back from earlier
                                                // ------------------------------------------------------------------------
                                                String storyPointField = fieldValues.get("Story Points");
                                                Float storyPointInfo = null;
                                                if (singleIssue.getField(storyPointField).getValue() != null) {
                                                        storyPointInfo = Float
                                                                        .parseFloat(singleIssue
                                                                                        .getField(storyPointField)
                                                                                        .getValue()
                                                                                        .toString());
                                                }
                                                // ------------------------------------------------------------------------

                                                // This block of code is grabbing all the subTypes per issue
                                                // This is another location where the hashmap comes back again
                                                // ------------------------------------------------------------------------
                                                String typeField = fieldValues.get("Type");
                                                String subType = null;
                                                if (singleIssue.getField(typeField).getValue() != null) {
                                                        String subTypeValueJsonString = singleIssue.getField(typeField)
                                                                        .getValue()
                                                                        .toString();
                                                        ObjectMapper mapper = new ObjectMapper();
                                                        JsonNode node = mapper.readTree(subTypeValueJsonString);
                                                        subType = node.get("value").asText();
                                                        // https://stackoverflow.com/questions/5245840/how-to-convert-jsonstring-to-jsonobject-in-java
                                                }
                                                // ------------------------------------------------------------------------

                                                // Block of code just grabbing the resolution per issue
                                                // ------------------------------------------------------
                                                String resolution = null;
                                                if (singleIssue.getResolution() != null) {
                                                        resolution = singleIssue.getResolution().getName();
                                                }

                                                // Block of code just grabbing the priority per issue
                                                // ---------------------------------------------------
                                                String priority = null;
                                                if (singleIssue.getPriority() != null) {
                                                        priority = singleIssue.getPriority().getName();
                                                }
                                                // ---------------------------------------------------

                                                // Block of code grabbing the assignee and the avator url for them per
                                                // issue
                                                // --------------------------------------------------------------------------
                                                String assigneeName = null;
                                                String assigneeNameUrl = null;
                                                if (singleIssue.getAssignee() != null) {
                                                        assigneeName = singleIssue.getAssignee().getDisplayName();
                                                        assigneeNameUrl = singleIssue.getAssignee().getAvatarUri()
                                                                        .toString();
                                                }
                                                // --------------------------------------------------------------------------

                                                // Setting the values from the Jira Extraction to a variable in the Java
                                                // Issue
                                                // Object
                                                // --------------------------------------------------------
                                                issue.setId(Integer.parseInt(issueNumber));
                                                issue.setName(singleIssue.getKey());
                                                issue.setProjectName(projectName);
                                                issue.setProjectUniqueId(projectUniqueId);
                                                issue.setTeamType(singleIssue.getIssueType().getName());
                                                issue.setStatus(singleIssue.getStatus().getName());
                                                issue.setCreatedDateTime(creationDateTime);
                                                issue.setUpdatedDateTime(updatedDateTime);
                                                issue.setDueDateTime(dueDateTime);
                                                issue.setResolutionDateTime(resolutionDateTime);
                                                issue.setStoryPoint(storyPointInfo);
                                                issue.setSubType(subType);
                                                issue.setResolution(resolution);
                                                issue.setPriority(priority);
                                                issue.setAssignee(assigneeName);
                                                issue.setAssigneeAvatarUrl(assigneeNameUrl);
                                                // --------------------------------------------------------

                                                // Just saving that Issue Object with the saved values
                                                // into the database with the repository
                                                // Also increasing the issueCount so that I know
                                                // where to start the next issue search from
                                                // ---------------------------
                                                issueRepository.save(issue);
                                                issueCount += 1;
                                                // ---------------------------
                                        }

                                        // Outside the while loop means we haev iterated through all the issues in the
                                        // iterable
                                        // Now we continue to grab the next set of issues using the issueCount as the
                                        // starting point
                                        // -----------------------------------------------------------------------------
                                        allIssues = myJiraClient.getAllIssues(projectName, issueCount);
                                        allIssuesCount = StreamSupport.stream(allIssues.spliterator(), false).count();
                                        // -----------------------------------------------------------------------------
                                }
                                project.setCreatedDate(projectCreationDateTime);
                                project.setNumIssues(issueCount);
                                projectRepository.save(project);
                        }
                        System.out.println("finished, please wait 30 minutes for the initial update");
                        myJiraClient.getRestClient().close();

                } catch (RestClientException e) {
                        System.out.println(e.getLocalizedMessage());
                }

        }
}