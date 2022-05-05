package com.westerndigital.keyinsight.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


// public class SpringEmailNotification extends SpringEmailSender {
//     @Scheduled(fixedRate = 120000)
//     @Async
//     public void emailNotification(String to, String subject, String text){
//         sendSimpleMessage(to, subject, text);
//     }

// }
