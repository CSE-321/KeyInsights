package com.westerndigital.keyinsight.NotificationSettings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NotificationSettings")
public class NotificationSettings {
    
    @Id
    @SequenceGenerator(
        name = "notification_setting_sequence",
        sequenceName = "notification_setting_sequence",
        allocationSize = 1)
    @GeneratedValue(
        generator = "notification_setting_sequence",
        strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long name;
}
