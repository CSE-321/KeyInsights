package com.westerndigital.keyinsight;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateDatabase {
    //https://stackoverflow.com/questions/42246301/spring-scheduled-task-does-not-start-on-application-startup

    @Scheduled(initialDelay = 0, fixedRate = 5000)
    public void scheduledWork() {
        System.out.println("Hello World!");
    }

}
