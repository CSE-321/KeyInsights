package com.westerndigital.keyinsight.KPI1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class KPI1 {
    @Id
    @SequenceGenerator(name = "kpi1_sequence", sequenceName = "kpi1_sequence", allocationSize = 1)
    @GeneratedValue(generator = "kpi1_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String teamType;
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
}
