package com.westerndigital.keyinsight.Email;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;

public interface EmailService {




    void sendSimpleMessage(String to, String subject, String text);
    
    void sendMessageWithAttatchment(String to, String subject, String text, String pathToAttatchment);

    //void sendEmailNotification();
    void sendNotificationUnfinished(String to, int numberofissues, String name, String projectName);
}
