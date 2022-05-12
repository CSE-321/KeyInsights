package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetAllProjectsPOJO.ProjectSearchJson;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.ProjectPOJO.Versions;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.UserPOJO.UserJson;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Fields {
    private IssueType issuetype;
    private Parent parent;
    private String timespent;
    @JsonProperty("customfield_12010")
    private String development;
    private ProjectSearchJson project;
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
}
