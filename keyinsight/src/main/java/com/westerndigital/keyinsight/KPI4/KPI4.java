package com.westerndigital.keyinsight.KPI4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class KPI4 {
    @Id
    @SequenceGenerator(name = "kpi4_sequence", sequenceName = "kpi4_sequence", allocationSize = 1)
    @GeneratedValue(generator = "kpi4_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String assigneeName;//assignee Name
    private String issueType;
    private Integer jiraCount;
    private Double storyPoint;
    private Double PercentageCompleted;
    private Double PercentageWIP;
    private Double PercentageNotstarted;
    private Double PercentageCriticalNotstarted;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssigneeName() {
        return this.assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getIssueType() {
        return this.issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public Integer getJiraCount() {
        return this.jiraCount;
    }

    public void setJiraCount(Integer jiraCount) {
        this.jiraCount = jiraCount;
    }

    public Double getStoryPoint() {
        return this.storyPoint;
    }

    public void setStoryPoint(Double storyPoint) {
        this.storyPoint = storyPoint;
    }

    public Double getPercentageCompleted() {
        return this.PercentageCompleted;
    }

    public void setPercentageCompleted(Double PercentageCompleted) {
        this.PercentageCompleted = PercentageCompleted;
    }

    public Double getPercentageWIP() {
        return this.PercentageWIP;
    }

    public void setPercentageWIP(Double PercentageWIP) {
        this.PercentageWIP = PercentageWIP;
    }

    public Double getPercentageNotstarted() {
        return this.PercentageNotstarted;
    }

    public void setPercentageNotstarted(Double PercentageNotstarted) {
        this.PercentageNotstarted = PercentageNotstarted;
    }

    public Double getPercentageCriticalNotstarted() {
        return this.PercentageCriticalNotstarted;
    }

    public void setPercentageCriticalNotstarted(Double PercentageCriticalNotstarted) {
        this.PercentageCriticalNotstarted = PercentageCriticalNotstarted;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", issueType='" + getIssueType() + "'" +
            ", jiraCount='" + getJiraCount() + "'" +
            ", storyPoint='" + getStoryPoint() + "'" +
            ", PercentageCompleted='" + getPercentageCompleted() + "'" +
            ", PercentageWIP='" + getPercentageWIP() + "'" +
            ", PercentageNotstarted='" + getPercentageNotstarted() + "'" +
            ", PercentageCriticalNotstarted='" + getPercentageCriticalNotstarted() + "'" +
            "}";
    }

}
