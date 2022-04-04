package com.westerndigital.keyinsight;

import com.westerndigital.keyinsight.NotificationSetting;

public interface NotificationSettingRepository {
    
    // get all notification settings from the database
    Iterable<NotificationSetting> getAll();
}
