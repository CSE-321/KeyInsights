package com.westerndigital.keyinsight.JavaIssue;

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
@Table(name = "Issues")
@Getter
@Setter
public class JavaIssue {
    @Id
    private Long id;
    private String name;
    private String project_name;
    private String team_type;
    private String status;
    private String creation_date;
    private String creation_time;
    private String updated_date;
    private String updated_time;
    private String due_date;
    private String due_time;
    private Float story_point;
    private String sub_type;
    private String priority;
    private String resolution;
    private String assignee;
    private String assignee_avatar_url;

    // public JavaIssue(Long id, String issue_name, String project_name, String team_type, String sub_type, Float story_point,
    //         String priority, String resolution, String status, String creationDate, String creationTime) {
    //     this.id = id;
    //     this.issue_name = issue_name;
    //     this.project_name = project_name;
    //     this.team_type = team_type;
    //     this.sub_type = sub_type;
    //     this.story_point = story_point;
    //     this.priority = priority;
    //     this.resolution = resolution;
    //     this.status = status;
    //     this.creationDate = creationDate;
    //     this.creationTime = creationTime;
    // }

    public JavaIssue() {

    }

}
