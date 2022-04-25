package com.westerndigital.keyinsight.SpringEmail;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Properties;

public class SpringEmail {
    @Bean
    public MailSender getMailSender(){
        MailSenderImp mailSender = new MailSenderImp;
        mailSender.setHost();
        mailSender.setPort(587);

        mailSender.setUserName(my.gmail@gmail.com);
        mailSender.setPassword();

        Properties propts = mailSender.getJavaMailProperties();
        propts.put("mail.transport.protocol", "smtp");
        propts.put("mail.smtp.auth", "true");
        propts.put("mail.smtp.starttls.enable", "true");
        propts.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateSimpleMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("This is a template message:\n%s\n");
        return message;
    }

}
