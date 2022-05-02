package com.westerndigital.keyinsight.Notification;

import java.util.List;

import com.westerndigital.keyinsight.JiraUser.JiraUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotificationSettings(JiraUser jiraUser) {
        return notificationRepository.findByJiraUser(jiraUser);
    }

    public List<Notification> getNotificationSettings() {
        return notificationRepository.findAll();
    }

    public void updateNotificationSettings(Notification notificationSettings) {
        notificationRepository.save(notificationSettings);
    }
}
