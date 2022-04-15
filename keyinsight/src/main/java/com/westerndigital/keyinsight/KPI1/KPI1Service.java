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

        String closed = "Closed";
        String wip = "In Progress";
        String notStarted = "Waiting";
        String reopened = "Reopened";

        String bug = "Bug";

        String criticalPriority = "Critical";

        String completed = "Completed";

        String fixed = "Fixed";

        String done = "Done";

        String cancelled = "Project cancelled";

        for(String teamType: teamtypes ){
            KPI1 kpi1 = new KPI1();
            

            // --------------------------------------

            // KPI Report 1 requires these values
            // Type(team)
            // Total Jira# & story points
            // Closed Jira# & story points & % of total story points
            // Not started Jira# & story points & % of total story points
            // WIP Jira# & story points & % of total story points
            // % of new features - NO CLUE HOW TO FIND IT
            // % of bugs
            // % of reopen tickets
            // % of critical requests
            // % of critical requests not completed
            // % of cancelled tickets
            // I have grabbed all but the % of new features

            // Block of code for Total Jira# and story points
            // ----------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraIssueCount = issueRepository.totalTeamTypeJiraIssueCount(teamType);
            Float totalTeamTypeJiraIssueStoryPoint = issueRepository.totalTeamTypeJiraIssueStoryPoint(teamType)
                    .orElse(0.0f);
            // ----------------------------------------------------------------------------------------------------

            // Block of code for Closed Jira# and story points and % of total story points
            // ----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraClosedIssueCount = issueRepository.totalTeamTypeJiraStatusIssueCount(teamType, closed);
            Float totalTeamTypeJiraClosedIssueStoryPoint = issueRepository
                    .totalTeamTypeJiraStatusIssueStoryPoint(teamType, closed).orElse(0.0f);
            // ----------------------------------------------------------------------------------------------------------

            // Block of code for WIP Jira# and story points and % of total story points
            // -----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraWIPIssueCount = issueRepository.totalTeamTypeJiraStatusIssueCount(teamType, wip);
            Float totalTeamTypeJiraWIPIssueStoryPoint = issueRepository
                    .totalTeamTypeJiraStatusIssueStoryPoint(teamType, wip).orElse(0.0f);
            // ------------------------------------------------------------------------------------------------------------

            // Block of code for Not Started Jira# and story points and % of total story
            // points
            // -----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraNotStartedIssueCount = issueRepository.totalTeamTypeJiraStatusIssueCount(teamType,
                    notStarted);
            Float totalTeamTypeJiraNotStartedIssueStoryPoint = issueRepository
                    .totalTeamTypeJiraStatusIssueStoryPoint(teamType, notStarted).orElse(0.0f);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of bugs in Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraBugIssueCount = issueRepository.totalTeamTypeJiraSubTypeIssueCount(teamType, bug);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of reopen Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraReopenedIssueCount = issueRepository.totalTeamTypeJiraStatusIssueCount(teamType,
                    reopened);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of critical Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraCriticalIssueCount = issueRepository.totalTeamTypeJiraPriorityIssueCount(teamType,
                    criticalPriority);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of critical not completed Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraCriticalNotCompletedIssueCount = issueRepository
                    .totalTeamTypeJiraPriorityOppositeResolutionIssueCount(teamType, criticalPriority, completed, fixed, done);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of cancelled Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraCancelledIssueCount = issueRepository.totalTeamTypeJiraResolutionIssueCount(teamType,
                    cancelled);
            // -----------------------------------------------------------------------------------------------------------
            kpi1.setTeamType(teamType);
            System.out.println("The query for totalTeamTypeJiraIssueCount returns: " + totalTeamTypeJiraIssueCount);
            System.out.println(
                    "The query for totalTeamTypeJiraIssueStoryPoint returns: " + totalTeamTypeJiraIssueStoryPoint);

            System.out.println(
                    "The query for totalTeamTypeJiraClosedIssueCount returns: " + totalTeamTypeJiraClosedIssueCount);
            System.out.println("The query for totalTeamTypeJiraClosedIssueStoryPoint returns: "
                    + totalTeamTypeJiraClosedIssueStoryPoint);
            System.out.println(
                    "The percentage between totalTeamTypeJiraClosedIssueCount and totalTeamTypeJiraIssueStoryPoint is "
                            + (totalTeamTypeJiraClosedIssueStoryPoint / totalTeamTypeJiraIssueStoryPoint) * 100.0f
                            + "%");

            System.out
                    .println("The query for totalTeamTypeJiraWIPIssueCount returns: " + totalTeamTypeJiraWIPIssueCount);
            System.out.println("The query for totalTeamTypeJiraWIPIssueStoryPoint returns: "
                    + totalTeamTypeJiraWIPIssueStoryPoint);
            System.out.println(
                    "The percentage between totalTeamTypeJiraWIPIssueStoryPoint and totalTeamTypeJiraIssueStoryPoint is "
                            + (totalTeamTypeJiraWIPIssueStoryPoint / totalTeamTypeJiraIssueStoryPoint) * 100.0f + "%");
            
            System.out.println("The query for totalTeamTypeJiraNotStartedIssueCount returns: "
                    + totalTeamTypeJiraNotStartedIssueCount);
            System.out.println("The query for totalTeamTypeJiraNotStartedIssueStoryPoint returns: "
                    + totalTeamTypeJiraNotStartedIssueStoryPoint);
            System.out.println(
                    "The percentage between totalTeamTypeJiraNotStartedIssueStoryPoint and totalTeamTypeJiraIssueStoryPoint is "
                            + (totalTeamTypeJiraNotStartedIssueStoryPoint / totalTeamTypeJiraIssueStoryPoint) * 100.0f
                            + "%");

            System.out
                    .println("The query for totalTeamTypeJiraBugIssueCount returns: " + totalTeamTypeJiraBugIssueCount);
            System.out.println(
                    "The percentange between totalTeamTypeJiraBugIssueCount and totalTeamTypeJiraIssueCount is "
                            + ((float) totalTeamTypeJiraBugIssueCount / totalTeamTypeJiraIssueCount) * 100.0f + "%");

            System.out.println("The query for totalTeamTypeJiraReopenedIssueCount returns: "
                    + totalTeamTypeJiraReopenedIssueCount);
            System.out.println(
                    "The percentage between totalTeamTypeJiraReopenedIssueCount and totalTeamTypeJiraIssueCount is "
                            + ((float) totalTeamTypeJiraReopenedIssueCount / totalTeamTypeJiraIssueCount) * 100 + "%");

            System.out.println("The query for totalTeamTypeJiraCriticalIssueCount returns: "
                    + totalTeamTypeJiraCriticalIssueCount);
            System.out.println(
                    "The percentage between totalTeamTypeJiraCriticalIssueCount and totalTeamTypeJiraIssueCount is "
                            + ((float) totalTeamTypeJiraCriticalIssueCount / totalTeamTypeJiraIssueCount) * 100 + "%");

            System.out.println("The query for totalTeamTypeJiraCriticalNotCompletedIssueCount returns: "
                    + totalTeamTypeJiraCriticalNotCompletedIssueCount);
            System.out.println(
                    "The percentage between totalTeamTypeJiraCriticalNotCompletedIssueCount and totalTeamTypeJiraIssueCount is "
                            + ((float) totalTeamTypeJiraCriticalNotCompletedIssueCount / totalTeamTypeJiraIssueCount)
                                    * 100
                            + "%");

            System.out.println("The query for totalTeamTypeJiraCancelledIssueCount returns: "
                    + totalTeamTypeJiraCancelledIssueCount);
            System.out.println(
                    "The percentage between totalTeamTypeJiraCancelledIssueCount and totalTeamTypeJiraIssueCount is "
                            + ((float) totalTeamTypeJiraCancelledIssueCount / totalTeamTypeJiraIssueCount) * 100 + "%");

        }

        return null;
    }
}
