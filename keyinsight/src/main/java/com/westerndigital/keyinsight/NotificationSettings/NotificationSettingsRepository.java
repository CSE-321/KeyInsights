package com.westerndigital.keyinsight.NotificationSettings;

import com.westerndigital.keyinsight.NotificationSettings.NotificationSettings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationSettingsRepository 
    extends JpaRepository<NotificationSettings, Long> {
}
