package com.westerndigital.keyinsight.Issue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GenerationType;

@ToString
@Entity
@Table
@Getter
@Setter
public class Issue {
    @Id
    @SequenceGenerator(name = "issue_sequence", sequenceName = "issue_sequence", allocationSize = 1)
    @GeneratedValue(generator = "issue_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String issue_id;

    @Column(nullable = false)
    private String project_id;

    @Column(nullable = false)
    private String team_type;

    @Column(nullable = false)
    private String sub_type;

    @Column(nullable = false)
    private Float story_point;

    @Column(nullable = false)
    private String priority;

    @Column(nullable = false)
    private String resolution;

    @Column(nullable = false)
    private String status;

    public Issue(String issue_id, String project_id, String team_type, String sub_type, Float story_point,
            String priority, String resolution, String status) {
        this.issue_id = issue_id;
        this.project_id = project_id;
        this.team_type = team_type;
        this.sub_type = sub_type;
        this.story_point = story_point;
        this.priority = priority;
        this.resolution = resolution;
        this.status = status;
    }

}
