package com.westerndigital.keyinsight.KPI2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
@Entity
public class KPI2 {
    @Id
    @SequenceGenerator(name = "kpi2_sequence", sequenceName = "kpi2_sequence", allocationSize = 1)
    @GeneratedValue(generator = "kpi2_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String issueType;
    private Double averageDayToCompleteIssue;
    private Double medianDayToCompleteIssues;
    private Integer minimumDayToCompleteIssues;
    private Integer maximumDayToCompleteIssues;

    public KPI2() {
    }

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

    public Double getAverageDayToCompleteIssue() {
        return this.averageDayToCompleteIssue;
    }

    public void setAverageDayToCompleteIssue(Double averageDayToCompleteIssue) {
        this.averageDayToCompleteIssue = averageDayToCompleteIssue;
    }

    public Double getMedianDayToCompleteIssues() {
        return this.medianDayToCompleteIssues;
    }

    public void setMedianDayToCompleteIssues(Double medianDayToCompleteIssues) {
        this.medianDayToCompleteIssues = medianDayToCompleteIssues;
    }

    public Integer getMinimumDayToCompleteIssues() {
        return this.minimumDayToCompleteIssues;
    }

    public void setMinimumDayToCompleteIssues(Integer minimumDayToCompleteIssues) {
        this.minimumDayToCompleteIssues = minimumDayToCompleteIssues;
    }

    public Integer getMaximumDayToCompleteIssues() {
        return this.maximumDayToCompleteIssues;
    }

    public void setMaximumDayToCompleteIssues(Integer maximumDayToCompleteIssues) {
        this.maximumDayToCompleteIssues = maximumDayToCompleteIssues;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", issueType='" + getIssueType() + "'" +
            ", averageDayToCompleteIssue='" + getAverageDayToCompleteIssue() + "'" +
            ", medianDayToCompleteIssues='" + getMedianDayToCompleteIssues() + "'" +
            ", minimumDayToCompleteIssues='" + getMinimumDayToCompleteIssues() + "'" +
            ", maximumDayToCompleteIssues='" + getMaximumDayToCompleteIssues() + "'" +
            "}";
    }

}
