package com.westerndigital.keyinsight.JiraIssue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Table(name = "Issues")
@Getter
@Setter
public class JiraIssue {
    @Id
    private Integer id;
    private String name;
    private String projectName;
    private String teamType;
    private String status;
    private String creationDate;
    private String creationTime;
    private String updatedDate;
    private String updatedTime;
    private String dueDate;
    private String dueTime;
    private Float storyPoint;
    private String subType;
    private String priority;
    private String resolution;
    private String assignee;
    private String assigneeAvatarUrl;

    // public JavaIssue(Long id, String issue_name, String project_name, String
    // team_type, String sub_type, Float story_point,
    // String priority, String resolution, String status, String creationDate,
    // String creationTime) {
    // this.id = id;
    // this.issue_name = issue_name;
    // this.project_name = project_name;
    // this.team_type = team_type;
    // this.sub_type = sub_type;
    // this.story_point = story_point;
    // this.priority = priority;
    // this.resolution = resolution;
    // this.status = status;
    // this.creationDate = creationDate;
    // this.creationTime = creationTime;
    // }

    public JiraIssue() {

    }

}
