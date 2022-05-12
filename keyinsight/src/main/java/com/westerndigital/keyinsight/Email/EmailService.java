package com.westerndigital.keyinsight.Email;

import java.util.List;

import javax.mail.MessagingException;

public interface EmailService {

    // send email



    void sendSimpleMessage(String to, String subject, String text);
    
    void sendMessageWithAttatchment(String to, String subject, String text, String pathToAttatchment);

    void sendEmailNotification(List<String> nameOfIssues) throws MessagingException;
    void sendNotificationUnfinished(String to, int numberofissues, String name, String projectName, List<String> nameofissues);
}
