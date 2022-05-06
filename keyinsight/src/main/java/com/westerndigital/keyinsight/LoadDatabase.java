package com.westerndigital.keyinsight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.westerndigital.keyinsight.JiraIssue.JiraIssue;
import com.westerndigital.keyinsight.JiraIssue.JiraIssueRepository;
import com.westerndigital.keyinsight.JiraProject.JiraProject;
import com.westerndigital.keyinsight.JiraProject.JiraProjectRepository;

// import com.westerndigital.keyinsight.NotificationSettings.NotificationSettingsRepository;
// import com.westerndigital.keyinsight.JiraServer.JiraServerRepository;
// import com.westerndigital.keyinsight.JiraUser.JiraUserRepository;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.JsonNode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetAllProjectsPOJO.ProjectJson;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO.Issues;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO.IssuesFromSearchJson;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO.SingleProjectJson;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO.Versions;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleUser.UserJson;

@Component
public class LoadDatabase implements CommandLineRunner {

    // inject repositories
    // @Autowired
    // private JiraUserRepository userRepository;

    // @Autowired
    // private JiraServerRepository serverRepository;

    @Autowired
    private JiraProjectRepository projectRepository;

    @Autowired
    private JiraIssueRepository issueRepository;

    // @Autowired
    // private NotificationSettingsRepository notificationSettingsRepository;

    @Override
    public void run(String... args) throws Exception {
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

        // allows me to use the .env variables
        Dotenv dotenv = Dotenv.load();

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        // this commented out line of code allows the json to skip over the attributes
        // that I did not
        // create to avoid conflicts
        // mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final Long startTime = System.currentTimeMillis();
        HttpResponse<JsonNode> getAllProjects = Unirest.get(dotenv.get("JIRA_URL") + "/rest/api/latest/project")
                .basicAuth(dotenv.get("JIRA_USERNAME"), dotenv.get("JIRA_PASSWORD"))
                .header("Accept", "application/json")
                .asJson();
        ArrayList<ProjectJson> projectJsons = mapper.readValue(getAllProjects.getBody().getArray().toString(),
                new TypeReference<ArrayList<ProjectJson>>() {
                });

        for (ProjectJson projectJson : projectJsons) {
            System.out.println(projectJson.getName());
            System.out.println(projectJson.getId());
            System.out.println(projectJson.getProjectTypeKey());
            HttpResponse<JsonNode> getSingleProject = Unirest
                    .get(dotenv.get("JIRA_URL") + "/rest/api/latest/project/" + projectJson.getId())
                    .basicAuth(dotenv.get("JIRA_USERNAME"), dotenv.get("JIRA_PASSWORD"))
                    .header("Accept", "application/json")
                    .asJson();
            ArrayList<SingleProjectJson> singleProjectJsons = mapper.readValue(
                    getSingleProject.getBody().getArray().toString(),
                    new TypeReference<ArrayList<SingleProjectJson>>() {
                    });

            for (SingleProjectJson singleProjectJson : singleProjectJsons) {
                System.out.println(singleProjectJson.getName());
                OffsetDateTime projectCreationDateTime = null;
                JiraProject project = projectRepository.findById(dotenv.get("JIRA_URL") + singleProjectJson.getId())
                        .orElse(new JiraProject());
                project.setId(dotenv.get("JIRA_URL") + singleProjectJson.getId());
                project.setName(singleProjectJson.getName().trim());
                project.setProjectLead(singleProjectJson.getLead().getDisplayName());
                project.setProjectLeadAvatarUrl(singleProjectJson.getLead().getAvatarUrls().getSize48());
                HttpResponse<JsonNode> getSingleUser = Unirest
                        .get(dotenv.get("JIRA_URL") + "/rest/api/latest/user?key="
                                + singleProjectJson.getLead().getKey())
                        .basicAuth(dotenv.get("JIRA_USERNAME"), dotenv.get("JIRA_PASSWORD"))
                        .header("Accept", "application/json")
                        .asJson();

                ArrayList<UserJson> userJsons = mapper.readValue(
                        getSingleUser.getBody().getArray().toString(),
                        new TypeReference<ArrayList<UserJson>>() {
                        });

                for (UserJson userJson : userJsons) {
                    ArrayList<Versions> versions = singleProjectJson.getVersions();
                    for (Versions version : versions) {
                        if (projectCreationDateTime == null
                                || projectCreationDateTime.toLocalDate().isAfter(version.getStartDate())) {
                            ZoneId zoneId = ZoneId.of(userJson.getTimeZone());
                            projectCreationDateTime = version.getStartDate().atStartOfDay(zoneId).toOffsetDateTime();
                        }
                    }
                }
                projectRepository.save(project);

                Integer totalCount = 0;
                Integer currentCount = 0;
                String jqlQuery = "project=" + singleProjectJson.getName().trim();
                Integer startLocation = 0;
                Integer maxSearchResults = -1;
                String searchUrl = String.format("%s/rest/api/latest/search?jql=%s&startAt=%d&maxResults=%d",
                        dotenv.get("JIRA_URL"), jqlQuery, startLocation, maxSearchResults);

                HttpResponse<JsonNode> getIssues = Unirest.get(searchUrl)
                        .basicAuth(dotenv.get("JIRA_USERNAME"), dotenv.get("JIRA_PASSWORD"))
                        .header("Accept", "application/json").asJson();

                ArrayList<IssuesFromSearchJson> issuesFromSearchJsons = mapper.readValue(
                        getIssues.getBody().getArray().toString(),
                        new TypeReference<ArrayList<IssuesFromSearchJson>>() {
                        });
                do {
                    for (IssuesFromSearchJson issuesFromSearchJson : issuesFromSearchJsons) {
                        if (!issuesFromSearchJson.getIssues().isEmpty()) {
                            ArrayList<Issues> listOfIssues = issuesFromSearchJson
                                    .getIssues();
                            for (Issues singleIssue : listOfIssues) {
                                // finds an issue in the database with that issueNumber
                                // if it doesn't exist, create a new Java Issue Object
                                JiraIssue issue = issueRepository.findById(singleIssue.getKey())
                                        .orElse(new JiraIssue());
                                issue.setId(singleIssue.getKey());
                                issue.setIssueNumber(Integer.parseInt(
                                        singleIssue.getKey().trim().substring(singleIssue.getKey().indexOf('-') + 1)));
                                System.out.println(singleIssue.getKey());
                                String assignee = null;
                                String assigneeUrl = null;
                                if (singleIssue.getFields().getAssignee() != null) {
                                    assignee = singleIssue.getFields().getAssignee().getDisplayName();
                                    assigneeUrl = singleIssue.getFields().getAssignee().getAvatarUrls().getSize48();
                                }
                                issue.setAssignee(assignee);
                                issue.setAssigneeAvatarUrl(assigneeUrl);
                                issue.setCreatedDateTime(singleIssue.getFields().getCreated());
                                if (projectCreationDateTime.isAfter(singleIssue.getFields().getCreated())) {
                                    projectCreationDateTime = singleIssue.getFields().getCreated();
                                }
                                OffsetDateTime dueDateTime = null;
                                // https://stackoverflow.com/questions/57214468/java-8-convert-localdate-to-offsetdatetime
                                if (singleIssue.getFields().getDuedate() != null) {
                                    ZoneId zoneId = ZoneId.of(singleIssue.getFields().getCreator().getTimeZone());
                                    dueDateTime = singleIssue.getFields().getDuedate().atStartOfDay(zoneId)
                                            .toOffsetDateTime();
                                }
                                issue.setDueDateTime(dueDateTime);
                                String priority = null;
                                if (singleIssue.getFields().getPriority() != null) {
                                    priority = singleIssue.getFields().getPriority().getName();
                                }
                                issue.setPriority(priority);
                                issue.setProjectName(singleIssue.getFields().getProject().getName().trim());
                                issue.setProjectUniqueId(dotenv.get("JIRA_URL") + singleProjectJson.getId());
                                String resolutionName = null;
                                if (singleIssue.getFields().getResolution() != null) {
                                    resolutionName = singleIssue.getFields().getResolution().getName();
                                }
                                issue.setResolution(resolutionName);
                                issue.setResolutionDateTime(singleIssue.getFields().getResolutiondate());
                                issue.setStatus(singleIssue.getFields().getStatus().getName());
                                issue.setStoryPoint(singleIssue.getFields().getStorypoints());
                                String secondType = null;
                                if (singleIssue.getFields().getSecondtype() != null) {
                                    secondType = singleIssue.getFields().getSecondtype().getValue();
                                }
                                issue.setSecondType(secondType);
                                issue.setIssueType(singleIssue.getFields().getIssuetype().getName());
                                issue.setUpdatedDateTime(singleIssue.getFields().getUpdated());
                                issueRepository.save(issue);
                            }
                        } else {
                            System.out.println("issues is empty");
                            break;
                        }
                        currentCount = issuesFromSearchJson.getMaxResults();
                        totalCount = issuesFromSearchJson.getTotal();
                    }
                    startLocation += currentCount;
                    System.out.println("Finished Current Issues, New startLocation at " + startLocation);
                    searchUrl = String.format(
                            "%s/rest/api/latest/search?jql=%s&startAt=%d&maxResults=%d",
                            dotenv.get("JIRA_URL"), jqlQuery, startLocation,
                            maxSearchResults);
                    getIssues = Unirest
                            .get(searchUrl)
                            .basicAuth(dotenv.get("JIRA_USERNAME"),
                                    dotenv.get("JIRA_PASSWORD"))
                            .header("Accept", "application/json")
                            .asJson();
                    issuesFromSearchJsons = mapper.readValue(
                            getIssues.getBody().getArray().toString(),
                            new TypeReference<ArrayList<IssuesFromSearchJson>>() {
                            });

                } while (startLocation < totalCount);
                System.out.println("Outside of the while issue loop");
                project.setNumberOfIssues(totalCount);
                project.setCreatedDate(projectCreationDateTime);
                projectRepository.save(project);
            }
        }
        final Long endTime = System.currentTimeMillis();
        System.out.println("Total Execution Time: " + ((endTime - startTime) / 60000) + " minutes");

    }
}

/*
 * List of Resources
 * https://stackoverflow.com/questions/20832015/how-do-i-iterate-over-a-json-
 * response-using-jackson-api-of-a-list-inside-a-list
 * 
 * http://makeseleniumeasy.com/2020/06/11/rest-assured-tutorial-30-how-to-create
 * -pojo-classes-of-a-json-array-payload/
 * 
 * https://stackoverflow.com/questions/58539657/com-fasterxml-jackson-databind-
 * exc-mismatchedinputexception-cannot-deserialize
 * 
 * https://stackoverflow.com/questions/7421474/how-can-i-tell-jackson-to-ignore-
 * a-property-for-which-i-dont-have-control-over
 * 
 * https://stackoverflow.com/questions/20837856/can-not-deserialize-instance-of-
 * java-util-arraylist-out-of-start-object-token
 * 
 * https://docs.atlassian.com/software/jira/docs/api/REST/8.13.10/
 * 
 * https://developer.atlassian.com/cloud/jira/platform/rest/v3/api-group-
 * projects/#api-rest-api-3-project-search-get
 */