package com.westerndigital.keyinsight.KPI4;

import java.util.ArrayList;
import java.util.List;

import com.westerndigital.keyinsight.JiraIssue.JiraIssueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KPI4Service {
    @Autowired
    private final JiraIssueService issueService;

    @Autowired
    private final KPI4Repository kpi4Repository;

    public KPI4Service(JiraIssueService issueService, KPI4Repository kpi4Repository) {
        this.issueService = issueService;
        this.kpi4Repository = kpi4Repository;
    }

    public List<KPI4> getKPI4PerTeam(String projectName) {
        kpi4Repository.deleteAll();
        List<KPI4> listofKPI4 = new ArrayList<>();

        List<String> teamTypes = issueService.getAllTeamType(projectName);
        String wip = "In Progress";
        String notstarted = "Open";
        String critical = "Critical";
        for(String teamType : teamTypes){
            List<Object[]> assigneeCompleteTotalInformationByTeamType = issueService.assigneeTotalCompleteInformation(projectName, teamType, wip, notstarted, critical);
            for(Object[] singleAssigneeCompleteInfo : assigneeCompleteTotalInformationByTeamType){
                KPI4 assigneeInformation = new KPI4();
                int totalJiraCount = Integer.parseInt(singleAssigneeCompleteInfo[1].toString());
                double PercentageCompleted = Integer.parseInt(singleAssigneeCompleteInfo[3].toString())/(double)totalJiraCount;
                double PercentageWIP = Integer.parseInt(singleAssigneeCompleteInfo[5].toString())/(double)totalJiraCount;
                double PercentageNotstarted = Integer.parseInt(singleAssigneeCompleteInfo[7].toString())/(double)totalJiraCount;
                double PercentageCriticalNotstarted = Integer.parseInt(singleAssigneeCompleteInfo[9].toString())/(double)totalJiraCount;

                assigneeInformation.setAssigneeName(singleAssigneeCompleteInfo[0].toString());
                assigneeInformation.setTeamType(teamType);                
                assigneeInformation.setJiraCount(totalJiraCount);
                assigneeInformation.setStoryPoint(Double.parseDouble(singleAssigneeCompleteInfo[2].toString()));                
                assigneeInformation.setPercentageCompleted(PercentageCompleted);                
                assigneeInformation.setPercentageWIP(PercentageWIP);                
                assigneeInformation.setPercentageNotstarted(PercentageNotstarted);                
                assigneeInformation.setPercentageCriticalNotstarted(PercentageCriticalNotstarted);
                kpi4Repository.save(assigneeInformation);
                listofKPI4.add(assigneeInformation);
            }
        }
        return listofKPI4;
    }
}
