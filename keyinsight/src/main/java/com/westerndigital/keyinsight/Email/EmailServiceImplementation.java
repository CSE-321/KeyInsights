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

    //https://www.baeldung.com/spring-email
    //https://www.thymeleaf.org/doc/articles/springmail.html
    //https://javabydeveloper.com/spring-boot-email-template/
    public void sendUnfinishedJiraIssuePastDueDateEmailNotification(String to, String name, String projectName, Integer issueCount, List<String> nameOfIssues, Integer limitCount) throws MessagingException {
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

    public void sendCriticalJiraIssueNotUpdatedEmailNotification(String to, String name, String projectName, Integer interval, Integer issueCount, List<String> nameOfIssues, Integer limitCount) throws MessagingException {
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
}

