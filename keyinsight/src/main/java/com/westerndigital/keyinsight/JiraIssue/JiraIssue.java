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

    public JiraIssue(Integer id, String name, String project_name, String team_type, String status, String creation_date, String creation_time, String updated_date, String updated_time, String due_date, String due_time, Float story_point, String sub_type, String priority, String resolution, String assignee, String assignee_avatar_url) {
        this.id = id;
        this.name = name;
        this.project_name = project_name;
        this.team_type = team_type;
        this.status = status;
        this.creation_date = creation_date;
        this.creation_time = creation_time;
        this.updated_date = updated_date;
        this.updated_time = updated_time;
        this.due_date = due_date;
        this.due_time = due_time;
        this.story_point = story_point;
        this.sub_type = sub_type;
        this.priority = priority;
        this.resolution = resolution;
        this.assignee = assignee;
        this.assignee_avatar_url = assignee_avatar_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getTeam_type() {
        return team_type;
    }

    public void setTeam_type(String team_type) {
        this.team_type = team_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(String creation_time) {
        this.creation_time = creation_time;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getDue_time() {
        return due_time;
    }

    public void setDue_time(String due_time) {
        this.due_time = due_time;
    }

    public Float getStory_point() {
        return story_point;
    }

    public void setStory_point(Float story_point) {
        this.story_point = story_point;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssignee_avatar_url() {
        return assignee_avatar_url;
    }

    public void setAssignee_avatar_url(String assignee_avatar_url) {
        this.assignee_avatar_url = assignee_avatar_url;
    }

    

}
