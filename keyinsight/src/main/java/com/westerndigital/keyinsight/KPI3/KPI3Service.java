package com.westerndigital.keyinsight.KPI3;

import java.util.ArrayList;
import java.util.List;

import com.westerndigital.keyinsight.JiraIssue.JiraIssueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KPI3Service {
    @Autowired
    private final JiraIssueRepository issueRepository;

    @Autowired
    private final KPI3Repository kpi3Repository;

    public KPI3Service(JiraIssueRepository issueRepository, KPI3Repository kpi3Repository) {
        this.issueRepository = issueRepository;
        this.kpi3Repository = kpi3Repository;
    }

    public List<KPI3> getKPI3PerTeam(String projectName){
        kpi3Repository.deleteAll();
        ArrayList<KPI3> listofKPI3 = new ArrayList<KPI3>();
        ArrayList<Object[]> numOfCreatedIssuesByMonth = issueRepository.numberOfIssuesCreatedInMonth(projectName);
        
        
        for(Object[] numOfCreatedIssue : numOfCreatedIssuesByMonth){
            KPI3 numberOfIssuesCreatedInMonth = new KPI3();
            System.out.println(numOfCreatedIssue[0]);
            System.out.println(numOfCreatedIssue[1]);
            System.out.println(numOfCreatedIssue[2]);
            String createdMonth = null;
            if(numOfCreatedIssue[0].toString() != null){
                createdMonth = numOfCreatedIssue[0].toString();
            }
            

            Integer jiraCount = null;
            if(numOfCreatedIssue[1] != null){
                jiraCount = Integer.parseInt(numOfCreatedIssue[1].toString());
            }

            Float jiraStoryPoints = null;
            if(numOfCreatedIssue[2] != null){
                jiraStoryPoints = Float.parseFloat(numOfCreatedIssue[2].toString());
            }
            numberOfIssuesCreatedInMonth.setTeamType("All Jira Issues");
            numberOfIssuesCreatedInMonth.setCreatedMonth(createdMonth);
            numberOfIssuesCreatedInMonth.setTotalCreatedJiraCount(jiraCount);
            numberOfIssuesCreatedInMonth.setTotalCreatedJiraStoryPoints(jiraStoryPoints);
            kpi3Repository.save(numberOfIssuesCreatedInMonth);
            listofKPI3.add(numberOfIssuesCreatedInMonth);
        }

        List<String> teamTypes = issueRepository.getAllTeamType(projectName);

        for(String teamType : teamTypes){
            ArrayList<Object[]> numOfCreatedIssuesInMonthByTeamType = issueRepository.numberOfIssuesCreatedInMonthByTeamType(projectName, teamType);
            for(Object[] numOfCreatedIssueByTeamType : numOfCreatedIssuesInMonthByTeamType){
                KPI3 numberOfIssuesCreatedInMonthByTeamType = new KPI3();
                System.out.println(numOfCreatedIssueByTeamType[0]);
                System.out.println(numOfCreatedIssueByTeamType[1]);
                System.out.println(numOfCreatedIssueByTeamType[2]);
                String createdMonth = null;
                if(numOfCreatedIssueByTeamType[0].toString() != null){
                    createdMonth = numOfCreatedIssueByTeamType[0].toString();
                }
                
    
                Integer jiraCount = null;
                if(numOfCreatedIssueByTeamType[1] != null){
                    jiraCount = Integer.parseInt(numOfCreatedIssueByTeamType[1].toString());
                }
    
                Float jiraStoryPoints = null;
                if(numOfCreatedIssueByTeamType[2] != null){
                    jiraStoryPoints = Float.parseFloat(numOfCreatedIssueByTeamType[2].toString());
                }
                numberOfIssuesCreatedInMonthByTeamType.setTeamType(teamType);
                numberOfIssuesCreatedInMonthByTeamType.setCreatedMonth(createdMonth);
                numberOfIssuesCreatedInMonthByTeamType.setTotalCreatedJiraCount(jiraCount);
                numberOfIssuesCreatedInMonthByTeamType.setTotalCreatedJiraStoryPoints(jiraStoryPoints);
                kpi3Repository.save(numberOfIssuesCreatedInMonthByTeamType);
                listofKPI3.add(numberOfIssuesCreatedInMonthByTeamType);
            }
        }
        return listofKPI3;
    }
}
