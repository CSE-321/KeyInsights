package com.westerndigital.keyinsight.KPI1;

import org.springframework.stereotype.Service;

import com.westerndigital.keyinsight.JiraIssue.JiraIssueRepository;

import java.util.List;

@Service

public class KPI1Service {
    private final JiraIssueRepository issueRepository;

    public KPI1Service(JiraIssueRepository issueRepository){
        this.issueRepository = issueRepository;
    }
    public List<KPI1> getKPI1() {
        List<KPI1> listofKPI1;
        List<String> teamtypes = issueRepository.getAllTeamType();
        System.out.print(teamtypes);
        
        //for(KPI : ){

        //}

        return null;
    }
}
