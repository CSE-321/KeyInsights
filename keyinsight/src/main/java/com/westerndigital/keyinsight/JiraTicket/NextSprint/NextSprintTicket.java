package com.westerndigital.keyinsight.JiraTicket.NextSprint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class NextSprintTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
