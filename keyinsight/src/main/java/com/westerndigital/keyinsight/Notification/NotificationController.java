package com.westerndigital.keyinsight.Notification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications(){
        return new ResponseEntity<>(notificationService.getAllNotifications(), HttpStatus.OK);
    }
}
