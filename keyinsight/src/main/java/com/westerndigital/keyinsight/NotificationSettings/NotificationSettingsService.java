package com.westerndigital.keyinsight.NotificationSettings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NotificationSettingsService {
    @Autowired
    private NotificationSettingsRepository notificationSettingsRepository;

    public List<NotificationSettings> getAllNotifications(){
        return notificationSettingsRepository.findAll();
    }
}
