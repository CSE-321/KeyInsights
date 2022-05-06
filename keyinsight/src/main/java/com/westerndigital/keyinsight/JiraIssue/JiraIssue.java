package com.westerndigital.keyinsight.JiraIssue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.OffsetDateTime;
@Entity
@Table(name = "Issues")

public class JiraIssue {
    @Id
    private String id;
    private Integer issueNumber;
    private String projectName;
    private String projectUniqueId;
    private String issueType;
    private String status;
    private OffsetDateTime createdDateTime;
    private OffsetDateTime updatedDateTime;
    private OffsetDateTime dueDateTime;
    private OffsetDateTime resolutionDateTime;
    private Double storyPoint;
    private String secondType;
    private String priority;
    private String resolution;
    private String assignee;
    private String assigneeAvatarUrl;

    public JiraIssue() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIssueNumber() {
        return this.issueNumber;
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectUniqueId() {
        return this.projectUniqueId;
    }

    public void setProjectUniqueId(String projectUniqueId) {
        this.projectUniqueId = projectUniqueId;
    }

    public String getIssueType() {
        return this.issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(OffsetDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public OffsetDateTime getUpdatedDateTime() {
        return this.updatedDateTime;
    }

    public void setUpdatedDateTime(OffsetDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public OffsetDateTime getDueDateTime() {
        return this.dueDateTime;
    }

    public void setDueDateTime(OffsetDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public OffsetDateTime getResolutionDateTime() {
        return this.resolutionDateTime;
    }

    public void setResolutionDateTime(OffsetDateTime resolutionDateTime) {
        this.resolutionDateTime = resolutionDateTime;
    }

    public Double getStoryPoint() {
        return this.storyPoint;
    }

    public void setStoryPoint(Double storyPoint) {
        this.storyPoint = storyPoint;
    }

    public String getSecondType() {
        return this.secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getResolution() {
        return this.resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getAssignee() {
        return this.assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssigneeAvatarUrl() {
        return this.assigneeAvatarUrl;
    }

    public void setAssigneeAvatarUrl(String assigneeAvatarUrl) {
        this.assigneeAvatarUrl = assigneeAvatarUrl;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", projectName='" + getProjectName() + "'" +
            ", projectUniqueId='" + getProjectUniqueId() + "'" +
            ", issueType='" + getIssueType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDateTime='" + getCreatedDateTime() + "'" +
            ", updatedDateTime='" + getUpdatedDateTime() + "'" +
            ", dueDateTime='" + getDueDateTime() + "'" +
            ", resolutionDateTime='" + getResolutionDateTime() + "'" +
            ", storyPoint='" + getStoryPoint() + "'" +
            ", secondType='" + getSecondType() + "'" +
            ", priority='" + getPriority() + "'" +
            ", resolution='" + getResolution() + "'" +
            ", assignee='" + getAssignee() + "'" +
            ", assigneeAvatarUrl='" + getAssigneeAvatarUrl() + "'" +
            "}";
    }

    
}
