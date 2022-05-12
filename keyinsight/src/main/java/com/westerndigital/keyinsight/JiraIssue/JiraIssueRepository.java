package com.westerndigital.keyinsight.JiraIssue;

import java.util.Optional;
import java.util.ArrayList;
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

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.subType = :subType")
    Integer totalJiraSubTypeIssueCount(@Param("projectName") String projectName, @Param("subType") String subType);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.priority = :priority")
    Integer totalJiraPriorityIssueCount(@Param("projectName") String projectName, @Param("priority") String priority);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.priority = :priority AND j.resolution = :resolution")
    Integer totalJiraPriorityResolutionIssueCount(@Param("projectName") String projectName, @Param("priority") String priority, @Param("resolution") String resolution);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.priority = :priority AND (j.resolution NOT IN(:resolution1, :resolution2, :resolution3) OR j.resolution is null)")
    Integer totalJiraPriorityOppositeResolutionIssueCount(@Param("projectName") String projectName, @Param("priority") String priority, @Param("resolution1") String resolution1,@Param("resolution2") String resolution2,@Param("resolution3") String resolution3);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.resolution = :resolution")
    Integer totalJiraResolutionIssueCount(@Param("projectName") String projectName, @Param("resolution") String resolution);

    //with teamtype queries
    @Query(value = "SELECT DISTINCT(j.teamType) FROM JiraIssue j WHERE j.projectName = :projectName")
    List<String> getAllTeamType(@Param("projectName") String projectName);

    @Query(value = "SELECT COUNT(j.id), COALESCE(SUM(j.storyPoint),0) FROM JiraIssue j WHERE j.projectName = :projectName AND j.teamType = :teamType")
    List<Object[]> totalTeamTypeJiraCountAndStoryPoints(@Param("projectName") String projectName, @Param("teamType") String teamType);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.teamType = :teamType AND j.status = :status")
    Integer totalTeamTypeJiraStatusIssueCount(@Param("projectName") String projectName, @Param("teamType") String teamType, @Param("status") String status);

    @Query(value = "SELECT COUNT(j.id), COALESCE(SUM(j.storyPoint),0) FROM JiraIssue j WHERE j.projectName = :projectName AND j.teamType = :teamType AND j.status = :status")
    List<Object[]> totalTeamTypeJiraCountAndStoryPointsFromStatus(@Param("projectName") String projectName, @Param("teamType") String teamType, @Param("status") String status);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.teamType = :teamType AND j.subType = :subType")
    Integer totalTeamTypeJiraSubTypeIssueCount(@Param("projectName") String projectName, @Param("teamType") String teamType, @Param("subType") String subType);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.teamType = :teamType AND j.priority = :priority")
    Integer totalTeamTypeJiraPriorityIssueCount(@Param("projectName") String projectName, @Param("teamType") String teamType, @Param("priority") String priority);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.teamType = :teamType AND j.priority = :priority AND j.resolution = :resolution")
    Integer totalTeamTypeJiraPriorityResolutionIssueCount(@Param("projectName") String projectName, @Param("teamType") String teamType, @Param("priority") String priority, @Param("resolution") String resolution);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.teamType = :teamType AND j.priority = :priority AND (j.resolution NOT IN(:resolution1, :resolution2, :resolution3) OR j.resolution is null)")
    Integer totalTeamTypeJiraPriorityOppositeResolutionIssueCount(@Param("projectName") String projectName, @Param("teamType") String teamType, @Param("priority") String priority, @Param("resolution1") String resolution1,@Param("resolution2") String resolution2,@Param("resolution3") String resolution3);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.teamType = :teamType AND j.resolution = :resolution")
    Integer totalTeamTypeJiraResolutionIssueCount(@Param("projectName") String projectName, @Param("teamType") String teamType, @Param("resolution") String resolution);

    @Query(value = "SELECT EXTRACT(DAY FROM j.resolutionDateTime - j.createdDateTime) as intervalDays FROM JiraIssue j WHERE j.projectName = :projectName AND j.resolutionDateTime is not null AND j.teamType = :teamType ORDER BY intervalDays ASC")
    List<Integer> daysNeededToCompleteTeamTypeJiraIssues(@Param("projectName") String projectName, @Param("teamType") String teamType);

    @Query(value = "SELECT EXTRACT(DAY FROM j.resolutionDateTime - j.createdDateTime) as intervalDays FROM JiraIssue j WHERE j.projectName = :projectName AND j.resolutionDateTime is not null ORDER BY intervalDays ASC")
    List<Integer> daysNeededToCompleteTotalJiraIssues(@Param("projectName") String projectName);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND dueDateTime <= CURRENT_TIMESTAMP and j.resolution is null")
    Integer unfinishedJiraIssuesByToday(@Param("projectName") String projectName);

    @Query(value = "SELECT id, DATE(due_date_time) FROM issues WHERE project_name =?1 AND due_date_time <= CURRENT_TIMESTAMP and resolution is null ORDER BY due_date_time ASC LIMIT ?2", nativeQuery = true)
    List<Object[]> topXUnifinishedJiraIssuesByToday(@Param("projectName") String projectName, @Param("limitNumber") Integer limitNumber);

    @Query(value = "SELECT COUNT(id) FROM issues WHERE project_name = ?1 AND priority = ?2 AND updated_date_time <= NOW() - (INTERVAL '1 DAYS') * ?3", nativeQuery = true)
    Integer criticalIssuesNotUpdatedCount(@Param("projectName") String projectName, @Param("priority") String priority, @Param("interval") Integer interval);

    @Query(value = "SELECT id, DATE(updated_date_time) FROM issues WHERE project_name = ?1 AND priority = ?2 AND updated_date_time <= NOW() - (INTERVAL '1 DAYS') * ?3 ORDER BY updated_date_time ASC LIMIT ?4", nativeQuery = true)
    List<Object[]> criticalIssuesNotUpdatedInfo(@Param("projectName") String projectName, @Param("priority") String priority, @Param("interval") Integer interval, @Param("limitNumber") Integer limitNumber);

    @Query(value = "WITH created AS(SELECT TO_CHAR(created_date_time, 'YYYY-MM') AS created_month, COUNT(id) AS createdJiraCount, SUM(story_point) AS createdJiraStoryPoints FROM issues WHERE project_name =?1 GROUP BY created_month ORDER BY created_month),"+
        "resolved AS(SELECT TO_CHAR(resolution_date_time, 'YYYY-MM') AS resolved_month,  COUNT(id) AS resolvedJiraCount, SUM(story_point) AS resolvedJiraStoryPoints FROM issues WHERE project_name =?1 AND resolution_date_time is not null GROUP BY resolved_month ORDER BY resolved_month)" +
        "SELECT created.created_month, resolved.resolved_month, created.createdJiraCount, created.createdJiraStoryPoints, resolved.resolvedJiraCount, resolved.resolvedJiraStoryPoints FROM created LEFT JOIN resolved ON created.created_month = resolved.resolved_month", nativeQuery = true)
    List<Object[]> numberOfIssuesCreatedAndResolvedInAMonth(@Param("projectName") String projectName);

    @Query(value = "WITH created AS(SELECT TO_CHAR(created_date_time, 'YYYY-MM') AS created_month, COUNT(id) AS createdJiraCount, SUM(story_point) AS createdJiraStoryPoints FROM issues WHERE project_name =?1 AND team_type =?2 GROUP BY created_month ORDER BY created_month),"+
        "resolved AS(SELECT TO_CHAR(resolution_date_time, 'YYYY-MM') AS resolved_month,  COUNT(id) AS resolvedJiraCount, SUM(story_point) AS resolvedJiraStoryPoints FROM issues WHERE project_name =?1 AND team_type =?2 AND resolution_date_time is not null GROUP BY resolved_month ORDER BY resolved_month)" +
        "SELECT created.created_month, resolved.resolved_month, created.createdJiraCount, created.createdJiraStoryPoints, resolved.resolvedJiraCount, resolved.resolvedJiraStoryPoints FROM created LEFT JOIN resolved ON created.created_month = resolved.resolved_month", nativeQuery = true)
    List<Object[]> numberOfIssuesCreatedAndResolvedInAMonthByTeamType(@Param("projectName") String projectName, @Param("teamType") String teamType);

    @Query(value = "WITH total AS(SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint "+
    "FROM issues WHERE project_name =?1 AND team_type =?2 AND assignee is not null GROUP BY assignee ORDER BY assignee ASC), "+ 
    "completed AS(SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint "+
    "FROM issues WHERE project_name =?1 AND team_type =?2 AND assignee is not null AND resolution is not null GROUP BY assignee ORDER BY assignee ASC), "+
    "wip AS(SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint "+ 
    "FROM issues WHERE project_name =?1 AND team_type =?2 AND status = ?3 AND assignee is not null GROUP BY assignee ORDER BY assignee ASC), "+
    "notstarted AS(SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint "+ 
    "FROM issues WHERE project_name =?1 AND team_type =?2 AND status = ?4 AND assignee is not null GROUP BY assignee ORDER BY assignee ASC), "+
    "criticalnotstarted AS(SELECT DISTINCT(assignee) AS assignee, COUNT(id) as count, SUM(story_point) as storypoint "+ 
    "FROM issues WHERE project_name =?1 AND team_type =?2 AND status = ?4 AND priority = ?5 AND assignee is not null GROUP BY assignee ORDER BY assignee ASC) "+
    "SELECT total.assignee, COALESCE(total.count,0) AS totalCount, COALESCE(total.storypoint,0) AS totalStoryPoint, COALESCE(completed.count,0) AS completedCount, "+
    "COALESCE(completed.storypoint,0) AS completedStoryPoint, COALESCE(wip.count,0) AS wipCount, COALESCE(wip.storypoint,0) AS wipStoryPoint, "+
    "COALESCE(notstarted.count,0) AS notstartedCount,COALESCE(notstarted.storypoint,0) AS notstartedStoryPoint, COALESCE(criticalnotstarted.count,0) AS criticalnotstartedCount, "+
    "COALESCE(criticalnotstarted.storypoint,0) AS criticalnotstartedStoryPoint FROM total LEFT JOIN completed on total.assignee = completed.assignee "+ 
    "LEFT JOIN wip on total.assignee = wip.assignee LEFT JOIN notstarted on total.assignee = notstarted.assignee LEFT JOIN criticalnotstarted on total.assignee = criticalnotstarted.assignee ",nativeQuery = true)
    List<Object[]> assigneeTotalCompleteInformation(@Param("projectName") String projectName, @Param("teamType") String teamType, @Param("status1") String status1, @Param("status2") String status2, @Param("priority") String priority);
}

//https://www.baeldung.com/spring-data-jpa-query
