package com.westerndigital.keyinsight.KPI3;

import java.util.ArrayList;
import java.util.List;

import com.westerndigital.keyinsight.JiraIssue.JiraIssueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KPI3Service {
    @Autowired
    private JiraIssueService issueService;

    @Autowired
    private KPI3Repository kpi3Repository;

    public List<KPI3> getKPI3PerTeam(String projectName) {

        kpi3Repository.deleteAll();
        List<KPI3> listofKPI3 = new ArrayList<>();
        List<Object[]> numOfCreatedResolvedIssuesByMonth = issueService
                .numberOfIssuesCreatedAndResolvedInAMonth(projectName);
        for (Object[] numOfCreatedResolvedIssue : numOfCreatedResolvedIssuesByMonth) {
            KPI3 numberOfIssuesCreatedResolvedInMonth = new KPI3();
            
            String createdMonth = null;
            if (numOfCreatedResolvedIssue[0] != null) {
                createdMonth = numOfCreatedResolvedIssue[0].toString();
            }

            String resolvedMonth = null;
            if (numOfCreatedResolvedIssue[1] != null) {
                resolvedMonth = numOfCreatedResolvedIssue[1].toString();
            }

            Integer createdJiraCount = 0;
            if (numOfCreatedResolvedIssue[2] != null) {
                createdJiraCount = Integer.parseInt(numOfCreatedResolvedIssue[2].toString());
            }

            Float createdJiraStoryPoints = 0f;
            if (numOfCreatedResolvedIssue[3] != null) {
                createdJiraStoryPoints = Float.parseFloat(numOfCreatedResolvedIssue[3].toString());
            }

            Integer resolvedJiraCount = 0;
            if (numOfCreatedResolvedIssue[4] != null) {
                resolvedJiraCount = Integer.parseInt(numOfCreatedResolvedIssue[4].toString());
            }

            Float resolvedJiraStoryPoints = 0f;
            if (numOfCreatedResolvedIssue[5] != null) {
                resolvedJiraStoryPoints = Float.parseFloat(numOfCreatedResolvedIssue[5].toString());
            }

            numberOfIssuesCreatedResolvedInMonth.setTeamType("All Jira Issues");
            numberOfIssuesCreatedResolvedInMonth.setCreatedMonth(createdMonth);
            numberOfIssuesCreatedResolvedInMonth.setResolvedMonth(resolvedMonth);
            numberOfIssuesCreatedResolvedInMonth.setCreatedJiraCount(createdJiraCount);
            numberOfIssuesCreatedResolvedInMonth.setCreatedJiraStoryPoints(createdJiraStoryPoints);
            numberOfIssuesCreatedResolvedInMonth.setResolvedJiraCount(resolvedJiraCount);
            numberOfIssuesCreatedResolvedInMonth.setResolvedJiraStoryPoints(resolvedJiraStoryPoints);
            kpi3Repository.save(numberOfIssuesCreatedResolvedInMonth);
            listofKPI3.add(numberOfIssuesCreatedResolvedInMonth);

        }

        List<String> teamTypes = issueService.getAllTeamType(projectName);

        for (String teamType : teamTypes) {
            List<Object[]> numOfCreatedResolvedIssuesByMonthAndTeamType = issueService
                    .numberOfIssuesCreatedAndResolvedInAMonthByTeamType(projectName, teamType);
            for (Object[] numOfCreatedResolvedIssue : numOfCreatedResolvedIssuesByMonthAndTeamType) {
                KPI3 numberOfIssuesCreatedResolvedInMonth = new KPI3();

                String createdMonth = null;
                if (numOfCreatedResolvedIssue[0] != null) {
                    createdMonth = numOfCreatedResolvedIssue[0].toString();
                }

                String resolvedMonth = null;
                if (numOfCreatedResolvedIssue[1] != null) {
                    resolvedMonth = numOfCreatedResolvedIssue[1].toString();
                }

                Integer createdJiraCount = null;
                if (numOfCreatedResolvedIssue[2] != null) {
                    createdJiraCount = Integer.parseInt(numOfCreatedResolvedIssue[2].toString());
                }

                Float createdJiraStoryPoints = null;
                if (numOfCreatedResolvedIssue[3] != null) {
                    createdJiraStoryPoints = Float.parseFloat(numOfCreatedResolvedIssue[3].toString());
                }

                Integer resolvedJiraCount = null;
                if (numOfCreatedResolvedIssue[4] != null) {
                    resolvedJiraCount = Integer.parseInt(numOfCreatedResolvedIssue[4].toString());
                }

                Float resolvedJiraStoryPoints = null;
                if (numOfCreatedResolvedIssue[5] != null) {
                    resolvedJiraStoryPoints = Float.parseFloat(numOfCreatedResolvedIssue[5].toString());
                }

                numberOfIssuesCreatedResolvedInMonth.setTeamType(teamType);
                numberOfIssuesCreatedResolvedInMonth.setCreatedMonth(createdMonth);
                numberOfIssuesCreatedResolvedInMonth.setResolvedMonth(resolvedMonth);
                numberOfIssuesCreatedResolvedInMonth.setCreatedJiraCount(createdJiraCount);
                numberOfIssuesCreatedResolvedInMonth.setCreatedJiraStoryPoints(createdJiraStoryPoints);
                numberOfIssuesCreatedResolvedInMonth.setResolvedJiraCount(resolvedJiraCount);
                numberOfIssuesCreatedResolvedInMonth.setResolvedJiraStoryPoints(resolvedJiraStoryPoints);
                kpi3Repository.save(numberOfIssuesCreatedResolvedInMonth);
                listofKPI3.add(numberOfIssuesCreatedResolvedInMonth);
            }

        }
        return listofKPI3;
    }
}
