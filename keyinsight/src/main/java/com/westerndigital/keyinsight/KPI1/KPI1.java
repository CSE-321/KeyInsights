package com.westerndigital.keyinsight.KPI1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class KPI1 {
    @Id
    @SequenceGenerator(name = "kpi1_sequence", sequenceName = "kpi1_sequence", allocationSize = 1)
    @GeneratedValue(generator = "kpi1_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String issueType;
    private Integer totalJiraCount;
    private Double totalJiraStoryPoints;
    private Integer closedJiraCount;
    private Double closedJiraStoryPoints;
    private Double percentageClosedJiraStoryPoints;
    private Integer notStartedJiraCount;
    private Double notStartedJiraStoryPoints;
    private Double percentageNotStartedJiraStoryPoints;
    private Integer wipJiraCount;
    private Double wipJiraStoryPoints;
    private Double percentageWIPJiraStoryPoints;
    private Double percentageBugs;
    private Double percentageReopenedIssues;
    private Double percentageCriticalIssues;
    private Double percentageCriticalIssuesNotCompleted;
    private Double percentageCancelledIssues;
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIssueType() {
        return this.issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public Integer getTotalJiraCount() {
        return this.totalJiraCount;
    }

    public void setTotalJiraCount(Integer totalJiraCount) {
        this.totalJiraCount = totalJiraCount;
    }

    public Double getTotalJiraStoryPoints() {
        return this.totalJiraStoryPoints;
    }

    public void setTotalJiraStoryPoints(Double totalJiraStoryPoints) {
        this.totalJiraStoryPoints = totalJiraStoryPoints;
    }

    public Integer getClosedJiraCount() {
        return this.closedJiraCount;
    }

    public void setClosedJiraCount(Integer closedJiraCount) {
        this.closedJiraCount = closedJiraCount;
    }

    public Double getClosedJiraStoryPoints() {
        return this.closedJiraStoryPoints;
    }

    public void setClosedJiraStoryPoints(Double closedJiraStoryPoints) {
        this.closedJiraStoryPoints = closedJiraStoryPoints;
    }

    public Double getPercentageClosedJiraStoryPoints() {
        return this.percentageClosedJiraStoryPoints;
    }

    public void setPercentageClosedJiraStoryPoints(Double percentageClosedJiraStoryPoints) {
        this.percentageClosedJiraStoryPoints = percentageClosedJiraStoryPoints;
    }

    public Integer getNotStartedJiraCount() {
        return this.notStartedJiraCount;
    }

    public void setNotStartedJiraCount(Integer notStartedJiraCount) {
        this.notStartedJiraCount = notStartedJiraCount;
    }

    public Double getNotStartedJiraStoryPoints() {
        return this.notStartedJiraStoryPoints;
    }

    public void setNotStartedJiraStoryPoints(Double notStartedJiraStoryPoints) {
        this.notStartedJiraStoryPoints = notStartedJiraStoryPoints;
    }

    public Double getPercentageNotStartedJiraStoryPoints() {
        return this.percentageNotStartedJiraStoryPoints;
    }

    public void setPercentageNotStartedJiraStoryPoints(Double percentageNotStartedJiraStoryPoints) {
        this.percentageNotStartedJiraStoryPoints = percentageNotStartedJiraStoryPoints;
    }

    public Integer getWipJiraCount() {
        return this.wipJiraCount;
    }

    public void setWipJiraCount(Integer wipJiraCount) {
        this.wipJiraCount = wipJiraCount;
    }

    public Double getWipJiraStoryPoints() {
        return this.wipJiraStoryPoints;
    }

    public void setWipJiraStoryPoints(Double wipJiraStoryPoints) {
        this.wipJiraStoryPoints = wipJiraStoryPoints;
    }

    public Double getPercentageWIPJiraStoryPoints() {
        return this.percentageWIPJiraStoryPoints;
    }

    public void setPercentageWIPJiraStoryPoints(Double percentageWIPJiraStoryPoints) {
        this.percentageWIPJiraStoryPoints = percentageWIPJiraStoryPoints;
    }

    public Double getPercentageBugs() {
        return this.percentageBugs;
    }

    public void setPercentageBugs(Double percentageBugs) {
        this.percentageBugs = percentageBugs;
    }

    public Double getPercentageReopenedIssues() {
        return this.percentageReopenedIssues;
    }

    public void setPercentageReopenedIssues(Double percentageReopenedIssues) {
        this.percentageReopenedIssues = percentageReopenedIssues;
    }

    public Double getPercentageCriticalIssues() {
        return this.percentageCriticalIssues;
    }

    public void setPercentageCriticalIssues(Double percentageCriticalIssues) {
        this.percentageCriticalIssues = percentageCriticalIssues;
    }

    public Double getPercentageCriticalIssuesNotCompleted() {
        return this.percentageCriticalIssuesNotCompleted;
    }

    public void setPercentageCriticalIssuesNotCompleted(Double percentageCriticalIssuesNotCompleted) {
        this.percentageCriticalIssuesNotCompleted = percentageCriticalIssuesNotCompleted;
    }

    public Double getPercentageCancelledIssues() {
        return this.percentageCancelledIssues;
    }

    public void setPercentageCancelledIssues(Double percentageCancelledIssues) {
        this.percentageCancelledIssues = percentageCancelledIssues;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", issueType='" + getIssueType() + "'" +
            ", totalJiraCount='" + getTotalJiraCount() + "'" +
            ", totalJiraStoryPoints='" + getTotalJiraStoryPoints() + "'" +
            ", closedJiraCount='" + getClosedJiraCount() + "'" +
            ", closedJiraStoryPoints='" + getClosedJiraStoryPoints() + "'" +
            ", percentageClosedJiraStoryPoints='" + getPercentageClosedJiraStoryPoints() + "'" +
            ", notStartedJiraCount='" + getNotStartedJiraCount() + "'" +
            ", notStartedJiraStoryPoints='" + getNotStartedJiraStoryPoints() + "'" +
            ", percentageNotStartedJiraStoryPoints='" + getPercentageNotStartedJiraStoryPoints() + "'" +
            ", wipJiraCount='" + getWipJiraCount() + "'" +
            ", wipJiraStoryPoints='" + getWipJiraStoryPoints() + "'" +
            ", percentageWIPJiraStoryPoints='" + getPercentageWIPJiraStoryPoints() + "'" +
            ", percentageBugs='" + getPercentageBugs() + "'" +
            ", percentageReopenedIssues='" + getPercentageReopenedIssues() + "'" +
            ", percentageCriticalIssues='" + getPercentageCriticalIssues() + "'" +
            ", percentageCriticalIssuesNotCompleted='" + getPercentageCriticalIssuesNotCompleted() + "'" +
            ", percentageCancelledIssues='" + getPercentageCancelledIssues() + "'" +
            "}";
    }
    
}
