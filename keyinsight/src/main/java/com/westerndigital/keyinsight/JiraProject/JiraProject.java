package com.westerndigital.keyinsight.JiraProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Projects")
@Getter
@Setter
public class JiraProject {

    @Id
    @SequenceGenerator(name = "project_sequence", sequenceName = "project_sequence", allocationSize = 1)
    @GeneratedValue(generator = "project_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String teamLead;
    private String teamLeadAvatarUrl;
    private OffsetDateTime createdDate; // uses the create date of the first issue
    private Integer numIssues;

    public JiraProject() {

    }

}