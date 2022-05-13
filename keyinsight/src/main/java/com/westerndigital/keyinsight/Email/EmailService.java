package com.westerndigital.keyinsight.Email;

import java.util.List;

import javax.mail.MessagingException;

import com.westerndigital.keyinsight.Email.DAOs.ProjectDigest;
import com.westerndigital.keyinsight.Email.DAOs.ResourceDigest;

public interface EmailService {

    // send email
    void sendUnfinishedJiraIssuePastDueDateEmailNotification(String to, String name, String projectName, Integer issueCount, List<String> nameOfIssues, Integer limitNumber) throws MessagingException;
    
    void sendCriticalJiraIssueNotUpdatedEmailNotification(String to, String name, String projectName, Integer interval, Integer issueCount, List<String> nameOfIssues, Integer limitCount) throws MessagingException;

    void sendResourceDigestNotification(String to, String name, String projectName, Integer interval, List<ResourceDigest> nameOfIssues) throws MessagingException;

    void sendProjectDigestNotification(String to, String name, String projectName, Integer interval, List<ProjectDigest> nameOfIssues) throws MessagingException;
}
