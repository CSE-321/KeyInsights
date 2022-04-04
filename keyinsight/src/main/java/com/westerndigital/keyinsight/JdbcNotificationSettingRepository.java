package com.westerndigital.keyinsight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.westerndigital.keyinsight.NotificationSetting;

@Repository
public class JdbcNotificationSettingRepository implements 
    NotificationSettingRepository {
    
    private JdbcTemplate jdbc;    

    @Autowired
    public JdbcNotificationSettingRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    };

    @Override
    public Iterable<NotificationSetting> getAll() {

        return null;
    }
    
}