package com.westerndigital.keyinsight.JiraProject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

import java.time.OffsetDateTime;
@Entity
@Table(name = "Projects")
@Data
@ToString
public class JiraProject {

    @Id
    private String id;
    private String name;
    private String teamLead;
    private String teamLeadAvatarUrl;
    private String projectType;
    private Integer numIssues;
    private OffsetDateTime createdDate;
}