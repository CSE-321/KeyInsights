package com.westerndigital.keyinsight.JiraIssue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraIssueRepository extends JpaRepository<JiraIssue, String> {

    //no teamtype queries
    @Query(value = "SELECT COUNT(j.id), COALESCE(SUM(j.storyPoint),0) FROM JiraIssue j WHERE j.projectName = :projectName")
    List<Object[]> totalJiraCountAndStoryPoints(@Param("projectName") String projectName);
    
    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.status = :status")
    Integer totalJiraStatusIssueCount(@Param("projectName") String projectName, @Param("status") String status);

    @Query(value = "SELECT COUNT(j.id), SUM(j.storyPoint) FROM JiraIssue j WHERE j.projectName = :projectName AND j.status = :status")
    List<Object[]> totalJiraCountAndStoryPointsFromStatus(@Param("projectName") String projectName, @Param("status") String status);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.secondType = :secondType")
    Integer totalJiraSecondTypeIssueCount(@Param("projectName") String projectName, @Param("secondType") String secondType);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.priority = :priority")
    Integer totalJiraPriorityIssueCount(@Param("projectName") String projectName, @Param("priority") String priority);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.priority = :priority AND j.resolution = :resolution")
    Integer totalJiraPriorityResolutionIssueCount(@Param("projectName") String projectName, @Param("priority") String priority, @Param("resolution") String resolution);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.priority = :priority AND (j.resolution NOT IN(:resolution1, :resolution2, :resolution3) OR j.resolution is null)")
    Integer totalJiraPriorityOppositeResolutionIssueCount(@Param("projectName") String projectName, @Param("priority") String priority, @Param("resolution1") String resolution1,@Param("resolution2") String resolution2,@Param("resolution3") String resolution3);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.resolution = :resolution")
    Integer totalJiraResolutionIssueCount(@Param("projectName") String projectName, @Param("resolution") String resolution);

    //with teamtype queries
    @Query(value = "SELECT DISTINCT(j.issueType) FROM JiraIssue j WHERE j.projectName = :projectName")
    List<String> getAllIssueType(@Param("projectName") String projectName);

    @Query(value = "SELECT COUNT(j.id), COALESCE(SUM(j.storyPoint),0) FROM JiraIssue j WHERE j.projectName = :projectName AND j.issueType = :issueType")
    List<Object[]> totalIssueTypeJiraCountAndStoryPoints(@Param("projectName") String projectName, @Param("issueType") String issueType);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.issueType = :issueType AND j.status = :status")
    Integer totalIssueTypeJiraStatusIssueCount(@Param("projectName") String projectName, @Param("issueType") String issueType, @Param("status") String status);

    @Query(value = "SELECT COUNT(j.id), COALESCE(SUM(j.storyPoint),0) FROM JiraIssue j WHERE j.projectName = :projectName AND j.issueType = :issueType AND j.status = :status")
    List<Object[]> totalIssueTypeJiraCountAndStoryPointsFromStatus(@Param("projectName") String projectName, @Param("issueType") String issueType, @Param("status") String status);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.issueType = :issueType AND j.secondType = :secondType")
    Integer totalIssueTypeJiraSecondTypeIssueCount(@Param("projectName") String projectName, @Param("issueType") String issueType, @Param("secondType") String secondType);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.issueType = :issueType AND j.priority = :priority")
    Integer totalIssueTypeJiraPriorityIssueCount(@Param("projectName") String projectName, @Param("issueType") String issueType, @Param("priority") String priority);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.issueType = :issueType AND j.priority = :priority AND j.resolution = :resolution")
    Integer totalIssueTypeJiraPriorityResolutionIssueCount(@Param("projectName") String projectName, @Param("issueType") String issueType, @Param("priority") String priority, @Param("resolution") String resolution);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.issueType = :issueType AND j.priority = :priority AND (j.resolution NOT IN(:resolution1, :resolution2, :resolution3) OR j.resolution is null)")
    Integer totalIssueTypeJiraPriorityOppositeResolutionIssueCount(@Param("projectName") String projectName, @Param("issueType") String issueType, @Param("priority") String priority, @Param("resolution1") String resolution1,@Param("resolution2") String resolution2,@Param("resolution3") String resolution3);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.issueType = :issueType AND j.resolution = :resolution")
    Integer totalIssueTypeJiraResolutionIssueCount(@Param("projectName") String projectName, @Param("issueType") String issueType, @Param("resolution") String resolution);

    @Query(value = "SELECT EXTRACT(DAY FROM j.resolutionDateTime - j.createdDateTime) as intervalDays FROM JiraIssue j WHERE j.projectName = :projectName AND j.resolutionDateTime is not null AND j.issueType = :issueType ORDER BY intervalDays ASC")
    List<Integer> daysNeededToCompleteIssueTypeJiraIssues(@Param("projectName") String projectName, @Param("issueType") String issueType);

    @Query(value = "SELECT EXTRACT(DAY FROM j.resolutionDateTime - j.createdDateTime) as intervalDays FROM JiraIssue j WHERE j.projectName = :projectName AND j.resolutionDateTime is not null ORDER BY intervalDays ASC")
    List<Integer> daysNeededToCompleteTotalJiraIssues(@Param("projectName") String projectName);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND dueDateTime <= CURRENT_TIMESTAMP and j.resolution is null")
    Integer unfinishedJiraIssuesByToday(@Param("projectName") String projectName);

    @Query(value = "WITH created AS(SELECT TO_CHAR(created_date_time, 'YYYY-MM') AS created_month, COUNT(id) AS createdJiraCount, SUM(story_point) AS createdJiraStoryPoints FROM issues WHERE project_name =?1 GROUP BY created_month ORDER BY created_month),"+
        "resolved AS(SELECT TO_CHAR(resolution_date_time, 'YYYY-MM') AS resolved_month,  COUNT(id) AS resolvedJiraCount, SUM(story_point) AS resolvedJiraStoryPoints FROM issues WHERE project_name =?1 AND resolution_date_time is not null GROUP BY resolved_month ORDER BY resolved_month)" +
        "SELECT created.created_month, resolved.resolved_month, created.createdJiraCount, created.createdJiraStoryPoints, resolved.resolvedJiraCount, resolved.resolvedJiraStoryPoints FROM created LEFT JOIN resolved ON created.created_month = resolved.resolved_month", nativeQuery = true)
    List<Object[]> numberOfIssuesCreatedAndResolvedInAMonth(@Param("projectName") String projectName);

    @Query(value = "WITH created AS(SELECT TO_CHAR(created_date_time, 'YYYY-MM') AS created_month, COUNT(id) AS createdJiraCount, SUM(story_point) AS createdJiraStoryPoints FROM issues WHERE project_name =?1 AND issue_type =?2 GROUP BY created_month ORDER BY created_month),"+
        "resolved AS(SELECT TO_CHAR(resolution_date_time, 'YYYY-MM') AS resolved_month,  COUNT(id) AS resolvedJiraCount, SUM(story_point) AS resolvedJiraStoryPoints FROM issues WHERE project_name =?1 AND issue_type =?2 AND resolution_date_time is not null GROUP BY resolved_month ORDER BY resolved_month)" +
        "SELECT created.created_month, resolved.resolved_month, created.createdJiraCount, created.createdJiraStoryPoints, resolved.resolvedJiraCount, resolved.resolvedJiraStoryPoints FROM created LEFT JOIN resolved ON created.created_month = resolved.resolved_month", nativeQuery = true)
    List<Object[]> numberOfIssuesCreatedAndResolvedInAMonthByIssueType(@Param("projectName") String projectName, @Param("issueType") String issueType);

    // @Query(value = "SELECT DISTINCT(j.assignee), COUNT(j.id), COALESCE(SUM(j.storyPoint), 0) FROM JiraIssue j WHERE j.assignee is not null AND j.issueType = :issueType GROUP BY assignee ORDER BY assignee ASC")
    // List<Object[]> assigneeProjectInformation(@Param("projectName") String projectName, @Param("issueType") String issueType);

    // @Query(value = "SELECT DISTINCT(j.assignee), COUNT(j.id), COALESCE(SUM(j.storyPoint), 0) FROM JiraIssue j WHERE j.assignee is not null AND j.issueType = :issueType AND j.resolution is not null GROUP BY assignee ORDER BY assignee ASC")
    // List<Object[]> assigneeCompletedProjectInformation(@Param("projectName") String projectName, @Param("issueType") String issueType);

    @Query(value = "WITH total AS(SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint "+
    "FROM issues WHERE project_name =?1 AND issue_type =?2 AND assignee is not null GROUP BY assignee ORDER BY assignee ASC), "+ 
    "completed AS(SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint "+
    "FROM issues WHERE project_name =?1 AND issue_type =?2 AND assignee is not null AND resolution is not null GROUP BY assignee ORDER BY assignee ASC), "+
    "wip AS(SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint "+ 
    "FROM issues WHERE project_name =?1 AND issue_type =?2 AND status = ?3 AND assignee is not null GROUP BY assignee ORDER BY assignee ASC), "+
    "notstarted AS(SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint "+ 
    "FROM issues WHERE project_name =?1 AND issue_type =?2 AND status = ?4 AND assignee is not null GROUP BY assignee ORDER BY assignee ASC), "+
    "criticalnotstarted AS(SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint "+ 
    "FROM issues WHERE project_name =?1 AND issue_type =?2 AND status = ?4 AND priority = ?5 AND assignee is not null GROUP BY assignee ORDER BY assignee ASC) "+
    "SELECT total.assignee, COALESCE(total.count,0) AS totalCount, COALESCE(total.storypoint,0) AS totalStoryPoint, COALESCE(completed.count,0) AS completedCount, "+
    "COALESCE(completed.storypoint,0) AS completedStoryPoint, COALESCE(wip.count,0) AS wipCount, COALESCE(wip.storypoint,0) AS wipStoryPoint, "+
    "COALESCE(notstarted.count,0) AS notstartedCount,COALESCE(notstarted.storypoint,0) AS notstartedStoryPoint, COALESCE(criticalnotstarted.count,0) AS criticalnotstartedCount, "+
    "COALESCE(criticalnotstarted.storypoint,0) AS criticalnotstartedStoryPoint FROM total LEFT JOIN completed on total.assignee = completed.assignee "+ 
    "LEFT JOIN wip on total.assignee = wip.assignee LEFT JOIN notstarted on total.assignee = notstarted.assignee LEFT JOIN criticalnotstarted on total.assignee = criticalnotstarted.assignee ",nativeQuery = true)
    List<Object[]> assigneeTotalCompleteInformation(@Param("projectName") String projectName, @Param("issueType") String issueType, @Param("status1") String status1, @Param("status2") String status2, @Param("priority") String priority);
}


/*abstract
WITH total AS(
SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint
FROM issues
WHERE assignee is not null AND issue_type = 'Logic'
GROUP BY assignee
ORDER BY assignee ASC
), completed AS(
SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint
FROM issues
WHERE assignee is not null AND issue_type = 'Logic' AND resolution is not null
GROUP BY assignee
ORDER BY assignee ASC
), wip AS(
SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint
FROM issues
WHERE assignee is not null AND issue_type = 'Logic' AND status = 'In progress'
GROUP BY assignee
ORDER BY assignee ASC
),notstarted AS(
SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint
FROM issues
WHERE assignee is not null AND issue_type = 'Logic' AND status = 'Open'
GROUP BY assignee
ORDER BY assignee ASC
), criticalnotstarted AS(
SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint
FROM issues
WHERE assignee is not null AND issue_type = 'Logic' AND status = 'open' AND priority = 'Critical'
GROUP BY assignee
ORDER BY assignee ASC
)
SELECT total.assignee, COALESCE(total.count,0) AS totalCount, COALESCE(total.storypoint,0) AS totalStoryPoint, 
COALESCE(completed.count,0) AS completedCount, COALESCE(completed.storypoint,0) AS completedStoryPoint, 
COALESCE(wip.count,0) AS wipCount, COALESCE(wip.storypoint,0) AS wipStoryPoint, 
COALESCE(notstarted.count,0) AS notstartedCount,COALESCE(notstarted.storypoint,0) AS notstartedStoryPoint, 
COALESCE(criticalnotstarted.count,0) AS criticalnotstartedCount, COALESCE(criticalnotstarted.storypoint,0) AS criticalnotstartedStoryPoint
FROM total 
LEFT JOIN completed on total.assignee = completed.assignee
LEFT JOIN wip on total.assignee = wip.assignee
LEFT JOIN notstarted on total.assignee = notstarted.assignee
LEFT JOIN criticalnotstarted on total.assignee = criticalnotstarted.assignee

*/