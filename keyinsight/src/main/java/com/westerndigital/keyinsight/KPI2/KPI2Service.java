package com.westerndigital.keyinsight.KPI2;

import java.util.ArrayList;
import java.util.List;

import com.westerndigital.keyinsight.JiraIssue.JiraIssueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KPI2Service {
    @Autowired
    private final JiraIssueRepository issueRepository;

    @Autowired
    private final KPI2Repository kpi2Repository;

    public KPI2Service(JiraIssueRepository issueRepository, KPI2Repository kpi2Repository) {
        this.issueRepository = issueRepository;
        this.kpi2Repository = kpi2Repository;
    }

    public List<KPI2> getKPI2PerTeam(String projectName) {
        ArrayList<KPI2> listofKPI2 = new ArrayList<KPI2>();
        // String projectName = "B8X4";
        List<String> teamTypes = issueRepository.getAllTeamType(projectName);
        System.out.print(teamTypes);

        KPI2 daysToCompleteIssueKPI2 = new KPI2();
        ArrayList<Integer> daysNeedToCompleteTotal = issueRepository.daysNeededToCompleteTotalJiraIssues(projectName);

        double median = -1.0;
        int size = daysNeedToCompleteTotal.size();
        int middle = size / 2;
        if(size % 2 == 0){
            median = (double)(daysNeedToCompleteTotal.get(middle - 1) + daysNeedToCompleteTotal.get(middle))/2;
        }else{
            median = daysNeedToCompleteTotal.get(middle);
        }

        System.out.println("Median day needed for this team is " + median);

        daysToCompleteIssueKPI2.setTeamType("All Jira Issues");
        daysToCompleteIssueKPI2.setAverageDayToCompleteIssue(daysNeedToCompleteTotal.stream().mapToInt(val -> val).average().orElse(0.0));
        daysToCompleteIssueKPI2.setMaximumDayToCompleteIssues(daysNeedToCompleteTotal.stream().mapToInt(val -> val).max().orElse(0));
        daysToCompleteIssueKPI2.setMinimumDayToCompleteIssues(daysNeedToCompleteTotal.stream().mapToInt(val -> val).min().orElse(0));
        daysToCompleteIssueKPI2.setMedianDayToCompleteIssues(median);
        
        kpi2Repository.save(daysToCompleteIssueKPI2);
        listofKPI2.add(daysToCompleteIssueKPI2);

        for(String teamType : teamTypes){
            daysToCompleteIssueKPI2 = new KPI2();
            ArrayList<Integer> daysNeedToCompleteTeamType = issueRepository.daysNeededToCompleteTeamTypeJiraIssues(projectName, teamType);
            median = -1.0;
            size = daysNeedToCompleteTeamType.size();
            middle = size / 2;
            if(size % 2 == 0){
                median = (double)(daysNeedToCompleteTeamType.get(middle - 1) + daysNeedToCompleteTeamType.get(middle))/2;
            }else{
                median = daysNeedToCompleteTeamType.get(middle);
            }

            System.out.println("Median day needed for this team is " + median);

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
