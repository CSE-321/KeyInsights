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

        public KPI1Service(JiraIssueRepository issueRepository) {
                this.issueRepository = issueRepository;

        }

        public List<KPI1> getKPI1PerTeam() {
                ArrayList<KPI1> listofKPI1 = new ArrayList<KPI1>();
                String projectName = "B8X4";
                List<String> teamtypes = issueRepository.getAllTeamType(projectName);
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

                KPI1 OverviewKPI1 = new KPI1();

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
                int totalJiraIssueCount = issueRepository.totalJiraIssueCount(projectName);
                Float totalJiraIssueStoryPoint = issueRepository
                                .totalJiraIssueStoryPoint(projectName)
                                .orElse(1.0f);
                // ----------------------------------------------------------------------------------------------------

                // Block of code for Closed Jira# and story points and % of total story points
                // ----------------------------------------------------------------------------------------------------------
                int totalJiraClosedIssueCount = issueRepository.totalJiraStatusIssueCount(projectName,
                                closed);
                Float totalJiraClosedIssueStoryPoint = issueRepository
                                .totalJiraStatusIssueStoryPoint(projectName, closed).orElse(0.0f);
                // ----------------------------------------------------------------------------------------------------------

                // Block of code for WIP Jira# and story points and % of total story points
                // -----------------------------------------------------------------------------------------------------------
                int totalJiraWIPIssueCount = issueRepository.totalJiraStatusIssueCount(projectName,
                                wip);
                Float totalJiraWIPIssueStoryPoint = issueRepository
                                .totalJiraStatusIssueStoryPoint(projectName, wip).orElse(0.0f);
                // ------------------------------------------------------------------------------------------------------------

                // Block of code for Not Started Jira# and story points and % of total story
                // points
                // -----------------------------------------------------------------------------------------------------------
                int totalJiraNotStartedIssueCount = issueRepository.totalJiraStatusIssueCount(
                                projectName, 
                                notStarted);
                Float totalJiraNotStartedIssueStoryPoint = issueRepository
                                .totalJiraStatusIssueStoryPoint(projectName, notStarted).orElse(0.0f);
                // -----------------------------------------------------------------------------------------------------------

                // Line of code for % of bugs in Jira Issues
                // -----------------------------------------------------------------------------------------------------------
                int totalJiraBugIssueCount = issueRepository.totalJiraSubTypeIssueCount(projectName,
                                bug);
                // -----------------------------------------------------------------------------------------------------------

                // Line of code for % of reopen Jira Issues
                // -----------------------------------------------------------------------------------------------------------
                int totalJiraReopenedIssueCount = issueRepository.totalJiraStatusIssueCount(projectName,
                                
                                reopened);
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
                OverviewKPI1.setTeamType("All Jira Issues");
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
                OverviewKPI1.setPercentageBugs(((float) totalJiraBugIssueCount / totalJiraIssueCount) * 100.0f);
                OverviewKPI1.setPercentageReopenedIssues(
                                ((float) totalJiraReopenedIssueCount / totalJiraIssueCount) * 100);
                OverviewKPI1.setPercentageCriticalIssues(
                                ((float) totalJiraCriticalIssueCount / totalJiraIssueCount) * 100);
                OverviewKPI1.setPercentageCriticalIssuesNotCompleted(
                                ((float) totalJiraCriticalNotCompletedIssueCount / totalJiraIssueCount)
                                                * 100);
                OverviewKPI1.setPercentageCancelledIssues(
                                ((float) totalJiraCancelledIssueCount / totalJiraIssueCount) * 100);
                
                listofKPI1.add(OverviewKPI1);

                for (String teamType : teamtypes) {
                        OverviewKPI1 = new KPI1();

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
                        int totalTeamTypeJiraIssueCount = issueRepository.totalTeamTypeJiraIssueCount(projectName,
                                        teamType);
                        Float totalTeamTypeJiraIssueStoryPoint = issueRepository
                                        .totalTeamTypeJiraIssueStoryPoint(projectName, teamType)
                                        .orElse(1.0f);
                        // ----------------------------------------------------------------------------------------------------

                        // Block of code for Closed Jira# and story points and % of total story points
                        // ----------------------------------------------------------------------------------------------------------
                        int totalTeamTypeJiraClosedIssueCount = issueRepository
                                        .totalTeamTypeJiraStatusIssueCount(projectName, teamType, closed);
                        Float totalTeamTypeJiraClosedIssueStoryPoint = issueRepository
                                        .totalTeamTypeJiraStatusIssueStoryPoint(projectName, teamType, closed)
                                        .orElse(0.0f);
                        // ----------------------------------------------------------------------------------------------------------

                        // Block of code for WIP Jira# and story points and % of total story points
                        // -----------------------------------------------------------------------------------------------------------
                        int totalTeamTypeJiraWIPIssueCount = issueRepository
                                        .totalTeamTypeJiraStatusIssueCount(projectName, teamType, wip);
                        Float totalTeamTypeJiraWIPIssueStoryPoint = issueRepository
                                        .totalTeamTypeJiraStatusIssueStoryPoint(projectName, teamType, wip)
                                        .orElse(0.0f);
                        // ------------------------------------------------------------------------------------------------------------

                        // Block of code for Not Started Jira# and story points and % of total story
                        // points
                        // -----------------------------------------------------------------------------------------------------------
                        int totalTeamTypeJiraNotStartedIssueCount = issueRepository.totalTeamTypeJiraStatusIssueCount(
                                        projectName, teamType,
                                        notStarted);
                        Float totalTeamTypeJiraNotStartedIssueStoryPoint = issueRepository
                                        .totalTeamTypeJiraStatusIssueStoryPoint(projectName, teamType, notStarted)
                                        .orElse(0.0f);
                        // -----------------------------------------------------------------------------------------------------------

                        // Line of code for % of bugs in Jira Issues
                        // -----------------------------------------------------------------------------------------------------------
                        int totalTeamTypeJiraBugIssueCount = issueRepository
                                        .totalTeamTypeJiraSubTypeIssueCount(projectName, teamType, bug);
                        // -----------------------------------------------------------------------------------------------------------

                        // Line of code for % of reopen Jira Issues
                        // -----------------------------------------------------------------------------------------------------------
                        int totalTeamTypeJiraReopenedIssueCount = issueRepository.totalTeamTypeJiraStatusIssueCount(
                                        projectName, teamType,
                                        reopened);
                        // -----------------------------------------------------------------------------------------------------------

                        // Line of code for % of critical Jira Issues
                        // -----------------------------------------------------------------------------------------------------------
                        int totalTeamTypeJiraCriticalIssueCount = issueRepository.totalTeamTypeJiraPriorityIssueCount(
                                        projectName, teamType,
                                        criticalPriority);
                        // -----------------------------------------------------------------------------------------------------------

                        // Line of code for % of critical not completed Jira Issues
                        // -----------------------------------------------------------------------------------------------------------
                        int totalTeamTypeJiraCriticalNotCompletedIssueCount = issueRepository
                                        .totalTeamTypeJiraPriorityOppositeResolutionIssueCount(projectName, teamType,
                                                        criticalPriority, completed, fixed, done);
                        // -----------------------------------------------------------------------------------------------------------

                        // Line of code for % of cancelled Jira Issues
                        // -----------------------------------------------------------------------------------------------------------
                        int totalTeamTypeJiraCancelledIssueCount = issueRepository
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
                                        (totalTeamTypeJiraNotStartedIssueStoryPoint / totalTeamTypeJiraIssueStoryPoint)
                                                        * 100.0f);
                        OverviewKPI1.setPercentageBugs(((float) totalTeamTypeJiraBugIssueCount / totalTeamTypeJiraIssueCount)
                                        * 100.0f);
                        OverviewKPI1.setPercentageReopenedIssues(
                                        ((float) totalTeamTypeJiraReopenedIssueCount / totalTeamTypeJiraIssueCount)
                                                        * 100);
                        OverviewKPI1.setPercentageCriticalIssues(
                                        ((float) totalTeamTypeJiraCriticalIssueCount / totalTeamTypeJiraIssueCount)
                                                        * 100);
                        OverviewKPI1.setPercentageCriticalIssuesNotCompleted(
                                        ((float) totalTeamTypeJiraCriticalNotCompletedIssueCount
                                                        / totalTeamTypeJiraIssueCount)
                                                        * 100);
                        OverviewKPI1.setPercentageCancelledIssues(
                                        ((float) totalTeamTypeJiraCancelledIssueCount / totalTeamTypeJiraIssueCount)
                                                        * 100);

                        listofKPI1.add(OverviewKPI1);

                }

                return listofKPI1;
        }

        public void tmp() {
                ArrayList<Integer> listOfMedianValues = issueRepository.medianOfOpenResolvedJiraIssues("B8X4",
                                "Resolved");
                double median = 0;
                if (listOfMedianValues.size() % 2 != 0) {
                        median = (double) listOfMedianValues.get(listOfMedianValues.size() / 2);
                } else {
                        median = (double) (listOfMedianValues.get((listOfMedianValues.size() - 1) / 2)
                                        + listOfMedianValues.get(listOfMedianValues.size() / 2)) / 2.0;
                }
                System.out.println(median);
        }
}
