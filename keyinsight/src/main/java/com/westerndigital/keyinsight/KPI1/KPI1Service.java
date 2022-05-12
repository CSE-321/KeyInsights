package com.westerndigital.keyinsight.KPI1;

import com.westerndigital.keyinsight.JiraIssue.JiraIssueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class KPI1Service {
    @Autowired
    private JiraIssueService issueService;

    @Autowired
    private KPI1Repository kpi1Repository;

    public List<KPI1> getKPI1PerTeam(String projectName) {
        List<KPI1> listofKPI1 = new ArrayList<>();
        List<String> teamTypes = issueService.getAllTeamType(projectName);

        String closed = "Closed";
        String wip = "In Progress";
        String notStarted = "Open";
        String reopened = "Reopened";

        String bug = "Bug";

        String criticalPriority = "Critical";

        String completed = "Completed";

        String fixed = "Fixed";

        String done = "Done";

        String cancelled = "Project cancelled";

        KPI1 OverviewKPI1 = kpi1Repository.findByTeamType("All Jira Issues").orElse(new KPI1());

        // --------------------------------------

        // Block of code for Total Jira# and story points
        // ----------------------------------------------------------------------------------------------------
        List<Object[]> totalJiraCountAndStoryPoints = issueService
                .totalJiraCountAndStoryPoints(projectName);
        int totalJiraIssueCount = Integer.parseInt(totalJiraCountAndStoryPoints.get(0)[0].toString());
        double totalJiraIssueStoryPoint = Double.parseDouble(totalJiraCountAndStoryPoints.get(0)[1].toString());
        // ----------------------------------------------------------------------------------------------------

        // Block of code for Closed Jira# and story points and % of total story points
        // ----------------------------------------------------------------------------------------------------------
        List<Object[]> totalJiraCountAndStoryPointsFromClosed = issueService
                .totalJiraCountAndStoryPointsFromStatus(projectName, closed);
        int totalJiraClosedIssueCount = Integer
                .parseInt(totalJiraCountAndStoryPointsFromClosed.get(0)[0].toString());
        double totalJiraClosedIssueStoryPoint = Double
                .parseDouble(totalJiraCountAndStoryPointsFromClosed.get(0)[1].toString());
        // ----------------------------------------------------------------------------------------------------------

        // Block of code for WIP Jira# and story points and % of total story points
        // -----------------------------------------------------------------------------------------------------------
        List<Object[]> totalJiraCountAndStoryPointsFromWIP = issueService
                .totalJiraCountAndStoryPointsFromStatus(projectName, wip);
        int totalJiraWIPIssueCount = Integer.parseInt(totalJiraCountAndStoryPointsFromWIP.get(0)[0].toString());
        double totalJiraWIPIssueStoryPoint = Double
                .parseDouble(totalJiraCountAndStoryPointsFromWIP.get(0)[1].toString());
        // ------------------------------------------------------------------------------------------------------------

        // Block of code for Not Started Jira# and story points and % of total story
        // points
        // -----------------------------------------------------------------------------------------------------------
        List<Object[]> totalJiraCountAndStoryPointsFromNotStarted = issueService
                .totalJiraCountAndStoryPointsFromStatus(projectName, notStarted);
        int totalJiraNotStartedIssueCount = Integer
                .parseInt(totalJiraCountAndStoryPointsFromNotStarted.get(0)[0].toString());
        double totalJiraNotStartedIssueStoryPoint = Double
                .parseDouble(totalJiraCountAndStoryPointsFromNotStarted.get(0)[1].toString());
        // -----------------------------------------------------------------------------------------------------------

        // Line of code for % of bugs in Jira Issues
        // -----------------------------------------------------------------------------------------------------------
        int totalJiraBugIssueCount = issueService.totalJiraSubTypeIssueCount(projectName,
                bug);
        // -----------------------------------------------------------------------------------------------------------

        // Line of code for % of reopen Jira Issues
        // -----------------------------------------------------------------------------------------------------------
        int totalJiraReopenedIssueCount = issueService.totalJiraStatusIssueCount(projectName, reopened);
        // -----------------------------------------------------------------------------------------------------------

        // Line of code for % of critical Jira Issues
        // -----------------------------------------------------------------------------------------------------------
        int totalJiraCriticalIssueCount = issueService.totalJiraPriorityIssueCount(
                projectName,
                criticalPriority);
        // -----------------------------------------------------------------------------------------------------------

        // Line of code for % of critical not completed Jira Issues
        // -----------------------------------------------------------------------------------------------------------
        int totalJiraCriticalNotCompletedIssueCount = issueService
                .totalJiraPriorityOppositeResolutionIssueCount(projectName,
                        criticalPriority, completed, fixed, done);
        // -----------------------------------------------------------------------------------------------------------

        // Line of code for % of cancelled Jira Issues
        // -----------------------------------------------------------------------------------------------------------
        int totalJiraCancelledIssueCount = issueService.totalJiraResolutionIssueCount(
                projectName,
                cancelled);
        // -----------------------------------------------------------------------------------------------------------
        OverviewKPI1.setTeamType("All Jira Issues");
        OverviewKPI1.setTotalJiraCount(totalJiraIssueCount);
        OverviewKPI1.setTotalJiraStoryPoints(totalJiraIssueStoryPoint);
        OverviewKPI1.setClosedJiraCount(totalJiraClosedIssueCount);
        OverviewKPI1.setClosedJiraStoryPoints(totalJiraClosedIssueStoryPoint);
        OverviewKPI1.setPercentageClosedJiraStoryPoints(
                (totalJiraClosedIssueStoryPoint / totalJiraIssueStoryPoint) * 100.0);
        OverviewKPI1.setWipJiraCount(totalJiraWIPIssueCount);
        OverviewKPI1.setWipJiraStoryPoints(totalJiraWIPIssueStoryPoint);
        OverviewKPI1.setPercentageWIPJiraStoryPoints(
                (totalJiraWIPIssueStoryPoint / totalJiraIssueStoryPoint) * 100.0);
        OverviewKPI1.setNotStartedJiraCount(totalJiraNotStartedIssueCount);
        OverviewKPI1.setNotStartedJiraStoryPoints(totalJiraNotStartedIssueStoryPoint);
        OverviewKPI1.setPercentageNotStartedJiraStoryPoints(
                (totalJiraNotStartedIssueStoryPoint / totalJiraIssueStoryPoint)
                        * 100.0);
        OverviewKPI1.setPercentageBugs(((double) totalJiraBugIssueCount / totalJiraIssueCount) * 100.0);
        OverviewKPI1.setPercentageReopenedIssues(
                ((double) totalJiraReopenedIssueCount / totalJiraIssueCount) * 100.0);
        OverviewKPI1.setPercentageCriticalIssues(
                ((double) totalJiraCriticalIssueCount / totalJiraIssueCount) * 100.0);
        OverviewKPI1.setPercentageCriticalIssuesNotCompleted(
                ((double) totalJiraCriticalNotCompletedIssueCount / totalJiraIssueCount) * 100.0);
        OverviewKPI1.setPercentageCancelledIssues(
                ((double) totalJiraCancelledIssueCount / totalJiraIssueCount) * 100.0);

        kpi1Repository.save(OverviewKPI1);
        listofKPI1.add(OverviewKPI1);

        for (String teamType : teamTypes) {
            OverviewKPI1 = kpi1Repository.findByTeamType(teamType).orElse(new KPI1());

            // --------------------------------------

            // Block of code for Total Jira# and story points
            // ----------------------------------------------------------------------------------------------------
            List<Object[]> totalTeamTypeJiraCountAndStoryPoints = issueService
                    .totalTeamTypeJiraCountAndStoryPoints(projectName, teamType);
            int totalTeamTypeJiraIssueCount = Integer
                    .parseInt(totalTeamTypeJiraCountAndStoryPoints.get(0)[0].toString());
            double totalTeamTypeJiraIssueStoryPoint = Double
                    .parseDouble(totalTeamTypeJiraCountAndStoryPoints.get(0)[1].toString());
            // ----------------------------------------------------------------------------------------------------

            // Block of code for Closed Jira# and story points and % of total story points
            // ----------------------------------------------------------------------------------------------------------
            List<Object[]> totalTeamTypeJiraCountAndStoryPointsFromClosed = issueService
                    .totalTeamTypeJiraCountAndStoryPointsFromStatus(projectName, teamType, closed);
            int totalTeamTypeJiraClosedIssueCount = Integer
                    .parseInt(totalTeamTypeJiraCountAndStoryPointsFromClosed.get(0)[0].toString());
            double totalTeamTypeJiraClosedIssueStoryPoint = Double
                    .parseDouble(totalTeamTypeJiraCountAndStoryPointsFromClosed.get(0)[1].toString());
            // ----------------------------------------------------------------------------------------------------------

            // Block of code for WIP Jira# and story points and % of total story points
            // -----------------------------------------------------------------------------------------------------------
            List<Object[]> totalTeamTypeJiraCountAndStoryPointsFromWIP = issueService
                    .totalTeamTypeJiraCountAndStoryPointsFromStatus(projectName, teamType, wip);
            int totalTeamTypeJiraWIPIssueCount = Integer
                    .parseInt(totalTeamTypeJiraCountAndStoryPointsFromWIP.get(0)[0].toString());
            double totalTeamTypeJiraWIPIssueStoryPoint = Double
                    .parseDouble(totalTeamTypeJiraCountAndStoryPointsFromWIP.get(0)[1].toString());
            // ------------------------------------------------------------------------------------------------------------

            // Block of code for Not Started Jira# and story points and % of total story
            // points
            // -----------------------------------------------------------------------------------------------------------
            List<Object[]> totalTeamTypeJiraCountAndStoryPointsFromNotStarted = issueService
                    .totalTeamTypeJiraCountAndStoryPointsFromStatus(projectName, teamType, notStarted);
            int totalTeamTypeJiraNotStartedIssueCount = Integer
                    .parseInt(totalTeamTypeJiraCountAndStoryPointsFromNotStarted.get(0)[0].toString());
            double totalTeamTypeJiraNotStartedIssueStoryPoint = Double
                    .parseDouble(totalTeamTypeJiraCountAndStoryPointsFromNotStarted.get(0)[1].toString());
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of bugs in Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraBugIssueCount = issueService
                    .totalTeamTypeJiraSubTypeIssueCount(projectName, teamType, bug);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of reopen Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraReopenedIssueCount = issueService.totalTeamTypeJiraStatusIssueCount(
                    projectName, teamType,
                    reopened);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of critical Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraCriticalIssueCount = issueService.totalTeamTypeJiraPriorityIssueCount(
                    projectName, teamType,
                    criticalPriority);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of critical not completed Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraCriticalNotCompletedIssueCount = issueService
                    .totalTeamTypeJiraPriorityOppositeResolutionIssueCount(projectName, teamType,
                            criticalPriority, completed, fixed, done);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of cancelled Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalTeamTypeJiraCancelledIssueCount = issueService
                    .totalTeamTypeJiraResolutionIssueCount(projectName, teamType,
                            cancelled);
            // -----------------------------------------------------------------------------------------------------------
            OverviewKPI1.setTeamType(teamType);
            OverviewKPI1.setTotalJiraCount(totalTeamTypeJiraIssueCount);
            OverviewKPI1.setTotalJiraStoryPoints(totalTeamTypeJiraIssueStoryPoint);
            OverviewKPI1.setClosedJiraCount(totalTeamTypeJiraClosedIssueCount);
            OverviewKPI1.setClosedJiraStoryPoints(totalTeamTypeJiraClosedIssueStoryPoint);
            OverviewKPI1.setPercentageClosedJiraStoryPoints(
                    (totalTeamTypeJiraClosedIssueStoryPoint / totalTeamTypeJiraIssueStoryPoint)
                            * 100.0f);
            OverviewKPI1.setWipJiraCount(totalTeamTypeJiraWIPIssueCount);
            OverviewKPI1.setWipJiraStoryPoints(totalTeamTypeJiraWIPIssueStoryPoint);
            OverviewKPI1.setPercentageWIPJiraStoryPoints(
                    (totalTeamTypeJiraWIPIssueStoryPoint / totalTeamTypeJiraIssueStoryPoint)
                            * 100.0f);
            OverviewKPI1.setNotStartedJiraCount(totalTeamTypeJiraNotStartedIssueCount);
            OverviewKPI1.setNotStartedJiraStoryPoints(totalTeamTypeJiraNotStartedIssueStoryPoint);
            OverviewKPI1.setPercentageNotStartedJiraStoryPoints(
                    (totalTeamTypeJiraNotStartedIssueStoryPoint
                            / totalTeamTypeJiraIssueStoryPoint)
                            * 100.0f);
            OverviewKPI1.setPercentageBugs(
                    ((double) totalTeamTypeJiraBugIssueCount / totalTeamTypeJiraIssueCount) * 100.0);
            OverviewKPI1.setPercentageReopenedIssues(
                    ((double) totalTeamTypeJiraReopenedIssueCount / totalTeamTypeJiraIssueCount) * 100.0);
            OverviewKPI1.setPercentageCriticalIssues(
                    ((double) totalTeamTypeJiraCriticalIssueCount / totalTeamTypeJiraIssueCount) * 100.0);
            OverviewKPI1.setPercentageCriticalIssuesNotCompleted(
                    ((double) totalTeamTypeJiraCriticalNotCompletedIssueCount / totalTeamTypeJiraIssueCount)
                            * 100.0);
            OverviewKPI1.setPercentageCancelledIssues(
                    ((double) totalTeamTypeJiraCancelledIssueCount / totalTeamTypeJiraIssueCount) * 100.0);

            kpi1Repository.save(OverviewKPI1);
            listofKPI1.add(OverviewKPI1);

        }

        return listofKPI1;
    }
}
