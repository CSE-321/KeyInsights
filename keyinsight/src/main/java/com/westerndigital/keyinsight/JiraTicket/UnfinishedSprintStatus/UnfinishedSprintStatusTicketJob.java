package com.westerndigital.keyinsight.JiraTicket.UnfinishedSprintStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import com.westerndigital.keyinsight.Email.EmailService;
import com.westerndigital.keyinsight.JiraIssue.JiraIssueRepository;
import com.westerndigital.keyinsight.Notification.NotificationRepository;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UnfinishedSprintStatusTicketJob extends QuartzJobBean {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JiraIssueRepository jiraIssueRepository;

    @Autowired
    private EmailService emailService;
    
    @Override
    protected void executeInternal(JobExecutionContext context) 
        throws JobExecutionException {

        final int ISSUE_LIMIT = 10;
        System.out.println("UnfinishedJiraTicketJob");
        
        // filter the Notification table for users who enabled the notification
        notificationRepository.findAll()
            .stream()
            .filter(notification -> notification.getUnfinishedTicketSetting().isNotifyUser())
            .forEach(user -> {
                String projectName = user.getProjectName();
                // query for unfinished ticket count
                int unfinishedTicketCount = jiraIssueRepository.unfinishedJiraIssuesByToday(projectName);

                System.out.println("Unfinished tickets: " + unfinishedTicketCount);

                List<Object[]> unfinishedTickets = jiraIssueRepository.topXUnifinishedJiraIssuesByToday(projectName, ISSUE_LIMIT);

                // format the Jira issues for the email
                List<String> unfinishedTicketInfo = new ArrayList<>();
                unfinishedTickets.forEach(unfinishedTicket -> {
                    String info = String.format("Issue name: %s, Due date: %s", 
                        unfinishedTicket[0].toString(), 
                        unfinishedTicket[1].toString());

                    unfinishedTicketInfo.add(info);
                });

                System.out.println("Sending the email"); 

                // send the email
                final String EMAIL = "dragonoath123@gmail.com";
                try {
                    emailService.sendUnfinishedJiraIssuePastDueDateEmailNotification(
                        EMAIL,
                        user.getJiraUsername(), 
                        projectName, 
                        unfinishedTicketCount, 
                        unfinishedTicketInfo,
                        ISSUE_LIMIT);
                } catch (MessagingException e) {
                    log.error(e.getMessage(), e);
                    e.printStackTrace();
                }
            });
    }
}
