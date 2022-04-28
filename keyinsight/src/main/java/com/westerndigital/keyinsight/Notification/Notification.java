package com.westerndigital.keyinsight.Notification;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.westerndigital.keyinsight.JiraUser.JiraUser;
import com.westerndigital.keyinsight.Notification.Settings
    .ProjectDigestReportSetting;
import com.westerndigital.keyinsight.Notification.Settings.SprintStatusSetting;
import com.westerndigital.keyinsight.Notification.Settings.TicketStatusSetting;
import com.westerndigital.keyinsight.Notification.Settings
    .UnfinishedTicketSetting;
import com.westerndigital.keyinsight.Notification.Settings
    .WorkloadDigestReportSetting;

import lombok.Data;

@Entity
@Data
@Table(name = "notification")
public class Notification {
    
    @Id
    @SequenceGenerator(
        name = "notification_sequence",
        sequenceName = "notification_sequence",
        allocationSize = 1)
    @GeneratedValue(
        generator = "notification_sequence",
        strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private JiraUser jiraUser;

    private String serverUrl;
    private String projectName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private TicketStatusSetting ticketStatusSetting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private SprintStatusSetting sprintStatusSetting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private UnfinishedTicketSetting unfinishedTicketSetting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private ProjectDigestReportSetting projectDigestReportSetting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private WorkloadDigestReportSetting workloadDigestReportSetting;

    public Notification(JiraUser jiraUser, String serverUrl, 
        String projectName) {

        this.jiraUser = jiraUser;
        this.serverUrl = serverUrl;
        this.projectName = projectName;

        ticketStatusSetting = new TicketStatusSetting();
        sprintStatusSetting = new SprintStatusSetting();
        unfinishedTicketSetting = new UnfinishedTicketSetting();
        projectDigestReportSetting = new ProjectDigestReportSetting();
        workloadDigestReportSetting = new WorkloadDigestReportSetting();
    }
}
