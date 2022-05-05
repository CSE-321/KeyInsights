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

    public List<KPI3> getKPI3PerTeam(String projectName) {

        kpi3Repository.deleteAll();
        ArrayList<KPI3> listofKPI3 = new ArrayList<KPI3>();
        ArrayList<Object[]> numOfCreatedResolvedIssuesByMonth = issueRepository
                .numberOfIssuesCreatedAndResolvedInAMonth(projectName);
        for (Object[] numOfCreatedResolvedIssue : numOfCreatedResolvedIssuesByMonth) {
            KPI3 numberOfIssuesCreatedResolvedInMonth = new KPI3();
            System.out.println(numOfCreatedResolvedIssue[0]);
            System.out.println(numOfCreatedResolvedIssue[1]);
            System.out.println(numOfCreatedResolvedIssue[2]);
            System.out.println(numOfCreatedResolvedIssue[3]);
            System.out.println(numOfCreatedResolvedIssue[4]);
            System.out.println(numOfCreatedResolvedIssue[5]);

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

        List<String> teamTypes = issueRepository.getAllTeamType(projectName);

        for (String teamType : teamTypes) {
            ArrayList<Object[]> numOfCreatedResolvedIssuesByMonthAndTeamType = issueRepository
                    .numberOfIssuesCreatedAndResolvedInAMonthByTeamType(projectName, teamType);
            for (Object[] numOfCreatedResolvedIssue : numOfCreatedResolvedIssuesByMonthAndTeamType) {
                KPI3 numberOfIssuesCreatedResolvedInMonth = new KPI3();
                System.out.println(numOfCreatedResolvedIssue[0]);
                System.out.println(numOfCreatedResolvedIssue[1]);
                System.out.println(numOfCreatedResolvedIssue[2]);
                System.out.println(numOfCreatedResolvedIssue[3]);
                System.out.println(numOfCreatedResolvedIssue[4]);
                System.out.println(numOfCreatedResolvedIssue[5]);

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
