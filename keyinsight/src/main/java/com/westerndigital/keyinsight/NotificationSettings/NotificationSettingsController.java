package com.westerndigital.keyinsight.NotificationSettings;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.orm.jpa.*;

import java.util.List;

//@Controller

@RequestMapping("/api/v1/NotificationSettings")

public class NotificationSettingsController {
    @Autowired
    private NotificationSettingsService notificationSettingsService;

    public NotificationSettingsController(NotificationSettingsService notificationSettingsService){
        this.notificationSettingsService = notificationSettingsService;
    }
    @GetMapping
    public ResponseEntity<List<NotificationSettings>> getAllNotifications(){
        return new ResponseEntity<>(notificationSettingsService.getAllNotifications(), HttpStatus.OK);
    }
}