package com.westerndigital.keyinsight.KPI1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class KPI1 {
    @Id
    @SequenceGenerator(name = "kpi1_sequence", sequenceName = "kpi1_sequence", allocationSize = 1)
    @GeneratedValue(generator = "kpi1_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String teamType;
    private Integer totalJiraCount;
    private Float totalJiraStoryPoints;
    private Integer closedJiraCount;
    private Float closedJiraStoryPoints;
    private Float percentageClosedJiraStoryPoints;
    private Integer notStartedJiraCount;
    private Float notStartedJiraStoryPoints;
    private Float percentageNotStartedJiraStoryPoints;
    private Integer wipJiraCount;
    private Float wipJiraStoryPoints;
    private Float percentageWIPJiraStoryPoints;
    private Float percentageBugs;
    private Float percentageReopenedIssues;
    private Float percentageCriticalIssues;
    private Float percentageCriticalIssuesNotCompleted;
    private Float percentageCancelledIssues;
}
