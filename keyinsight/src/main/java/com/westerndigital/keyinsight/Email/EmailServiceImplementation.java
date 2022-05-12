package com.westerndigital.keyinsight.Email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Component
public class EmailServiceImplementation implements EmailService{
    @Autowired
    public JavaMailSender mailSender;

    @Autowired
    public TemplateEngine templateEngine;

    public void sendSimpleMessage(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        
    }
    public void sendNotificationUnfinished(String to, int numberofissues, String name, String projectName, List<String> nameofissues){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Unfinished Jira Issues");
        message.setText("Hello " + name + ", " + "\n" +"\n" + "During a daily scan of  " + projectName + "'s database, we noticed that the following " + numberofissues + " issues are incomplete: " + nameofissues + " Please be aware of these issues and complete them by their assigned date." +"\n" +"\n" + "Best Regards," +"\n" +"\n" +"KeySight Team");
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

    //https://www.thymeleaf.org/doc/articles/springmail.html
    //https://javabydeveloper.com/spring-boot-email-template/
    public void sendUnfinishedJiraIssuePastDueDateEmailNotification(String to, String name, String projectName, Integer issueCount, Integer limitCount, List<String> nameOfIssues) throws MessagingException {
        Context context = new Context();
        context.setVariable("userName", name);
        context.setVariable("projectName", projectName);
        context.setVariable("issueCount", issueCount);
        context.setVariable("limitCount", limitCount);
        context.setVariable("listOfIssueNames", nameOfIssues);
        context.setVariable("westernDigitalLogo", "westernDigitalLogo");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        String process = templateEngine.process("emails/html/unfinishedIssuesNotification.html", context);

        helper.setSubject("Unfinished Jira Issues from " + projectName);
        helper.setText(process, true);
        helper.setTo(to);

        ClassPathResource westernDigitalLogoLocation = new ClassPathResource("templates/emails/images/westerndigitallogosmall.png");
        helper.addInline("westernDigitalLogo", westernDigitalLogoLocation);
        mailSender.send(message);
    }

    public void sendCriticalJiraIssueNotUpdatedEmailNotification(String to, String name, String projectName, Integer interval, Integer issueCount, Integer limitCount, List<String> nameOfIssues) throws MessagingException {
        Context context = new Context();
        context.setVariable("userName", name);
        context.setVariable("projectName", projectName);
        context.setVariable("issueCount", issueCount);
        context.setVariable("limitCount", limitCount);
        context.setVariable("listOfIssueNames", nameOfIssues);
        context.setVariable("interval", interval);
        context.setVariable("westernDigitalLogo", "westernDigitalLogo");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        String process = templateEngine.process("emails/html/criticalIssuesNotUpdatedNotification.html", context);

        helper.setSubject("Critical Jira Issues Not Updated from " + projectName);
        helper.setText(process, true);
        helper.setTo(to);

        ClassPathResource westernDigitalLogoLocation = new ClassPathResource("templates/emails/images/westerndigitallogosmall.png");
        helper.addInline("westernDigitalLogo", westernDigitalLogoLocation);
        mailSender.send(message);
    }



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

