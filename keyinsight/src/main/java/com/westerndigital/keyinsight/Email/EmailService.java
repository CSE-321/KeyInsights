package com.westerndigital.keyinsight.Email;

import java.util.List;

public interface EmailService {

    // send email



    void sendSimpleMessage(String to, String subject, String text);
    
    void sendMessageWithAttatchment(String to, String subject, String text, String pathToAttatchment);

    //void sendEmailNotification();
    void sendNotificationUnfinished(String to, int numberofissues, String name, String projectName, List<Object> nameofissues);
}
