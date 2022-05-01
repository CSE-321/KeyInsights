package com.westerndigital.keyinsight.SpringEmail;

public interface SpringEmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMessageWithAttatchment(String to, String subject, String text, String pathToAttatchment);
    //void sendEmailNotification();
    void sendNotificationUnfinished(String to, int numberofissues, String name, String projectName);
}
