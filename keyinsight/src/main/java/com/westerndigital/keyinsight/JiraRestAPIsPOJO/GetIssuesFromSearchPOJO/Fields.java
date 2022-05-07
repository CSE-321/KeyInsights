package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetAllProjectsPOJO.ProjectJson;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO.Versions;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleUser.UserJson;

public class Fields {
    private IssueType issuetype;
    private Parent parent;
    private String timespent;
    @JsonProperty("customfield_12010")
    private String development;
    private ProjectJson project;
    private List<Versions> fixVersions;
    private String aggregatetimespent;
    private Resolution resolution;
    @JsonProperty("customfield_11711")
    private Object caughtby;
    @JsonProperty("customfield_12844")
    private String riskconsequence;
    @JsonProperty("customfield_12843")
    private String riskprobability;
    private OffsetDateTime resolutiondate;
    @JsonProperty("customfield_12849")
    private Object verificationscope;
    private Integer workratio;
    private OffsetDateTime lastViewed;
    private Watches watches;
    @JsonProperty("customfield_13010")
    private List<UserJson> watchers;
    private OffsetDateTime created;
    private Priority priority;
    @JsonProperty("customfield_12840")
    private OffsetDateTime baselineenddate;
    @JsonProperty("customfield_12842")
    private Integer taskprogress;
    private List<String> labels;
    @JsonProperty("customfield_12841")
    private String taskmode;
    @JsonProperty("customfield_10610")
    private String sprint;
    @JsonProperty("customfield_10611")
    private String epiclink;
    @JsonProperty("customfield_10612")
    private String rankobsolete;
    @JsonProperty("customfield_12837")
    private LocalDate startdatesoftwareplant;
    private String timeestimate;
    private String aggregatetimeoriginalestimate;
    private List[] versions;
    @JsonProperty("customfield_12839")
    private OffsetDateTime baselinestartdate;
    @JsonProperty("customfield_12838")
    private LocalDate enddate;
    @JsonProperty("customfield_10618")
    private Double storypoints;
    private List<Issuelinks> issuelinks;
    private Assignee assignee;
    private OffsetDateTime updated;
    private Status status;
    private List[] components;
    private String timeoriginalestimate;
    private String description;
    @JsonProperty("customfield_10451")
    private Type subtype;
    @JsonProperty("customfield_10453")
    private LocalDate startdate;
    @JsonProperty("customfield_11810")
    private String jirarelatedtopreviousproject;
    @JsonProperty("customfield_12628")
    private Type secondtype;
    private String aggregatetimeestimate;
    private String summary;
    private Creator creator;
    private List<Issues> subtasks;
    private UserJson reporter;
    private Progress aggregateprogress;
    @JsonProperty("customfield_11410")
    private String rank;
    private String environment;
    private LocalDate duedate;
    @JsonProperty("customfield_10715")
    private String releaseversionhistory;
    private Progress progress;
    private Votes votes;

    public IssueType getIssuetype() {
        return this.issuetype;
    }

    public void setIssuetype(IssueType issuetype) {
        this.issuetype = issuetype;
    }

    public Parent getParent() {
        return this.parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getTimespent() {
        return this.timespent;
    }

    public void setTimespent(String timespent) {
        this.timespent = timespent;
    }

    public String getDevelopment() {
        return this.development;
    }

    public void setDevelopment(String development) {
        this.development = development;
    }

    public ProjectJson getProject() {
        return this.project;
    }

    public void setProject(ProjectJson project) {
        this.project = project;
    }

    public List<Versions> getFixVersions() {
        return this.fixVersions;
    }

    public void setFixVersions(List<Versions> fixVersions) {
        this.fixVersions = fixVersions;
    }

    public String getAggregatetimespent() {
        return this.aggregatetimespent;
    }

    public void setAggregatetimespent(String aggregatetimespent) {
        this.aggregatetimespent = aggregatetimespent;
    }

    public Resolution getResolution() {
        return this.resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public Object getCaughtby() {
        return this.caughtby;
    }

    public void setCaughtby(Object caughtby) {
        this.caughtby = caughtby;
    }

    public String getRiskconsequence() {
        return this.riskconsequence;
    }

    public void setRiskconsequence(String riskconsequence) {
        this.riskconsequence = riskconsequence;
    }

    public String getRiskprobability() {
        return this.riskprobability;
    }

    public void setRiskprobability(String riskprobability) {
        this.riskprobability = riskprobability;
    }

    public OffsetDateTime getResolutiondate() {
        return this.resolutiondate;
    }

    public void setResolutiondate(OffsetDateTime resolutiondate) {
        this.resolutiondate = resolutiondate;
    }

    public Object getVerificationscope() {
        return this.verificationscope;
    }

    public void setVerificationscope(Object verificationscope) {
        this.verificationscope = verificationscope;
    }

    public Integer getWorkratio() {
        return this.workratio;
    }

    public void setWorkratio(Integer workratio) {
        this.workratio = workratio;
    }

    public OffsetDateTime getLastViewed() {
        return this.lastViewed;
    }

    public void setLastViewed(OffsetDateTime lastViewed) {
        this.lastViewed = lastViewed;
    }

    public Watches getWatches() {
        return this.watches;
    }

    public void setWatches(Watches watches) {
        this.watches = watches;
    }

    public List<UserJson> getWatchers() {
        return this.watchers;
    }

    public void setWatchers(List<UserJson> watchers) {
        this.watchers = watchers;
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

    public OffsetDateTime getBaselineenddate() {
        return this.baselineenddate;
    }

    public void setBaselineenddate(OffsetDateTime baselineenddate) {
        this.baselineenddate = baselineenddate;
    }

    public Integer getTaskprogress() {
        return this.taskprogress;
    }

    public void setTaskprogress(Integer taskprogress) {
        this.taskprogress = taskprogress;
    }

    public List<String> getLabels() {
        return this.labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getTaskmode() {
        return this.taskmode;
    }

    public void setTaskmode(String taskmode) {
        this.taskmode = taskmode;
    }

    public String getSprint() {
        return this.sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getEpiclink() {
        return this.epiclink;
    }

    public void setEpiclink(String epiclink) {
        this.epiclink = epiclink;
    }

    public String getRankobsolete() {
        return this.rankobsolete;
    }

    public void setRankobsolete(String rankobsolete) {
        this.rankobsolete = rankobsolete;
    }

    public LocalDate getStartdatesoftwareplant() {
        return this.startdatesoftwareplant;
    }

    public void setStartdatesoftwareplant(LocalDate startdatesoftwareplant) {
        this.startdatesoftwareplant = startdatesoftwareplant;
    }

    public String getTimeestimate() {
        return this.timeestimate;
    }

    public void setTimeestimate(String timeestimate) {
        this.timeestimate = timeestimate;
    }

    public String getAggregatetimeoriginalestimate() {
        return this.aggregatetimeoriginalestimate;
    }

    public void setAggregatetimeoriginalestimate(String aggregatetimeoriginalestimate) {
        this.aggregatetimeoriginalestimate = aggregatetimeoriginalestimate;
    }

    public List[] getVersions() {
        return this.versions;
    }

    public void setVersions(List[] versions) {
        this.versions = versions;
    }

    public OffsetDateTime getBaselinestartdate() {
        return this.baselinestartdate;
    }

    public void setBaselinestartdate(OffsetDateTime baselinestartdate) {
        this.baselinestartdate = baselinestartdate;
    }

    public LocalDate getEnddate() {
        return this.enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public Double getStorypoints() {
        return this.storypoints;
    }

    public void setStorypoints(Double storypoints) {
        this.storypoints = storypoints;
    }

    public List<Issuelinks> getIssuelinks() {
        return this.issuelinks;
    }

    public void setIssuelinks(List<Issuelinks> issuelinks) {
        this.issuelinks = issuelinks;
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

    public List[] getComponents() {
        return this.components;
    }

    public void setComponents(List[] components) {
        this.components = components;
    }

    public String getTimeoriginalestimate() {
        return this.timeoriginalestimate;
    }

    public void setTimeoriginalestimate(String timeoriginalestimate) {
        this.timeoriginalestimate = timeoriginalestimate;
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

    public LocalDate getStartdate() {
        return this.startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public String getJirarelatedtopreviousproject() {
        return this.jirarelatedtopreviousproject;
    }

    public void setJirarelatedtopreviousproject(String jirarelatedtopreviousproject) {
        this.jirarelatedtopreviousproject = jirarelatedtopreviousproject;
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

    public String getAggregatetimeestimate() {
        return this.aggregatetimeestimate;
    }

    public void setAggregatetimeestimate(String aggregatetimeestimate) {
        this.aggregatetimeestimate = aggregatetimeestimate;
    }

    public List<Issues> getSubtasks() {
        return this.subtasks;
    }

    public void setSubtasks(List<Issues>subtasks) {
        this.subtasks = subtasks;
    }

    public UserJson getReporter() {
        return this.reporter;
    }

    public void setReporter(UserJson reporter) {
        this.reporter = reporter;
    }

    public Progress getAggregateprogress() {
        return this.aggregateprogress;
    }

    public void setAggregateprogress(Progress aggregateprogress) {
        this.aggregateprogress = aggregateprogress;
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public LocalDate getDuedate() {
        return this.duedate;
    }

    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }

    public String getReleaseversionhistory() {
        return this.releaseversionhistory;
    }

    public void setReleaseversionhistory(String releaseversionhistory) {
        this.releaseversionhistory = releaseversionhistory;
    }

    public Progress getProgress() {
        return this.progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public Votes getVotes() {
        return this.votes;
    }

    public void setVotes(Votes votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "{" +
            " issuetype='" + getIssuetype() + "'" +
            ", parent='" + getParent() + "'" +
            ", timespent='" + getTimespent() + "'" +
            ", development='" + getDevelopment() + "'" +
            ", project='" + getProject() + "'" +
            ", fixVersions='" + getFixVersions() + "'" +
            ", aggregatetimespent='" + getAggregatetimespent() + "'" +
            ", resolution='" + getResolution() + "'" +
            ", caughtby='" + getCaughtby() + "'" +
            ", riskconsequence='" + getRiskconsequence() + "'" +
            ", riskprobability='" + getRiskprobability() + "'" +
            ", resolutiondate='" + getResolutiondate() + "'" +
            ", verificationscope='" + getVerificationscope() + "'" +
            ", workratio='" + getWorkratio() + "'" +
            ", lastViewed='" + getLastViewed() + "'" +
            ", watches='" + getWatches() + "'" +
            ", watchers='" + getWatchers() + "'" +
            ", created='" + getCreated() + "'" +
            ", priority='" + getPriority() + "'" +
            ", baselineenddate='" + getBaselineenddate() + "'" +
            ", taskprogress='" + getTaskprogress() + "'" +
            ", labels='" + getLabels() + "'" +
            ", taskmode='" + getTaskmode() + "'" +
            ", sprint='" + getSprint() + "'" +
            ", epiclink='" + getEpiclink() + "'" +
            ", rankobsolete='" + getRankobsolete() + "'" +
            ", startdatesoftwareplant='" + getStartdatesoftwareplant() + "'" +
            ", timeestimate='" + getTimeestimate() + "'" +
            ", aggregatetimeoriginalestimate='" + getAggregatetimeoriginalestimate() + "'" +
            ", versions='" + getVersions() + "'" +
            ", baselinestartdate='" + getBaselinestartdate() + "'" +
            ", enddate='" + getEnddate() + "'" +
            ", storypoints='" + getStorypoints() + "'" +
            ", issuelinks='" + getIssuelinks() + "'" +
            ", assignee='" + getAssignee() + "'" +
            ", updated='" + getUpdated() + "'" +
            ", status='" + getStatus() + "'" +
            ", components='" + getComponents() + "'" +
            ", timeoriginalestimate='" + getTimeoriginalestimate() + "'" +
            ", description='" + getDescription() + "'" +
            ", subtype='" + getSubtype() + "'" +
            ", startdate='" + getStartdate() + "'" +
            ", jirarelatedtopreviousproject='" + getJirarelatedtopreviousproject() + "'" +
            ", secondtype='" + getSecondtype() + "'" +
            ", aggregatetimeestimate='" + getAggregatetimeestimate() + "'" +
            ", summary='" + getSummary() + "'" +
            ", creator='" + getCreator() + "'" +
            ", subtasks='" + getSubtasks() + "'" +
            ", reporter='" + getReporter() + "'" +
            ", aggregateprogress='" + getAggregateprogress() + "'" +
            ", rank='" + getRank() + "'" +
            ", environment='" + getEnvironment() + "'" +
            ", duedate='" + getDuedate() + "'" +
            ", releaseversionhistory='" + getReleaseversionhistory() + "'" +
            ", progress='" + getProgress() + "'" +
            ", votes='" + getVotes() + "'" +
            "}";
    }
}
