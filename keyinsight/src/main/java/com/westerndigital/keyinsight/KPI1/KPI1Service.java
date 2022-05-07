package com.westerndigital.keyinsight.KPI1;

import com.westerndigital.keyinsight.JiraIssue.JiraIssueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class KPI1Service {
    @Autowired
    private final JiraIssueService issueService;

    @Autowired
    private final KPI1Repository kpi1Repository;

    public KPI1Service(JiraIssueService issueService, KPI1Repository kpi1Repository) {
        this.issueService = issueService;
        this.kpi1Repository = kpi1Repository;
    }

    public List<KPI1> getKPI1PerTeam(String projectName) {
        List<KPI1> listofKPI1 = new ArrayList<>();
        List<String> issueTypes = issueService.getAllIssueType(projectName);

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

        KPI1 OverviewKPI1 = kpi1Repository.findByIssueType("All Jira Issues").orElse(new KPI1());

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
        int totalJiraBugIssueCount = issueService.totalJiraSecondTypeIssueCount(projectName,
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
        OverviewKPI1.setIssueType("All Jira Issues");
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

        for (String issueType : issueTypes) {
            OverviewKPI1 = kpi1Repository.findByIssueType(issueType).orElse(new KPI1());

            // --------------------------------------

            // Block of code for Total Jira# and story points
            // ----------------------------------------------------------------------------------------------------
            List<Object[]> totalIssueTypeJiraCountAndStoryPoints = issueService
                    .totalIssueTypeJiraCountAndStoryPoints(projectName, issueType);
            int totalIssueTypeJiraIssueCount = Integer
                    .parseInt(totalIssueTypeJiraCountAndStoryPoints.get(0)[0].toString());
            double totalIssueTypeJiraIssueStoryPoint = Double
                    .parseDouble(totalIssueTypeJiraCountAndStoryPoints.get(0)[1].toString());
            // ----------------------------------------------------------------------------------------------------

            // Block of code for Closed Jira# and story points and % of total story points
            // ----------------------------------------------------------------------------------------------------------
            List<Object[]> totalIssueTypeJiraCountAndStoryPointsFromClosed = issueService
                    .totalIssueTypeJiraCountAndStoryPointsFromStatus(projectName, issueType, closed);
            int totalIssueTypeJiraClosedIssueCount = Integer
                    .parseInt(totalIssueTypeJiraCountAndStoryPointsFromClosed.get(0)[0].toString());
            double totalIssueTypeJiraClosedIssueStoryPoint = Double
                    .parseDouble(totalIssueTypeJiraCountAndStoryPointsFromClosed.get(0)[1].toString());
            // ----------------------------------------------------------------------------------------------------------

            // Block of code for WIP Jira# and story points and % of total story points
            // -----------------------------------------------------------------------------------------------------------
            List<Object[]> totalIssueTypeJiraCountAndStoryPointsFromWIP = issueService
                    .totalIssueTypeJiraCountAndStoryPointsFromStatus(projectName, issueType, wip);
            int totalIssueTypeJiraWIPIssueCount = Integer
                    .parseInt(totalIssueTypeJiraCountAndStoryPointsFromWIP.get(0)[0].toString());
            double totalIssueTypeJiraWIPIssueStoryPoint = Double
                    .parseDouble(totalIssueTypeJiraCountAndStoryPointsFromWIP.get(0)[1].toString());
            // ------------------------------------------------------------------------------------------------------------

            // Block of code for Not Started Jira# and story points and % of total story
            // points
            // -----------------------------------------------------------------------------------------------------------
            List<Object[]> totalIssueTypeJiraCountAndStoryPointsFromNotStarted = issueService
                    .totalIssueTypeJiraCountAndStoryPointsFromStatus(projectName, issueType, notStarted);
            int totalIssueTypeJiraNotStartedIssueCount = Integer
                    .parseInt(totalIssueTypeJiraCountAndStoryPointsFromNotStarted.get(0)[0].toString());
            double totalIssueTypeJiraNotStartedIssueStoryPoint = Double
                    .parseDouble(totalIssueTypeJiraCountAndStoryPointsFromNotStarted.get(0)[1].toString());
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of bugs in Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalIssueTypeJiraBugIssueCount = issueService
                    .totalIssueTypeJiraSecondTypeIssueCount(projectName, issueType, bug);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of reopen Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalIssueTypeJiraReopenedIssueCount = issueService.totalIssueTypeJiraStatusIssueCount(
                    projectName, issueType,
                    reopened);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of critical Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalIssueTypeJiraCriticalIssueCount = issueService.totalIssueTypeJiraPriorityIssueCount(
                    projectName, issueType,
                    criticalPriority);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of critical not completed Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalIssueTypeJiraCriticalNotCompletedIssueCount = issueService
                    .totalIssueTypeJiraPriorityOppositeResolutionIssueCount(projectName, issueType,
                            criticalPriority, completed, fixed, done);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of cancelled Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalIssueTypeJiraCancelledIssueCount = issueService
                    .totalIssueTypeJiraResolutionIssueCount(projectName, issueType,
                            cancelled);
            // -----------------------------------------------------------------------------------------------------------
            OverviewKPI1.setIssueType(issueType);
            OverviewKPI1.setTotalJiraCount(totalIssueTypeJiraIssueCount);
            OverviewKPI1.setTotalJiraStoryPoints(totalIssueTypeJiraIssueStoryPoint);
            OverviewKPI1.setClosedJiraCount(totalIssueTypeJiraClosedIssueCount);
            OverviewKPI1.setClosedJiraStoryPoints(totalIssueTypeJiraClosedIssueStoryPoint);
            OverviewKPI1.setPercentageClosedJiraStoryPoints(
                    (totalIssueTypeJiraClosedIssueStoryPoint / totalIssueTypeJiraIssueStoryPoint)
                            * 100.0f);
            OverviewKPI1.setWipJiraCount(totalIssueTypeJiraWIPIssueCount);
            OverviewKPI1.setWipJiraStoryPoints(totalIssueTypeJiraWIPIssueStoryPoint);
            OverviewKPI1.setPercentageWIPJiraStoryPoints(
                    (totalIssueTypeJiraWIPIssueStoryPoint / totalIssueTypeJiraIssueStoryPoint)
                            * 100.0f);
            OverviewKPI1.setNotStartedJiraCount(totalIssueTypeJiraNotStartedIssueCount);
            OverviewKPI1.setNotStartedJiraStoryPoints(totalIssueTypeJiraNotStartedIssueStoryPoint);
            OverviewKPI1.setPercentageNotStartedJiraStoryPoints(
                    (totalIssueTypeJiraNotStartedIssueStoryPoint
                            / totalIssueTypeJiraIssueStoryPoint)
                            * 100.0f);
            OverviewKPI1.setPercentageBugs(
                    ((double) totalIssueTypeJiraBugIssueCount / totalIssueTypeJiraIssueCount) * 100.0);
            OverviewKPI1.setPercentageReopenedIssues(
                    ((double) totalIssueTypeJiraReopenedIssueCount / totalIssueTypeJiraIssueCount) * 100.0);
            OverviewKPI1.setPercentageCriticalIssues(
                    ((double) totalIssueTypeJiraCriticalIssueCount / totalIssueTypeJiraIssueCount) * 100.0);
            OverviewKPI1.setPercentageCriticalIssuesNotCompleted(
                    ((double) totalIssueTypeJiraCriticalNotCompletedIssueCount / totalIssueTypeJiraIssueCount)
                            * 100.0);
            OverviewKPI1.setPercentageCancelledIssues(
                    ((double) totalIssueTypeJiraCancelledIssueCount / totalIssueTypeJiraIssueCount) * 100.0);

            kpi1Repository.save(OverviewKPI1);
            listofKPI1.add(OverviewKPI1);

        }

        return listofKPI1;
    }
}
