package com.westerndigital.keyinsight.NotificationSettings;

import com.westerndigital.keyinsight.NotificationSettings.NotificationSettings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationSettingsRepository 
    extends JpaRepository<NotificationSettings, Long> {
}