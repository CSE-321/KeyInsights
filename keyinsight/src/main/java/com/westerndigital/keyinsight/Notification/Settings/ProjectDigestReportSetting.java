package com.westerndigital.keyinsight.Notification.Settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.westerndigital.keyinsight.Notification.Notification;

import lombok.Data;

@Data
@Entity
@Table(name = "project_digest_report_setting")
public class ProjectDigestReportSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Notification notification;

    private boolean notifyUser = false;
    private int notifyFrequency = 0;
    
    public ProjectDigestReportSetting(boolean notifyUser,
        int notifyFrequency) {
        
        this.notifyUser = notifyUser;
        this.notifyFrequency = notifyFrequency;
    }
}
