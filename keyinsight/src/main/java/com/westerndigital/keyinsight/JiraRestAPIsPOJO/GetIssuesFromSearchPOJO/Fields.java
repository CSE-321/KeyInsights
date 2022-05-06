package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetAllProjectsPOJO.ProjectJson;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO.Versions;

public class Fields {
    private IssueType issuetype;
    private ProjectJson project;
    private Resolution resolution;
    private OffsetDateTime resolutiondate;
    private OffsetDateTime created;
    private Priority priority;
    //story points
    @JsonProperty("customfield_10618")
    private Double storypoints;
    private Assignee assignee;
    private OffsetDateTime updated;
    private Status status;
    private String description;
    //subtype
    @JsonProperty("customfield_10451")
    private Type subtype;
    //type
    @JsonProperty("customfield_12628")
    private Type secondtype;
    private String summary;
    private Creator creator;
    private LocalDate duedate;

    public Fields() {
    }

    public IssueType getIssuetype() {
        return this.issuetype;
    }

    public void setIssuetype(IssueType issuetype) {
        this.issuetype = issuetype;
    }

    public ProjectJson getProject() {
        return this.project;
    }

    public void setProject(ProjectJson project) {
        this.project = project;
    }

    public Resolution getResolution() {
        return this.resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public OffsetDateTime getResolutiondate() {
        return this.resolutiondate;
    }

    public void setResolutiondate(OffsetDateTime resolutiondate) {
        this.resolutiondate = resolutiondate;
    }

    public OffsetDateTime getCreated() {
        return this.created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Double getStorypoints() {
        return this.storypoints;
    }

    public void setStorypoints(Double storypoints) {
        this.storypoints = storypoints;
    }

    public Assignee getAssignee() {
        return this.assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public OffsetDateTime getUpdated() {
        return this.updated;
    }

    public void setUpdated(OffsetDateTime updated) {
        this.updated = updated;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getSubtype() {
        return this.subtype;
    }

    public void setSubtype(Type subtype) {
        this.subtype = subtype;
    }

    public Type getSecondtype() {
        return this.secondtype;
    }

    public void setSecondtype(Type secondtype) {
        this.secondtype = secondtype;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Creator getCreator() {
        return this.creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public LocalDate getDuedate() {
        return this.duedate;
    }

    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }

    @Override
    public String toString() {
        return "{" +
            " issuetype='" + getIssuetype() + "'" +
            ", project='" + getProject() + "'" +
            ", resolution='" + getResolution() + "'" +
            ", resolutiondate='" + getResolutiondate() + "'" +
            ", created='" + getCreated() + "'" +
            ", priority='" + getPriority() + "'" +
            ", storypoints='" + getStorypoints() + "'" +
            ", assignee='" + getAssignee() + "'" +
            ", updated='" + getUpdated() + "'" +
            ", status='" + getStatus() + "'" +
            ", description='" + getDescription() + "'" +
            ", subtype='" + getSubtype() + "'" +
            ", secondtype='" + getSecondtype() + "'" +
            ", summary='" + getSummary() + "'" +
            ", creator='" + getCreator() + "'" +
            ", duedate='" + getDuedate() + "'" +
            "}";
    }
}
