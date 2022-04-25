package com.westerndigital.keyinsight.NotificationSettings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
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
    private String serverURL;
    private String userName;
    private String projectName;
    private Boolean toggleSwitch1;
    private Integer toggleSwitch1Val;
    private Boolean toggleSwitch2;
    private Integer toggleSwitch2Val;
    private Boolean toggleSwitch3;
    private Boolean toggleSwitch4;
    private Integer toggleSwitch4Val;
    private Boolean toggleSwitch5;
    private Integer toggleSwitch5Val;

}
