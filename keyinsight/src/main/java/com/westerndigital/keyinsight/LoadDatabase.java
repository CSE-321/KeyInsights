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
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.cdimascio.dotenv.Dotenv;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.JsonNode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetAllProjectsPOJO.ProjectJson;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetCustomFieldsFromSearchPOJO.CustomFieldJson;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetCustomFieldsFromSearchPOJO.CustomFields;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO.Fields;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO.Issues;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO.IssuesFromSearchJson;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO.Lead;
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

        Dotenv dotenv = Dotenv.load();
        // https://stackoverflow.com/questions/20832015/how-do-i-iterate-over-a-json-response-using-jackson-api-of-a-list-inside-a-list
        // http://makeseleniumeasy.com/2020/06/11/rest-assured-tutorial-30-how-to-create-pojo-classes-of-a-json-array-payload/
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        // https://stackoverflow.com/questions/58539657/com-fasterxml-jackson-databind-exc-mismatchedinputexception-cannot-deserialize
        // not sure what this did to help fix the issue
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        // https://stackoverflow.com/questions/7421474/how-can-i-tell-jackson-to-ignore-a-property-for-which-i-dont-have-control-over
        // this line of code allows the json to skip over the attributes that I did not
        // create to avoid conflicts
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // https://docs.atlassian.com/software/jira/docs/api/REST/8.13.10/
        // https://developer.atlassian.com/cloud/jira/platform/rest/v3/api-group-projects/#api-rest-api-3-project-search-get

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
                .get(dotenv.get("JIRA_URL") + "/rest/api/latest/user?key=" + singleProjectJson.getLead().getKey())
                .basicAuth(dotenv.get("JIRA_USERNAME"), dotenv.get("JIRA_PASSWORD"))
                .header("Accept", "application/json")
                .asJson();

                ArrayList<UserJson> userJsons = mapper.readValue(
                        getSingleUser.getBody().getArray().toString(),
                        new TypeReference<ArrayList<UserJson>>() {
                        });

                for(UserJson userJson : userJsons){
                    ArrayList<Versions> versions = singleProjectJson.getVersions();
                    for(Versions version : versions){
                        if(projectCreationDateTime == null || projectCreationDateTime.toLocalDate().isAfter(version.getStartDate())){
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
                                issue.setIssueNumber(Integer.parseInt(singleIssue.getKey().trim().substring(singleIssue.getKey().indexOf('-') + 1)));
                                System.out.println(singleIssue.getKey());
                                List<Fields> fields = singleIssue.getFields();
                                for (Fields field : fields) {
                                    String assignee = null;
                                    String assigneeUrl = null;
                                    if (field.getAssignee() != null) {
                                        assignee = field.getAssignee().getDisplayName();
                                        assigneeUrl = field.getAssignee().getAvatarUrls().getSize48();
                                    }
                                    issue.setAssignee(assignee);
                                    issue.setAssigneeAvatarUrl(assigneeUrl);
                                    issue.setCreatedDateTime(field.getCreated());
                                    if(projectCreationDateTime.isAfter(field.getCreated())){
                                        projectCreationDateTime = field.getCreated();
                                    }
                                    OffsetDateTime dueDateTime = null;
                                    // https://stackoverflow.com/questions/57214468/java-8-convert-localdate-to-offsetdatetime
                                    if (field.getDuedate() != null) {
                                        ZoneId zoneId = ZoneId.of(field.getCreator().getTimeZone());
                                        dueDateTime = field.getDuedate().atStartOfDay(zoneId).toOffsetDateTime();
                                    }
                                    issue.setDueDateTime(dueDateTime);
                                    String priority = null;
                                    if (field.getPriority() != null) {
                                        priority = field.getPriority().getName();
                                    }
                                    issue.setPriority(priority);
                                    issue.setProjectName(field.getProject().getName().trim());
                                    issue.setProjectUniqueId(dotenv.get("JIRA_URL") + singleProjectJson.getId());
                                    String resolutionName = null;
                                    if (field.getResolution() != null) {
                                        resolutionName = field.getResolution().getName();
                                    }
                                    issue.setResolution(resolutionName);
                                    issue.setResolutionDateTime(field.getResolutiondate());
                                    issue.setStatus(field.getStatus().getName());
                                    issue.setStoryPoint(field.getStorypoints());
                                    String secondType = null;
                                    if (field.getSecondtype() != null) {
                                        secondType = field.getSecondtype().getValue();
                                    }
                                    issue.setSecondType(secondType);
                                    issue.setIssueType(field.getIssuetype().getName());
                                    issue.setUpdatedDateTime(field.getUpdated());
                                    issueRepository.save(issue);

                                }
                            }
                        } else {
                            System.out.println("issues is empty");
                            break;
                        }
                        currentCount = issuesFromSearchJson.getMaxResults();
                        totalCount = issuesFromSearchJson.getTotal();
                    }
                    System.out.println("Finished Current Issues");
                    startLocation += currentCount;
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
        System.out.println("Total Execution Time: " + (endTime - startTime));

    }
}