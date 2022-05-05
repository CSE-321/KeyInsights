package com.westerndigital.keyinsight.JiraIssue;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraIssueRepository extends JpaRepository<JiraIssue, Integer> {

    //no teamtype queries
    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName")
    Integer totalJiraIssueCount(@Param("projectName") String projectName);

    @Query(value = "SELECT SUM(j.storyPoint) FROM JiraIssue j WHERE j.projectName = :projectName")
    Optional<Float> totalJiraIssueStoryPoint(@Param("projectName") String projectName);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.status = :status")
    Integer totalJiraStatusIssueCount(@Param("projectName") String projectName, @Param("status") String status);

    @Query(value = "SELECT SUM(j.storyPoint) FROM JiraIssue j WHERE j.projectName = :projectName AND j.status = :status")
    Optional<Float> totalJiraStatusIssueStoryPoint(@Param("projectName") String projectName, @Param("status") String status);

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

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.teamType = :teamType")
    Integer totalTeamTypeJiraIssueCount(@Param("projectName") String projectName, @Param("teamType") String teamType);

    @Query(value = "SELECT SUM(j.storyPoint) FROM JiraIssue j WHERE j.projectName = :projectName AND j.teamType = :teamType")
    Optional<Float> totalTeamTypeJiraIssueStoryPoint(@Param("projectName") String projectName, @Param("teamType") String teamType);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.teamType = :teamType AND j.status = :status")
    Integer totalTeamTypeJiraStatusIssueCount(@Param("projectName") String projectName, @Param("teamType") String teamType, @Param("status") String status);

    @Query(value = "SELECT SUM(j.storyPoint) FROM JiraIssue j WHERE j.projectName = :projectName AND j.teamType = :teamType AND j.status = :status")
    Optional<Float> totalTeamTypeJiraStatusIssueStoryPoint(@Param("projectName") String projectName, @Param("teamType") String teamType, @Param("status") String status);

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
    ArrayList<Integer> daysNeededToCompleteTeamTypeJiraIssues(@Param("projectName") String projectName, @Param("teamType") String teamType);

    @Query(value = "SELECT EXTRACT(DAY FROM j.resolutionDateTime - j.createdDateTime) as intervalDays FROM JiraIssue j WHERE j.projectName = :projectName AND j.resolutionDateTime is not null ORDER BY intervalDays ASC")
    ArrayList<Integer> daysNeededToCompleteTotalJiraIssues(@Param("projectName") String projectName);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND dueDateTime <= CURRENT_TIMESTAMP and j.resolution is null")
    Integer unfinishedJiraIssuesByToday(@Param("projectName") String projectName);

    @Query(value = "WITH created AS(SELECT TO_CHAR(created_date_time, 'YYYY-MM') AS created_month, COUNT(id) AS createdJiraCount, SUM(story_point) AS createdJiraStoryPoints FROM issues WHERE project_name =?1 GROUP BY created_month ORDER BY created_month),"+
        "resolved AS(SELECT TO_CHAR(resolution_date_time, 'YYYY-MM') AS resolved_month,  COUNT(id) AS resolvedJiraCount, SUM(story_point) AS resolvedJiraStoryPoints FROM issues WHERE project_name =?1 AND resolution_date_time is not null GROUP BY resolved_month ORDER BY resolved_month)" +
        "SELECT created.created_month, resolved.resolved_month, created.createdJiraCount, created.createdJiraStoryPoints, resolved.resolvedJiraCount, resolved.resolvedJiraStoryPoints FROM created LEFT JOIN resolved ON created.created_month = resolved.resolved_month", nativeQuery = true)
    ArrayList<Object[]> numberOfIssuesCreatedAndResolvedInAMonth(@Param("projectName") String projectName);

    @Query(value = "WITH created AS(SELECT TO_CHAR(created_date_time, 'YYYY-MM') AS created_month, COUNT(id) AS createdJiraCount, SUM(story_point) AS createdJiraStoryPoints FROM issues WHERE project_name =?1 AND team_type =?2 GROUP BY created_month ORDER BY created_month),"+
        "resolved AS(SELECT TO_CHAR(resolution_date_time, 'YYYY-MM') AS resolved_month,  COUNT(id) AS resolvedJiraCount, SUM(story_point) AS resolvedJiraStoryPoints FROM issues WHERE project_name =?1 AND team_type =?2 AND resolution_date_time is not null GROUP BY resolved_month ORDER BY resolved_month)" +
        "SELECT created.created_month, resolved.resolved_month, created.createdJiraCount, created.createdJiraStoryPoints, resolved.resolvedJiraCount, resolved.resolvedJiraStoryPoints FROM created LEFT JOIN resolved ON created.created_month = resolved.resolved_month", nativeQuery = true)
    ArrayList<Object[]> numberOfIssuesCreatedAndResolvedInAMonthByTeamType(@Param("projectName") String projectName, @Param("teamType") String teamType);
}
