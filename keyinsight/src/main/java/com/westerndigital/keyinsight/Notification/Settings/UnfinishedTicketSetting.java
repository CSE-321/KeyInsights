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
@Table(name = "unfinished_ticket_setting")
public class UnfinishedTicketSetting {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Notification notification;

    private boolean notifyUser = false;

    public UnfinishedTicketSetting() {}

    public UnfinishedTicketSetting(boolean notifyUser) {
        this.notifyUser = notifyUser;
    }
}
