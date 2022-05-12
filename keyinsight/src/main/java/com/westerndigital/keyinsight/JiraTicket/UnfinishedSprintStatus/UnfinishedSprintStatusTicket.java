package com.westerndigital.keyinsight.JiraTicket.UnfinishedSprintStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UnfinishedSprintStatusTicket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
