package com.westerndigital.keyinsight.JiraProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
    private String team_lead;
    private String team_lead_avatar_url;
    private String created_at; // uses the create date of the first issue
    private Integer num_issues;
    private String category; // not 100% sure

    // public JavaProject(String name, String team_lead, String team_lead_url,
    // String created_at, Integer num_issues) {
    // this.name = name;
    // this.team_lead = team_lead;
    // this.team_lead_avatar_url = team_lead_avatar_url;
    // // this.created_at = created_at;
    // this.num_issues = num_issues;
    // }

    public JiraProject() {

    }

}