package com.westerndigital.keyinsight.Notification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.westerndigital.keyinsight.JiraTicket.UnfinishedSprintStatus.UnfinishedSprintStatusTicketJob;
import com.westerndigital.keyinsight.JiraUser.JiraUserService;
import com.westerndigital.keyinsight.Notification.Settings.ProjectDigestReportSetting;
import com.westerndigital.keyinsight.Notification.Settings.SprintStatusSetting;
import com.westerndigital.keyinsight.Notification.Settings.TicketStatusSetting;
import com.westerndigital.keyinsight.Notification.Settings.UnfinishedTicketSetting;
import com.westerndigital.keyinsight.Notification.Settings.WorkloadDigestReportSetting;
import com.westerndigital.keyinsight.Scheduler.SchedulerJob;
import com.westerndigital.keyinsight.Scheduler.SchedulerJobService;

@Controller
@RequestMapping("/api/v1/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private JiraUserService jiraUserService;

    @Autowired
    private SchedulerJobService schedulerJobService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService
            .getAllNotificationSettings(); 

        return ResponseEntity.ok(notifications);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateNotificationSettings(
        RequestEntity<String> requestEntity) throws JsonProcessingException {

        // extract the user data from the request body
        String jsonString = requestEntity.getBody();

        final String USERNAME_FIELD = "userId";
        final String SERVER_URL_FIELD = "serverId";
        final String PROJECT_NAME_FIELD = "projectId";
        final String TICKET_STATUS_FIELD = "ticketStatusSetting";
        final String SPRINT_STATUS_FIELD = "sprintStatusSetting";
        final String UNFINISHED_TICKET_FIELD = "unfinishedTicketSetting";
        final String PROJECT_DIGEST_REPORT_FIELD = "projectDigestReportSetting";
        final String WORKLOAD_DIGEST_REPORT_FIELD = "workloadDigestReportSetting";

        JsonNode rootNode;
        JsonNode username, serverUrl, projectName, ticketStatus, sprintStatus, 
            unfinishedTicket, projectDigestReport, workloadDigestReport;

        // extract the JSON fields
        ObjectMapper objectMapper = new ObjectMapper();
        rootNode = objectMapper.readTree(jsonString);

        username = rootNode.path(USERNAME_FIELD);
        serverUrl = rootNode.path(SERVER_URL_FIELD);
        projectName = rootNode.path(PROJECT_NAME_FIELD);

        ticketStatus = rootNode.path(TICKET_STATUS_FIELD); 
        sprintStatus = rootNode.path(SPRINT_STATUS_FIELD);;
        unfinishedTicket = rootNode.path(UNFINISHED_TICKET_FIELD);
        projectDigestReport = rootNode.path(PROJECT_DIGEST_REPORT_FIELD);
        workloadDigestReport = rootNode.path(WORKLOAD_DIGEST_REPORT_FIELD);

        String jiraUser = objectMapper.convertValue(
            username, String.class);
        System.out.println(jiraUser);

        String jiraServerUrl = objectMapper.convertValue(
            serverUrl, String.class);

        String jiraProjectName = objectMapper.convertValue(
            projectName, String.class);

        String jiraEmail = jiraUserService
            .loadUserByUsername(jiraUser)
            .getEmail();

        // convert each notification setting field in the JSON to its
        // corresponding Java object 
        TicketStatusSetting ticketStatusSetting = objectMapper
            .convertValue(ticketStatus, TicketStatusSetting.class);

        SprintStatusSetting sprintStatusSetting = objectMapper
            .convertValue(sprintStatus, SprintStatusSetting.class);

        UnfinishedTicketSetting unfinishedTicketSetting = objectMapper
            .convertValue(unfinishedTicket, 
                UnfinishedTicketSetting.class);

        ProjectDigestReportSetting projectDigestReportSetting = objectMapper
            .convertValue(projectDigestReport, 
                ProjectDigestReportSetting.class);

        WorkloadDigestReportSetting workloadDigestReportSetting = objectMapper
            .convertValue(workloadDigestReport, 
                WorkloadDigestReportSetting.class);

        Notification notification = new Notification(jiraUser, jiraEmail, 
            jiraServerUrl, jiraProjectName, ticketStatusSetting, 
            sprintStatusSetting, unfinishedTicketSetting, 
            projectDigestReportSetting, workloadDigestReportSetting);

        notificationService.updateNotificationSettings(notification);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
