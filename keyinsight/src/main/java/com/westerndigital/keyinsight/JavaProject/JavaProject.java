package com.westerndigital.keyinsight.JavaProject;

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
public class JavaProject {

    @Id
    @SequenceGenerator(name = "project_sequence", sequenceName = "project_sequence", allocationSize = 1)
    @GeneratedValue(generator = "project_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String team_lead;
    private String team_lead_avatar_url;
    private String created_at;
    private Integer num_issues;

    // public JavaProject(String name, String team_lead, String team_lead_url, String created_at, Integer num_issues) {
    //     this.name = name;
    //     this.team_lead = team_lead;
    //     this.team_lead_avatar_url = team_lead_avatar_url;
    //     // this.created_at = created_at;
    //     this.num_issues = num_issues;
    // }

    public JavaProject() {

    }

}