package com.westerndigital.keyinsight.SpringEmail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

