package com.westerndigital.keyinsight.KPI1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.westerndigital.keyinsight.JiraIssue.JiraIssueRepository;

import java.util.ArrayList;
import java.util.List;

@Service

public class KPI1Service {
    @Autowired
    private final JiraIssueRepository issueRepository;

    @Autowired
    private final KPI1Repository kpi1Repository;

    public KPI1Service(JiraIssueRepository issueRepository, KPI1Repository kpi1Repository) {
        this.issueRepository = issueRepository;
        this.kpi1Repository = kpi1Repository;
    }

    public List<KPI1> getKPI1PerTeam(String projectName) {
        ArrayList<KPI1> listofKPI1 = new ArrayList<KPI1>();
        // String projectName = "B8X4";
        ArrayList<String> issueTypes = issueRepository.getAllIssueType(projectName);

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

        KPI1 OverviewKPI1 = kpi1Repository.findByIssueType("All Jira Issues").orElse(new KPI1());

        // --------------------------------------

        // Block of code for Total Jira# and story points
        // ----------------------------------------------------------------------------------------------------
        Integer totalJiraIssueCount = 0;
        Double totalJiraIssueStoryPoint = 1.0;
        ArrayList<Object[]> totalJiraCountAndStoryPoints = issueRepository
                .totalJiraCountAndStoryPoints(projectName);
        for (Object[] element : totalJiraCountAndStoryPoints) {
            totalJiraIssueCount = ((Long) element[0]).intValue();
            totalJiraIssueStoryPoint = ((Double)element[1] == null) ? 0.0 : (Double)element[1];
        }
        // ----------------------------------------------------------------------------------------------------

        // Block of code for Closed Jira# and story points and % of total story points
        // ----------------------------------------------------------------------------------------------------------
        Integer totalJiraClosedIssueCount = 0;
        Double totalJiraClosedIssueStoryPoint = 0.0;
        ArrayList<Object[]> totalJiraCountAndStoryPointsFromClosed = issueRepository
                .totalJiraCountAndStoryPointsFromStatus(projectName, closed);
        for (Object[] element : totalJiraCountAndStoryPointsFromClosed) {
            totalJiraClosedIssueCount = ((Long) element[0]).intValue();
            totalJiraClosedIssueStoryPoint = ((Double)element[1] == null) ? 0.0 : (Double)element[1];
        }
        // ----------------------------------------------------------------------------------------------------------

        // Block of code for WIP Jira# and story points and % of total story points
        // -----------------------------------------------------------------------------------------------------------
        Integer totalJiraWIPIssueCount = 0;
        Double totalJiraWIPIssueStoryPoint = 0.0;
        ArrayList<Object[]> totalJiraCountAndStoryPointsFromWIP = issueRepository
                .totalJiraCountAndStoryPointsFromStatus(projectName, wip);
        for (Object[] element : totalJiraCountAndStoryPointsFromWIP) {
            totalJiraWIPIssueCount = ((Long) element[0]).intValue();
            totalJiraWIPIssueStoryPoint = ((Double)element[1] == null) ? 0.0 : (Double)element[1];
        }
        // ------------------------------------------------------------------------------------------------------------

        // Block of code for Not Started Jira# and story points and % of total story
        // points
        // -----------------------------------------------------------------------------------------------------------
        Integer totalJiraNotStartedIssueCount = 0;
        Double totalJiraNotStartedIssueStoryPoint = 0.0;
        ArrayList<Object[]> totalJiraCountAndStoryPointsFromNotStarted = issueRepository
                .totalJiraCountAndStoryPointsFromStatus(projectName, notStarted);
        for (Object[] element : totalJiraCountAndStoryPointsFromNotStarted) {
            totalJiraNotStartedIssueCount = ((Long) element[0]).intValue();
            totalJiraNotStartedIssueStoryPoint = ((Double)element[1] == null) ? 0.0 : (Double)element[1];
        }
        // -----------------------------------------------------------------------------------------------------------

        // Line of code for % of bugs in Jira Issues
        // -----------------------------------------------------------------------------------------------------------
        int totalJiraBugIssueCount = issueRepository.totalJiraSecondTypeIssueCount(projectName,
                bug);
        // -----------------------------------------------------------------------------------------------------------

        // Line of code for % of reopen Jira Issues
        // -----------------------------------------------------------------------------------------------------------
        int totalJiraReopenedIssueCount = issueRepository.totalJiraStatusIssueCount(projectName, reopened);
        // -----------------------------------------------------------------------------------------------------------

        // Line of code for % of critical Jira Issues
        // -----------------------------------------------------------------------------------------------------------
        int totalJiraCriticalIssueCount = issueRepository.totalJiraPriorityIssueCount(
                projectName,
                criticalPriority);
        // -----------------------------------------------------------------------------------------------------------

        // Line of code for % of critical not completed Jira Issues
        // -----------------------------------------------------------------------------------------------------------
        int totalJiraCriticalNotCompletedIssueCount = issueRepository
                .totalJiraPriorityOppositeResolutionIssueCount(projectName,
                        criticalPriority, completed, fixed, done);
        // -----------------------------------------------------------------------------------------------------------

        // Line of code for % of cancelled Jira Issues
        // -----------------------------------------------------------------------------------------------------------
        int totalJiraCancelledIssueCount = issueRepository.totalJiraResolutionIssueCount(
                projectName,
                cancelled);
        // -----------------------------------------------------------------------------------------------------------
        OverviewKPI1.setIssueType("All Jira Issues");
        OverviewKPI1.setTotalJiraCount(totalJiraIssueCount);
        OverviewKPI1.setTotalJiraStoryPoints(totalJiraIssueStoryPoint);
        OverviewKPI1.setClosedJiraCount(totalJiraClosedIssueCount);
        OverviewKPI1.setClosedJiraStoryPoints(totalJiraClosedIssueStoryPoint);
        OverviewKPI1.setPercentageClosedJiraStoryPoints(
                (totalJiraClosedIssueStoryPoint / totalJiraIssueStoryPoint) * 100.0f);
        OverviewKPI1.setWipJiraCount(totalJiraWIPIssueCount);
        OverviewKPI1.setWipJiraStoryPoints(totalJiraWIPIssueStoryPoint);
        OverviewKPI1.setPercentageWIPJiraStoryPoints(
                (totalJiraWIPIssueStoryPoint / totalJiraIssueStoryPoint) * 100.0f);
        OverviewKPI1.setNotStartedJiraCount(totalJiraNotStartedIssueCount);
        OverviewKPI1.setNotStartedJiraStoryPoints(totalJiraNotStartedIssueStoryPoint);
        OverviewKPI1.setPercentageNotStartedJiraStoryPoints(
                (totalJiraNotStartedIssueStoryPoint / totalJiraIssueStoryPoint)
                        * 100.0f);
        OverviewKPI1.setPercentageBugs((Double.valueOf(totalJiraBugIssueCount / totalJiraIssueCount)) * 100.0);
        OverviewKPI1.setPercentageReopenedIssues(
                (Double.valueOf(totalJiraReopenedIssueCount / totalJiraIssueCount)) * 100.0);
        OverviewKPI1.setPercentageCriticalIssues(
                (Double.valueOf(totalJiraCriticalIssueCount / totalJiraIssueCount)) * 100.0);
        OverviewKPI1.setPercentageCriticalIssuesNotCompleted(
                (Double.valueOf(totalJiraCriticalNotCompletedIssueCount / totalJiraIssueCount)) * 100.0);
        OverviewKPI1.setPercentageCancelledIssues(
                (Double.valueOf(totalJiraCancelledIssueCount / totalJiraIssueCount)) * 100);

        kpi1Repository.save(OverviewKPI1);
        listofKPI1.add(OverviewKPI1);

        for (String issueType : issueTypes) {
            OverviewKPI1 = kpi1Repository.findByIssueType(issueType).orElse(new KPI1());

            // --------------------------------------

            // Block of code for Total Jira# and story points
            // ----------------------------------------------------------------------------------------------------
            Integer totalIssueTypeJiraIssueCount = 0;
            Double totalIssueTypeJiraIssueStoryPoint = 1.0;
            ArrayList<Object[]> totalIssueTypeJiraCountAndStoryPoints = issueRepository
                    .totalIssueTypeJiraCountAndStoryPoints(projectName, issueType);
            for (Object[] element : totalIssueTypeJiraCountAndStoryPoints) {
                totalIssueTypeJiraIssueCount = ((Long) element[0]).intValue();
                totalIssueTypeJiraIssueStoryPoint = ((Double)element[1] == null) ? 1.0 : (Double)element[1];
            }
            // ----------------------------------------------------------------------------------------------------

            // Block of code for Closed Jira# and story points and % of total story points
            // ----------------------------------------------------------------------------------------------------------
            Integer totalIssueTypeJiraClosedIssueCount = 0;
            Double totalIssueTypeJiraClosedIssueStoryPoint = 0.0;
            ArrayList<Object[]> totalIssueTypeJiraCountAndStoryPointsFromClosed = issueRepository
                    .totalIssueTypeJiraCountAndStoryPointsFromStatus(projectName, issueType, closed);
            for (Object[] element : totalIssueTypeJiraCountAndStoryPointsFromClosed) {
                totalIssueTypeJiraClosedIssueCount = ((Long) element[0]).intValue();
                totalIssueTypeJiraClosedIssueStoryPoint = ((Double)element[1] == null) ? 0.0 : (Double)element[1];
            }
            // ----------------------------------------------------------------------------------------------------------

            // Block of code for WIP Jira# and story points and % of total story points
            // -----------------------------------------------------------------------------------------------------------
            Integer totalIssueTypeJiraWIPIssueCount = 0;
            Double totalIssueTypeJiraWIPIssueStoryPoint = 0.0;
            ArrayList<Object[]> totalIssueTypeJiraCountAndStoryPointsFromWIP = issueRepository
                    .totalIssueTypeJiraCountAndStoryPointsFromStatus(projectName, issueType, wip);
            for (Object[] element : totalIssueTypeJiraCountAndStoryPointsFromWIP) {
                totalIssueTypeJiraWIPIssueCount = ((Long) element[0]).intValue();
                totalIssueTypeJiraWIPIssueStoryPoint = ((Double)element[1] == null) ? 0.0 : (Double)element[1];
            }
            // ------------------------------------------------------------------------------------------------------------

            // Block of code for Not Started Jira# and story points and % of total story
            // points
            // -----------------------------------------------------------------------------------------------------------
            Integer totalIssueTypeJiraNotStartedIssueCount = 0;
            Double totalIssueTypeJiraNotStartedIssueStoryPoint = 0.0;
            ArrayList<Object[]> totalIssueTypeJiraCountAndStoryPointsFromNotStarted = issueRepository
                    .totalIssueTypeJiraCountAndStoryPointsFromStatus(projectName, issueType, notStarted);
            for (Object[] element : totalIssueTypeJiraCountAndStoryPointsFromNotStarted) {
                totalIssueTypeJiraNotStartedIssueCount = ((Long) element[0]).intValue();
                totalIssueTypeJiraNotStartedIssueStoryPoint = ((Double)element[1] == null) ? 0.0 : (Double)element[1];
            }
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of bugs in Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalIssueTypeJiraBugIssueCount = issueRepository
                    .totalIssueTypeJiraSecondTypeIssueCount(projectName, issueType, bug);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of reopen Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalIssueTypeJiraReopenedIssueCount = issueRepository.totalIssueTypeJiraStatusIssueCount(
                    projectName, issueType,
                    reopened);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of critical Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalIssueTypeJiraCriticalIssueCount = issueRepository.totalIssueTypeJiraPriorityIssueCount(
                    projectName, issueType,
                    criticalPriority);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of critical not completed Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalIssueTypeJiraCriticalNotCompletedIssueCount = issueRepository
                    .totalIssueTypeJiraPriorityOppositeResolutionIssueCount(projectName, issueType,
                            criticalPriority, completed, fixed, done);
            // -----------------------------------------------------------------------------------------------------------

            // Line of code for % of cancelled Jira Issues
            // -----------------------------------------------------------------------------------------------------------
            int totalIssueTypeJiraCancelledIssueCount = issueRepository
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
                    (Double.valueOf(totalIssueTypeJiraBugIssueCount / totalIssueTypeJiraIssueCount)) * 100.0);
            OverviewKPI1.setPercentageReopenedIssues(
                    (Double.valueOf(totalIssueTypeJiraReopenedIssueCount / totalIssueTypeJiraIssueCount)) * 100.0);
            OverviewKPI1.setPercentageCriticalIssues(
                    (Double.valueOf(totalIssueTypeJiraCriticalIssueCount / totalIssueTypeJiraIssueCount)) * 100.0);
            OverviewKPI1.setPercentageCriticalIssuesNotCompleted(
                    (Double.valueOf(totalIssueTypeJiraCriticalNotCompletedIssueCount / totalIssueTypeJiraIssueCount))
                            * 100.0);
            OverviewKPI1.setPercentageCancelledIssues(
                    (Double.valueOf(totalIssueTypeJiraCancelledIssueCount / totalIssueTypeJiraIssueCount)) * 100.0);

            kpi1Repository.save(OverviewKPI1);
            listofKPI1.add(OverviewKPI1);

        }

        return listofKPI1;
    }
}
