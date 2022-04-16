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
        // This block of code underneath just deletes every entry in the database during
        // startup
        // ------------------------------------------
        userRepository.deleteAll();
        serverRepository.deleteAll();
        notificationSettingsRepository.deleteAll();
        projectRepository.deleteAll();
        issueRepository.deleteAll();
        notificationSettingsRepository.deleteAll();
        // -------------------------------------------

        // This block of code attempts to use the username, password, and server url to
        // create a
        // Jira Rest Java Client(JRJC) that connects to the server
        // That JRJC allows us to interact with the Jira Server and grab the information
        // we need
        // The authenication doesn't happen until the client attempts grab some kind of
        // information
        // --------------------------------------------------------------------
        try {
            Dotenv dotenv = Dotenv.load();
            myJiraClient = new JiraRestJavaClient(dotenv.get("JIRA_USERNAME"),
                    dotenv.get("JIRA_PASSWORD"), dotenv.get("JIRA_URL"));
            User user = myJiraClient.getUser(dotenv.get("JIRA_USERNAME"));
        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
        }
        // --------------------------------------------------------------------

        // This whole try catch block is in case an exeception occurs
        // when extracting the information into the PostgreSQL database
        // -------------------------------------------------------------------------------
        try {

            allProjects = myJiraClient.getAllProject(); // grabs all the projects that are within the Jira Server

            // Iterating through all the projects that was grabbed in the line of code above
            for (BasicProject basicProject : allProjects) {

                JiraProject project = new JiraProject();// creates a Java Project Object that allows us to store the
                                                        // Jira Project information using the setters

                // This block of code is just extracting information from the Project
                // using getters from the class and JRJC
                // ------------------------------------------------------------
                String projectKey = basicProject.getKey();
                Project singleProject = myJiraClient.getProject(projectKey);
                String projectName = singleProject.getName();
                String productLeadName = singleProject.getLead().getName();
                User projectLead = myJiraClient.getUser(productLeadName);
                String projectLeadDisplayName = projectLead.getDisplayName();
                // ------------------------------------------------------------

                // This block of code is setting all the information from the code above
                // and storing it in the Java Project Object
                // -------------------------------------------------------------------
                project.setName(projectName);
                project.setTeamLead(projectLeadDisplayName);
                project.setTeamLeadAvatarUrl(projectLead.getAvatarUri().toString());
                // -------------------------------------------------------------------

                // This block of code is to get ready to go through all the issues that the Jira
                // Project has
                // -----------------------------------------------------------------------------------
                int issueCount = 0;
                HashMap<String, String> fieldValues = new HashMap<String, String>();
                allIssues = myJiraClient.getAllIssues(projectName, issueCount); // if I have issueCount = 0, the first
                                                                                // Issue that I grab is B8X4-10282 not
                                                                                // B8X4-1
                Long allIssuesCount = StreamSupport.stream(allIssues.spliterator(), false).count();
                // -----------------------------------------------------------------------------------

                // This while loop just runs as long as the allIssuesCount isn't 0
                // -----------------------------------------------------------------------------
                while (allIssuesCount > 0) {

                    // As long as the allIssuesCount isn't 0, we have to iterate through all of the
                    // issues stored in the Iterable
                    for (Issue singleIssue : allIssues) {

                        JiraIssue issue = new JiraIssue(); // creates a Java Issue Object that allows us to store the
                                                           // Jira Issue information using the setters

                        // This block of code is just grabbing the issueNumber after the dash
                        // Example B8X4-10282,this block of code just grabs 10282
                        // ------------------------------------------------------------------
                        String issueNumber = singleIssue.getKey();
                        issueNumber = issueNumber.substring(issueNumber.indexOf('-') + 1);
                        System.out.println(issueNumber);
                        // ------------------------------------------------------------------

                        // Only needs to run on the FIRST iteration, we need to grab all the fields that
                        // an issue could potentially have and store it in the hashmap to use later
                        // ----------------------------------------------------------------
                        if (issueCount == 0) {
                            allIssueFields = singleIssue.getFields();
                            for (IssueField issueField : allIssueFields) {
                                fieldValues.put(issueField.getName(), issueField.getId());
                            }
                        }
                        // ----------------------------------------------------------------

                        // This block of code is just formatting
                        // the creation date and time for each issue
                        // Currently, these values are never null;
                        // However, I am not sure if that is always the case
                        // -----------------------------------------------------
                        String createCreationDate = String.format("%d-%d-%d",
                                singleIssue.getCreationDate().getYear(),
                                singleIssue.getCreationDate().getMonthOfYear(),
                                singleIssue.getCreationDate().getDayOfMonth());

                        String createCreationTime = String.format("%d:%d",
                                singleIssue.getCreationDate().getHourOfDay(),
                                singleIssue.getCreationDate().getMinuteOfHour());
                        // -------------------------------------------------------

                        // This block of code is just formatting
                        // the updated date and time for each issue
                        // Currently, these values are never null;
                        // However, I am not sure if that is always the case
                        // -------------------------------------------------------
                        String updatedDate = String.format("%d-%d-%d",
                                singleIssue.getUpdateDate().getYear(),
                                singleIssue.getUpdateDate().getMonthOfYear(),
                                singleIssue.getUpdateDate().getDayOfMonth());

                        String updatedTime = String.format("%d:%d",
                                singleIssue.getUpdateDate().getHourOfDay(),
                                singleIssue.getUpdateDate().getMinuteOfHour());
                        // -------------------------------------------------------

                        // This block of code is just formatting
                        // the due date and time for each issue
                        // Currently, some values are null;
                        // so I need to use if statements to handle that
                        // ---------------------------------------------------------------------------
                        String dueDate = null;
                        String dueTime = null;
                        if (singleIssue.getDueDate() != null) {
                            dueDate = String.format("%d-%d-%d", singleIssue.getDueDate().getYear(),
                                    singleIssue.getDueDate().getMonthOfYear(),
                                    singleIssue.getDueDate().getDayOfMonth());

                            dueTime = String.format("%d:%d", singleIssue.getDueDate().getHourOfDay(),
                                    singleIssue.getDueDate().getMinuteOfHour());
                        }
                        // ---------------------------------------------------------------------------

                        // This block of code is grabbing the story points per issue if they have them
                        // This is one location where the hashmap comes back from earlier
                        // ------------------------------------------------------------------------
                        String storyPointField = fieldValues.get("Story Points");
                        Float storyPointInfo = null;
                        if (singleIssue.getField(storyPointField).getValue() != null) {
                            storyPointInfo = Float
                                    .parseFloat(singleIssue.getField(storyPointField).getValue()
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
                        // ------------------------------------------------------

                        // Block of code just grabbing the priority per issue
                        // ---------------------------------------------------
                        String priority = null;
                        if (singleIssue.getPriority() != null) {
                            priority = singleIssue.getPriority().getName();
                        }
                        // ---------------------------------------------------

                        // Block of code grabbing the assignee and the avator url for them per issue
                        // --------------------------------------------------------------------------
                        String assigneeName = null;
                        String assigneeNameUrl = null;
                        if (singleIssue.getAssignee() != null) {
                            assigneeName = singleIssue.getAssignee().getDisplayName();
                            assigneeNameUrl = singleIssue.getAssignee().getAvatarUri().toString();
                        }
                        // --------------------------------------------------------------------------

                        // Setting the values from the Jira Extraction to a variable in the Java Issue
                        // Object
                        // --------------------------------------------------------
                        issue.setId(Integer.parseInt(issueNumber));
                        issue.setName(singleIssue.getKey());
                        issue.setProjectName(projectName);
                        issue.setTeamType(singleIssue.getIssueType().getName());
                        issue.setStatus(singleIssue.getStatus().getName());
                        issue.setCreationDate(createCreationDate);
                        issue.setCreationTime(createCreationTime);
                        issue.setUpdatedDate(updatedDate);
                        issue.setUpdatedTime(updatedTime);
                        issue.setDueDate(dueDate);
                        issue.setDueTime(dueTime);
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

                        // In our project table, we have a created date column
                        // We decided to use the earlist issue creation date
                        // as that value
                        // ----------------------------------------------------
                        if (Integer.parseInt(issueNumber) == 1) {
                            project.setCreatedDate(createCreationDate);
                        }
                        // ----------------------------------------------------
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
                // Outside of the while loop means we have iterated through all the issues
                // within that project
                // Just have to set the number of issues to issueCount and save the Java Project
                // Object
                // with the saved values to the repository
                // --------------------------------
                project.setNumIssues(issueCount);
                projectRepository.save(project);
                // ---------------------------------
            }

            System.out.println("finished");

        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
        }
        // --------------------------------------------------------------------------------

    }
}