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
import javax.persistence.Transient;
import javax.transaction.Transactional;

import com.westerndigital.keyinsight.JiraUser.JiraUser;
import com.westerndigital.keyinsight.Notification.Settings
    .ProjectDigestReportSetting;
import com.westerndigital.keyinsight.Notification.Settings.SprintStatusSetting;
import com.westerndigital.keyinsight.Notification.Settings.TicketStatusSetting;
import com.westerndigital.keyinsight.Notification.Settings
    .UnfinishedTicketSetting;
import com.westerndigital.keyinsight.Notification.Settings
    .WorkloadDigestReportSetting;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Table(name = "notification")
@Transactional
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

    // remove the auto generated getter otherwise there will be a loop in the
    // query result
    // @Getter(value = AccessLevel.NONE)
    // @OneToOne
    // private JiraUser jiraUser;

    private String jiraUsername;
    private String email;
    private String serverUrl;
    private String projectName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_status_setting_id")
    private TicketStatusSetting ticketStatusSetting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sprint_status_setting_id")
    private SprintStatusSetting sprintStatusSetting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unfinished_ticket_setting_id")
    private UnfinishedTicketSetting unfinishedTicketSetting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_digest_report_setting_id")
    private ProjectDigestReportSetting projectDigestReportSetting;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workload_digest_report_setting_id")
    private WorkloadDigestReportSetting workloadDigestReportSetting;

    public Notification() {}

    public Notification(String jiraUsername, String email, String serverUrl, 
        String projectName) {

        this.jiraUsername = jiraUsername;
        this.email = email;
        this.serverUrl = serverUrl;
        this.projectName = projectName;

        // initialize the default values for each of the settings
        ticketStatusSetting = new TicketStatusSetting();
        sprintStatusSetting = new SprintStatusSetting();
        unfinishedTicketSetting = new UnfinishedTicketSetting();
        projectDigestReportSetting = new ProjectDigestReportSetting();
        workloadDigestReportSetting = new WorkloadDigestReportSetting();
    }

    public Notification(String jiraUsername, String email, String serverUrl, 
        String projectName,
        TicketStatusSetting ticketStatusSetting,
        SprintStatusSetting sprintStatusSetting,
        UnfinishedTicketSetting unfinishedTicketSetting,
        ProjectDigestReportSetting projectDigestReportSetting,
        WorkloadDigestReportSetting workloadDigestReportSetting) {

        this.jiraUsername = jiraUsername;
        this.email = email;
        this.serverUrl = serverUrl;
        this.projectName = projectName;
        this.ticketStatusSetting = ticketStatusSetting;
        this.sprintStatusSetting = sprintStatusSetting;
        this.unfinishedTicketSetting = unfinishedTicketSetting;
        this.projectDigestReportSetting = projectDigestReportSetting;
        this.workloadDigestReportSetting = workloadDigestReportSetting;
    }
}
