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
    private Double storyPoints;
    private Assignee assignee;
    private Status status;
    private String description;
    //subtype
    private Type customfield_10451;
    //type
    private Type customfield_12628;
    private String summary;
    private Creator creator;
    private LocalDate duedate;

    public Fields() {
    }

    public Fields(IssueType issuetype, ProjectJson project, Resolution resolution, OffsetDateTime resolutiondate, OffsetDateTime created, Priority priority, Double storyPoints, Assignee assignee, Status status, String description, Type customfield_12628, String summary, Creator creator, LocalDate duedate) {
        this.issuetype = issuetype;
        this.project = project;
        this.resolution = resolution;
        this.resolutiondate = resolutiondate;
        this.created = created;
        this.priority = priority;
        this.storyPoints = storyPoints;
        this.assignee = assignee;
        this.status = status;
        this.description = description;
        this.customfield_12628 = customfield_12628;
        this.summary = summary;
        this.creator = creator;
        this.duedate = duedate;
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

    public Double getstoryPoints() {
        return this.storyPoints;
    }

    public void setstoryPoints(Double storyPoints) {
        this.storyPoints = storyPoints;
    }

    public Assignee getAssignee() {
        return this.assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
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

    public Type getCustomfield_12628() {
        return this.customfield_12628;
    }

    public void setCustomfield_12628(Type customfield_12628) {
        this.customfield_12628 = customfield_12628;
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

    public Fields issuetype(IssueType issuetype) {
        setIssuetype(issuetype);
        return this;
    }

    public Fields project(ProjectJson project) {
        setProject(project);
        return this;
    }

    public Fields resolution(Resolution resolution) {
        setResolution(resolution);
        return this;
    }

    public Fields resolutiondate(OffsetDateTime resolutiondate) {
        setResolutiondate(resolutiondate);
        return this;
    }

    public Fields created(OffsetDateTime created) {
        setCreated(created);
        return this;
    }

    public Fields priority(Priority priority) {
        setPriority(priority);
        return this;
    }

    public Fields storyPoints(Double storyPoints) {
        setstoryPoints(storyPoints);
        return this;
    }

    public Fields assignee(Assignee assignee) {
        setAssignee(assignee);
        return this;
    }

    public Fields status(Status status) {
        setStatus(status);
        return this;
    }

    public Fields description(String description) {
        setDescription(description);
        return this;
    }

    public Fields customfield_12628(Type customfield_12628) {
        setCustomfield_12628(customfield_12628);
        return this;
    }

    public Fields summary(String summary) {
        setSummary(summary);
        return this;
    }

    public Fields creator(Creator creator) {
        setCreator(creator);
        return this;
    }

    public Fields duedate(LocalDate duedate) {
        setDuedate(duedate);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Fields)) {
            return false;
        }
        Fields fields = (Fields) o;
        return Objects.equals(issuetype, fields.issuetype) && Objects.equals(project, fields.project) && Objects.equals(resolution, fields.resolution) && Objects.equals(resolutiondate, fields.resolutiondate) && Objects.equals(created, fields.created) && Objects.equals(priority, fields.priority) && Objects.equals(storyPoints, fields.storyPoints) && Objects.equals(assignee, fields.assignee) && Objects.equals(status, fields.status) && Objects.equals(description, fields.description) && Objects.equals(customfield_12628, fields.customfield_12628) && Objects.equals(summary, fields.summary) && Objects.equals(creator, fields.creator) && Objects.equals(duedate, fields.duedate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issuetype, project, resolution, resolutiondate, created, priority, storyPoints, assignee, status, description, customfield_12628, summary, creator, duedate);
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
            ", storyPoints='" + getstoryPoints() + "'" +
            ", assignee='" + getAssignee() + "'" +
            ", status='" + getStatus() + "'" +
            ", description='" + getDescription() + "'" +
            ", customfield_12628='" + getCustomfield_12628() + "'" +
            ", summary='" + getSummary() + "'" +
            ", creator='" + getCreator() + "'" +
            ", duedate='" + getDuedate() + "'" +
            "}";
    }
}
