package com.westerndigital.keyinsight.KPI2;

import java.util.ArrayList;
import java.util.List;

import com.westerndigital.keyinsight.JiraIssue.JiraIssueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KPI2Service {
    @Autowired
    private final JiraIssueService issueService;

    @Autowired
    private final KPI2Repository kpi2Repository;

    public KPI2Service(JiraIssueService issueService, KPI2Repository kpi2Repository) {
        this.issueService = issueService;
        this.kpi2Repository = kpi2Repository;
    }

    public List<KPI2> getKPI2PerTeam(String projectName) {
        List<KPI2> listofKPI2 = new ArrayList<>();
        List<String> teamTypes = issueService.getAllTeamType(projectName);
        System.out.print(teamTypes);

        KPI2 daysToCompleteIssueKPI2 = kpi2Repository.findByTeamType("All Jira Issues").orElse(new KPI2());
        List<Integer> daysNeedToCompleteTotal = issueService.daysNeededToCompleteTotalJiraIssues(projectName);

        double median = -1.0;
        int size = daysNeedToCompleteTotal.size();
        int middle = size / 2;
        if(size % 2 == 0){
            median = (double)(daysNeedToCompleteTotal.get(middle - 1) + daysNeedToCompleteTotal.get(middle))/2;
        }else{
            median = daysNeedToCompleteTotal.get(middle);
        }

        daysToCompleteIssueKPI2.setTeamType("All Jira Issues");
        daysToCompleteIssueKPI2.setAverageDayToCompleteIssue(daysNeedToCompleteTotal.stream().mapToInt(val -> val).average().orElse(0.0));
        daysToCompleteIssueKPI2.setMaximumDayToCompleteIssues(daysNeedToCompleteTotal.stream().mapToInt(val -> val).max().orElse(0));
        daysToCompleteIssueKPI2.setMinimumDayToCompleteIssues(daysNeedToCompleteTotal.stream().mapToInt(val -> val).min().orElse(0));
        daysToCompleteIssueKPI2.setMedianDayToCompleteIssues(median);
        
        kpi2Repository.save(daysToCompleteIssueKPI2);
        listofKPI2.add(daysToCompleteIssueKPI2);

        for(String teamType : teamTypes){
            daysToCompleteIssueKPI2 = kpi2Repository.findByTeamType(teamType).orElse(new KPI2());
            List<Integer> daysNeedToCompleteTeamType = issueService.daysNeededToCompleteTeamTypeJiraIssues(projectName, teamType);
            median = -1.0;
            size = daysNeedToCompleteTeamType.size();
            middle = size / 2;
            if(size % 2 == 0){
                median = (double)(daysNeedToCompleteTeamType.get(middle - 1) + daysNeedToCompleteTeamType.get(middle))/2;
            }else{
                median = daysNeedToCompleteTeamType.get(middle);
            }

            daysToCompleteIssueKPI2.setTeamType(teamType);
            daysToCompleteIssueKPI2.setAverageDayToCompleteIssue(daysNeedToCompleteTeamType.stream().mapToInt(val -> val).average().orElse(0.0));
            daysToCompleteIssueKPI2.setMaximumDayToCompleteIssues(daysNeedToCompleteTeamType.stream().mapToInt(val -> val).max().orElse(0));
            daysToCompleteIssueKPI2.setMinimumDayToCompleteIssues(daysNeedToCompleteTeamType.stream().mapToInt(val -> val).min().orElse(0));
            daysToCompleteIssueKPI2.setMedianDayToCompleteIssues(median);
            
            kpi2Repository.save(daysToCompleteIssueKPI2);
            listofKPI2.add(daysToCompleteIssueKPI2);

        }

        return listofKPI2;

    }

}
