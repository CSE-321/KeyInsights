package com.westerndigital.keyinsight.SpringEmail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;

@Component
public class SpringEmailSender implements SpringEmailService{
    @Autowired
    public JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        
    }
    public void sendNotificationUnfinished(String to, int numberofissues, String name, String projectName){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Unfinished Jira Issues");
        message.setText("Hello " + name + ", " + "\n" +"\n" + "This email is to inform you that " + numberofissues + " Jira Issues from " + projectName + " are uncompleted or unresolved by their known due date." +"\n" +"\n" + "Best Regards," +"\n" +"\n" +"KeyInsights Team");
        mailSender.send(message);
    }
    // @Scheduled(initialDelay = 30 * 1000, fixedRate = 120000)
    // public void sendEmailNotification(){
    //     String to = "jrosales530@gmail.com" ;
    //     String subject = "Subjects";
    //     String text = "Hello Again";
    //     sendSimpleMessage(to, subject, text);
    //     System.out.print("sent message");
    // }
    @Override
    public void sendMessageWithAttatchment(String to, String subject, String text, String pathToAttatchment){
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttatchment));
            helper.addAttachment("Invoice", file);

            mailSender.send(message);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
           
            
        }

}

