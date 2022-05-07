package com.westerndigital.keyinsight.KPI3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
@Entity
public class KPI3 {
    @Id
    @SequenceGenerator(name = "kpi3_sequence", sequenceName = "kpi3_sequence", allocationSize = 1)
    @GeneratedValue(generator = "kpi3_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String issueType;
    private String createdMonth;
    private String resolvedMonth;
    private Integer createdJiraCount;
    private Float createdJiraStoryPoints;
    private Integer resolvedJiraCount;
    private Float resolvedJiraStoryPoints;

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

    public String getCreatedMonth() {
        return this.createdMonth;
    }

    public void setCreatedMonth(String createdMonth) {
        this.createdMonth = createdMonth;
    }

    public String getResolvedMonth() {
        return this.resolvedMonth;
    }

    public void setResolvedMonth(String resolvedMonth) {
        this.resolvedMonth = resolvedMonth;
    }

    public Integer getCreatedJiraCount() {
        return this.createdJiraCount;
    }

    public void setCreatedJiraCount(Integer createdJiraCount) {
        this.createdJiraCount = createdJiraCount;
    }

    public Float getCreatedJiraStoryPoints() {
        return this.createdJiraStoryPoints;
    }

    public void setCreatedJiraStoryPoints(Float createdJiraStoryPoints) {
        this.createdJiraStoryPoints = createdJiraStoryPoints;
    }

    public Integer getResolvedJiraCount() {
        return this.resolvedJiraCount;
    }

    public void setResolvedJiraCount(Integer resolvedJiraCount) {
        this.resolvedJiraCount = resolvedJiraCount;
    }

    public Float getResolvedJiraStoryPoints() {
        return this.resolvedJiraStoryPoints;
    }

    public void setResolvedJiraStoryPoints(Float resolvedJiraStoryPoints) {
        this.resolvedJiraStoryPoints = resolvedJiraStoryPoints;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", issueType='" + getIssueType() + "'" +
            ", createdMonth='" + getCreatedMonth() + "'" +
            ", resolvedMonth='" + getResolvedMonth() + "'" +
            ", createdJiraCount='" + getCreatedJiraCount() + "'" +
            ", createdJiraStoryPoints='" + getCreatedJiraStoryPoints() + "'" +
            ", resolvedJiraCount='" + getResolvedJiraCount() + "'" +
            ", resolvedJiraStoryPoints='" + getResolvedJiraStoryPoints() + "'" +
            "}";
    }

}
