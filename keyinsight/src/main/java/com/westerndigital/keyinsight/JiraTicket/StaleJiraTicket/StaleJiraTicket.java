package com.westerndigital.keyinsight.JiraTicket.StaleJiraTicket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class StaleJiraTicket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
