package com.westerndigital.keyinsight.Email;

import java.util.List;

import javax.mail.MessagingException;

public interface EmailService {

    // send email
    void sendSimpleMessage(String to, String subject, String text);
    
    void sendMessageWithAttatchment(String to, String subject, String text, String pathToAttatchment);

    void sendUnfinishedJiraIssuePastDueDateEmailNotification(String to, String name, String projectName, Integer issueCount, Integer limitNumber, List<String> nameOfIssues) throws MessagingException;
    
    void sendCriticalJiraIssueNotUpdatedEmailNotification(String to, String name, String projectName, Integer interval, Integer issueCount, Integer limitCount, List<String> nameOfIssues) throws MessagingException;

    void sendNotificationUnfinished(String to, int numberofissues, String name, String projectName, List<String> nameofissues);
}