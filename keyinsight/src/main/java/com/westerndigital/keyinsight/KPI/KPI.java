package com.westerndigital.keyinsight.KPI;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KPI {

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
