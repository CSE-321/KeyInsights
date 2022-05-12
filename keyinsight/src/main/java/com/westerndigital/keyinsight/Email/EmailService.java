package com.westerndigital.keyinsight.Email;

import java.util.List;

import javax.mail.MessagingException;

public interface EmailService {

    // send email
    void sendUnfinishedJiraIssuePastDueDateEmailNotification(String to, String name, String projectName, Integer issueCount, List<String> nameOfIssues, Integer limitNumber) throws MessagingException;
    
    void sendCriticalJiraIssueNotUpdatedEmailNotification(String to, String name, String projectName, Integer interval, Integer issueCount, List<String> nameOfIssues, Integer limitCount) throws MessagingException;

}
