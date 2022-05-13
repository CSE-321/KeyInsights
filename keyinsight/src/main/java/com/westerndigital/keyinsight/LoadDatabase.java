package com.westerndigital.keyinsight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import com.westerndigital.keyinsight.JiraIssue.JiraIssue;
import com.westerndigital.keyinsight.JiraIssue.JiraIssueService;
import com.westerndigital.keyinsight.JiraProject.JiraProject;
import com.westerndigital.keyinsight.JiraProject.JiraProjectService;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetAllProjectsPOJO.ProjectSearchJson;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO.Issues;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO.IssuesSearchJson;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.ProjectPOJO.ProjectJson;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.ProjectPOJO.Versions;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.UserPOJO.UserJson;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.JsonNode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.westerndigital.keyinsight.Email.EmailService;
import com.westerndigital.keyinsight.Email.DAOs.ResourceDigest;
import com.westerndigital.keyinsight.Email.DAOs.ProjectDigest;

@Component
public class LoadDatabase implements CommandLineRunner {

    // inject repositories
    @Autowired
    private JiraProjectService projectService;

    @Autowired
    private JiraIssueService issueService;

    @Autowired
    private EmailService emailService;

    @Override
    public void run(String... args) throws Exception {

        String to = "dragonoath123@gmail.com";
        String name = "ucm-cse-321";
        String projectName = "B8X4";
        //int issueCount = issueService.unfinishedJiraIssuesByToday(projectName);
        int limitNumber = 10;
        // List<Object[]> tmp = issueService.topXUnfinishedJiraIssuesByToday(projectName, limitNumber);
        // List<String> issueName = new ArrayList<String>();

        // for(Object[] element : tmp){
        //     issueName.add(String.format("Issue Name: %s\nDue Date: %s", element[0].toString(), element[1].toString()));
        // }

        // emailService.sendUnfinishedJiraIssuePastDueDateEmailNotification(to, name, projectName, issueCount, issueName, limitNumber);
        // System.out.println("Sent the first email");

        String priority = "Critical";
        int interval = 150;
        // int issueCount2 = issueService.criticalIssuesNotUpdatedCount(projectName, priority, interval);
        // System.out.println("got the count");
        // List<Object[]> tmp2 = issueService.criticalIssuesNotUpdatedInfo(projectName, priority, interval, limitNumber);
        // List<String> issueInfo = new ArrayList<String>();

        // for(Object[] element2 : tmp2){
        //     issueInfo.add(String.format("Issue Name: %s\nUpdated Date: %s", element2[0].toString(), element2[1].toString()));
        // }

        // emailService.sendCriticalJiraIssueNotUpdatedEmailNotification(to, name, projectName, interval, issueCount2, issueInfo, limitNumber);
        // System.out.println("Sent the second email");

        List<String> teamTypes = issueService.getAllTeamType(projectName);
        // List<String> issueInfo2 = new ArrayList<String>();
        // List<ResourceDigest> resourceDigestList = new ArrayList<ResourceDigest>();
        // for(String teamType : teamTypes){
            
        //     List<Object[]> tmp3 = issueService.resourceWorkloadDigestCreated(projectName, teamType, interval);
        //     List<Object[]> tmp4 = issueService.resourceWorkloadDigestClosed(projectName, teamType, interval);
        //     ResourceDigest resourceDigest = new ResourceDigest();
        //     String issueInfo4 = String.format("Team Type: %s, New Jira Count Assigned Since Last Digest: %s, New Story Points Assigned Since Last Digest: %s, Jira Count Closed Since Last Digest: %s, Total Story Points Closed Since Last Digest: %s", 
        //     teamType, tmp3.get(0)[0], tmp3.get(0)[1], tmp4.get(0)[0], tmp4.get(0)[1]);
        //     resourceDigest.setTeamType(teamType);
        //     resourceDigest.setNewJiraCount(tmp3.get(0)[0].toString());
        //     resourceDigest.setNewJiraStoryPoint(tmp3.get(0)[1].toString());
        //     resourceDigest.setClosedJiraCount(tmp4.get(0)[0].toString());
        //     resourceDigest.setClosedJiraStoryPoint(tmp4.get(0)[1].toString());
        //     resourceDigestList.add(resourceDigest);
        //     issueInfo2.add(issueInfo4);
        //     System.out.println(issueInfo4);

        // }
        // emailService.sendResourceDigestNotification(to, name, projectName, interval, resourceDigestList);
        // System.out.println("Sent the third email");
        String subType = "Bug";
        List<ProjectDigest> projectDigestList = new ArrayList<ProjectDigest>();
        for(String teamType : teamTypes){
            List<Object[]> tmp5 = issueService.projectDigestCreated(projectName, teamType, interval);
            Integer tmp9 = issueService.projectDigestAssigneeCount(projectName, teamType, interval);
            List<Object[]> tmp6 = issueService.projectDigestClosed(projectName, teamType, interval);
            Integer tmp7 = issueService.projectDigestBugsCreated(projectName, teamType, subType, interval);
            Integer tmp8 = issueService.projectDigestBugsClosed(projectName, teamType, subType, interval);
            ProjectDigest projectDigest = new ProjectDigest();
            projectDigest.setTeamType(teamType);
            projectDigest.setNewJiraCount(tmp5.get(0)[0].toString());
            projectDigest.setNewJiraStoryPoint(tmp5.get(0)[1].toString());
            projectDigest.setAssigneeCount(tmp9.toString());
            projectDigest.setClosedJiraCount(tmp6.get(0)[0].toString());
            projectDigest.setClosedJiraStoryPoint(tmp6.get(0)[1].toString());
            projectDigest.setNewJiraBugCount(tmp7.toString());
            projectDigest.setClosedJiraBugCount(tmp8.toString());
            projectDigestList.add(projectDigest);
        }
        emailService.sendProjectDigestNotification(to, name, projectName, interval, projectDigestList);
        System.out.println("Sent the fourth email");

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
        // Dotenv dotenv = Dotenv.load();
        // String JiraUrl = dotenv.get("JIRA_URL");
        // String JiraUsername = dotenv.get("JIRA_USERNAME");
        // String JiraPassword = dotenv.get("JIRA_PASSWORD");

        // // maps the JSON object to the POJO selected
        // ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        // // this commented out line of code allows the json to skip over the attributes
        // // that I did not create to avoid conflicts
        // // mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // final Long startTime = System.currentTimeMillis();

        // // This block of code just uses the REST API for JiraServer to get all the
        // // projects into a JSON
        // // We then use the mapper to map the JSON to the POJO, in this case the
        // // ProjectJson
        // // --------------------------------------------------------------------------------------------------
        // HttpResponse<JsonNode> getAllProjects = Unirest.get(JiraUrl + "/rest/api/latest/project")
        //         .basicAuth(JiraUsername, JiraPassword).header("Accept", "application/json").asJson();

        // List<ProjectSearchJson> projectJsons = mapper.readValue(getAllProjects.getBody().toString(),
        //         new TypeReference<List<ProjectSearchJson>>() {
        //         });
        // // --------------------------------------------------------------------------------------------------

        // for (ProjectSearchJson projectJson : projectJsons) {

        //     // This block of code just uses the REST API for JiraServer to get a specific
        //     // project into a JSON
        //     // We then use the mapper to map the JSON to the POJO, in this case the
        //     // SingleProjectJson
        //     // ---------------------------------------------------------------------------------
        //     HttpResponse<JsonNode> getSingleProject = Unirest
        //             .get(JiraUrl + "/rest/api/latest/project/" + projectJson.getId())
        //             .basicAuth(JiraUsername, JiraPassword).header("Accept", "application/json").asJson();

        //     ProjectJson singleProjectJson = mapper.readValue(getSingleProject.getBody().toString(),
        //             new TypeReference<ProjectJson>() {
        //             });
        //     // -----------------------------------------------------------------------------

        //     JiraProject project = projectService.findById(JiraUrl + singleProjectJson.getId());
        //     project.setId(JiraUrl + singleProjectJson.getId());
        //     project.setName(singleProjectJson.getName().trim());
        //     project.setTeamLead(singleProjectJson.getLead().getDisplayName());
        //     project.setTeamLeadAvatarUrl(singleProjectJson.getLead().getAvatarUrls().getSize48());
        //     project.setProjectType(singleProjectJson.getProjectTypeKey());
        //     // This block of code just uses the REST API for JiraServer to get a specific
        //     // user into a JSON
        //     // We then use the mapper to map the JSON to the POJO, in this case the UserJson
        //     // --------------------------------------------------------
        //     HttpResponse<JsonNode> getSingleUser = Unirest
        //             .get(JiraUrl + "/rest/api/latest/user")
        //             .basicAuth(JiraUsername, JiraPassword).header("Accept", "application/json")
        //             .queryString("key", singleProjectJson.getLead().getKey()).asJson();

        //     UserJson userJson = mapper.readValue(
        //             getSingleUser.getBody().toString(), new TypeReference<UserJson>() {
        //             });
        //     // --------------------------------------------------------

        //     // This block of code is just for getting the timezone of the project lead
        //     // We can replace it by having the timezone be default GMT
        //     // --------------------------------------------------------------------------------------------------------
        //     OffsetDateTime projectCreationDateTime = null;
        //     if (project.getCreatedDate() != null) {
        //         projectCreationDateTime = project.getCreatedDate();
        //     }

        //     List<Versions> versions = singleProjectJson.getVersions();
        //     for (Versions version : versions) {
        //         if (projectCreationDateTime == null
        //                 || projectCreationDateTime.toLocalDate().isAfter(version.getStartDate())) {
        //             ZoneId zoneId = ZoneId.of(userJson.getTimeZone());
        //             projectCreationDateTime = version.getStartDate().atStartOfDay(zoneId).toOffsetDateTime();
        //         }
        //     }
        //     project.setCreatedDate(projectCreationDateTime);
        //     // --------------------------------------------------------------------------------------------------------
        //     projectService.saveSingleProject(project);

        //     // This block of code just uses the REST API for JiraServer to get a the maximum
        //     // numbers of issues at once into a JSON
        //     // We then use the mapper to map the JSON to the POJO, in this case the
        //     // IssuesFromSearchJson
        //     // -----------------------------------------------------------------------------------------------------------
        //     Integer totalCount = 0;
        //     Integer currentCount = 0;
        //     String jqlQuery = String.format("project=%s",
        //             singleProjectJson.getName().trim());
        //     Integer startLocation = 0;
        //     Integer maxSearchResults = -1;
        //     String searchUrl = String.format("%s/rest/api/latest/search?startAt=%d&maxResults=%d",
        //             JiraUrl, startLocation, maxSearchResults);
        //     HttpResponse<JsonNode> getIssues = Unirest.get(searchUrl)
        //             .basicAuth(JiraUsername, JiraPassword).header("Accept", "application/json")
        //             .queryString("jql", jqlQuery).asJson();

        //     IssuesSearchJson issuesFromSearchJson = mapper.readValue(
        //             getIssues.getBody().toString(), new TypeReference<IssuesSearchJson>() {
        //             });
        //     // -----------------------------------------------------------------------------------------------------------

        //     do {
        //         if (!issuesFromSearchJson.getIssues().isEmpty()) {
        //             List<Issues> listOfIssues = issuesFromSearchJson
        //                     .getIssues();
        //             for (Issues singleIssue : listOfIssues) {
        //                 // finds an issue in the database with that issueNumber
        //                 // if it doesn't exist, create a new Java Issue Object
        //                 JiraIssue issue = issueService.findById(singleIssue.getKey());
        //                 System.out.println(singleIssue.getKey());
        //                 issue.setId(singleIssue.getKey());
        //                 issue.setIssueNumber(Integer.parseInt(
        //                         singleIssue.getKey().trim().substring(singleIssue.getKey().indexOf('-') + 1)));
        //                 String assignee = null;
        //                 String assigneeUrl = null;
        //                 if (singleIssue.getFields().getAssignee() != null) {
        //                     assignee = singleIssue.getFields().getAssignee().getDisplayName();
        //                     assigneeUrl = singleIssue.getFields().getAssignee().getAvatarUrls().getSize48();
        //                 }
        //                 issue.setAssignee(assignee);
        //                 issue.setAssigneeAvatarUrl(assigneeUrl);
        //                 issue.setCreatedDateTime(singleIssue.getFields().getCreated());
        //                 if (projectCreationDateTime.isAfter(singleIssue.getFields().getCreated())) {
        //                     projectCreationDateTime = singleIssue.getFields().getCreated();
        //                 }
        //                 OffsetDateTime dueDateTime = null;
        //                 // https://stackoverflow.com/questions/57214468/java-8-convert-localdate-to-offsetdatetime
        //                 if (singleIssue.getFields().getDuedate() != null) {
        //                     ZoneId zoneId = ZoneId.of(singleIssue.getFields().getCreator().getTimeZone());
        //                     dueDateTime = singleIssue.getFields().getDuedate().atStartOfDay(zoneId)
        //                             .toOffsetDateTime();
        //                 }
        //                 issue.setDueDateTime(dueDateTime);
        //                 String priority = null;
        //                 if (singleIssue.getFields().getPriority() != null) {
        //                     priority = singleIssue.getFields().getPriority().getName();
        //                 }
        //                 issue.setPriority(priority);
        //                 issue.setProjectName(singleIssue.getFields().getProject().getName().trim());
        //                 issue.setProjectUniqueId(JiraUrl + singleProjectJson.getId());
        //                 String resolutionName = null;
        //                 if (singleIssue.getFields().getResolution() != null) {
        //                     resolutionName = singleIssue.getFields().getResolution().getName();
        //                 }
        //                 issue.setResolution(resolutionName);
        //                 issue.setResolutionDateTime(singleIssue.getFields().getResolutiondate());
        //                 issue.setStatus(singleIssue.getFields().getStatus().getName());
        //                 issue.setStoryPoint(singleIssue.getFields().getStorypoints());
        //                 String secondType = null;
        //                 if (singleIssue.getFields().getSecondtype() != null) {
        //                     secondType = singleIssue.getFields().getSecondtype().getValue();
        //                 }
        //                 issue.setSubType(secondType);
        //                 issue.setTeamType(singleIssue.getFields().getIssuetype().getName());
        //                 issue.setUpdatedDateTime(singleIssue.getFields().getUpdated());
        //                 issueService.saveSingleIssue(issue);
        //             }
        //         } else {
        //             System.out.println("issues is empty");
        //             break;
        //         }
        //         currentCount = issuesFromSearchJson.getMaxResults();
        //         totalCount = issuesFromSearchJson.getTotal();
        //         startLocation += currentCount;
        //         System.out.println("Finished Current Issues, New startLocation at " + startLocation);
        //         // --------------------------------------------------------------------------------
        //         searchUrl = String.format("%s/rest/api/latest/search?startAt=%d&maxResults=%d",
        //                 JiraUrl, startLocation, maxSearchResults);

        //         getIssues = Unirest.get(searchUrl).basicAuth(JiraUsername, JiraPassword)
        //                 .header("Accept", "application/json").queryString("jql", jqlQuery).asJson();

        //         issuesFromSearchJson = mapper.readValue(getIssues.getBody().toString(),
        //                 new TypeReference<IssuesSearchJson>() {
        //                 });
        //         // --------------------------------------------------------------------------------

        //     } while (startLocation < totalCount);
        //     System.out.println("Outside of the while issue loop");
        //     project.setNumIssues(totalCount);
        //     project.setCreatedDate(projectCreationDateTime);
        //     projectService.saveSingleProject(project);
        // }
        // final Long endTime = System.currentTimeMillis();
        // System.out.println("Total Execution Time: " + (endTime - startTime) + " milliseconds");
        // System.out
        //         .println("Please wait 30 minutes for the database to update! Current time is " + OffsetDateTime.now()
        //                 + ". The next Update is at " + OffsetDateTime.now().plusMinutes(30));

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
