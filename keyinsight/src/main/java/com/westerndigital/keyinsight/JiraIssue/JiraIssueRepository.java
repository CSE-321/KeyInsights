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
    @Query(value = "SELECT COUNT(j.id), SUM(j.storyPoint) FROM JiraIssue j WHERE j.projectName = :projectName")
    ArrayList<Object[]> totalJiraCountAndStoryPoints(@Param("projectName") String projectName);
    
    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.status = :status")
    Integer totalJiraStatusIssueCount(@Param("projectName") String projectName, @Param("status") String status);

    @Query(value = "SELECT COUNT(j.id), SUM(j.storyPoint) FROM JiraIssue j WHERE j.projectName = :projectName AND j.status = :status")
    ArrayList<Object[]> totalJiraCountAndStoryPointsFromStatus(@Param("projectName") String projectName, @Param("status") String status);

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
    ArrayList<String> getAllIssueType(@Param("projectName") String projectName);

    @Query(value = "SELECT COUNT(j.id), SUM(j.storyPoint) FROM JiraIssue j WHERE j.projectName = :projectName AND j.issueType = :issueType")
    ArrayList<Object[]> totalIssueTypeJiraCountAndStoryPoints(@Param("projectName") String projectName, @Param("issueType") String issueType);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND j.issueType = :issueType AND j.status = :status")
    Integer totalIssueTypeJiraStatusIssueCount(@Param("projectName") String projectName, @Param("issueType") String issueType, @Param("status") String status);

    @Query(value = "SELECT COUNT(j.id), SUM(j.storyPoint) FROM JiraIssue j WHERE j.projectName = :projectName AND j.issueType = :issueType AND j.status = :status")
    ArrayList<Object[]> totalIssueTypeJiraCountAndStoryPointsFromStatus(@Param("projectName") String projectName, @Param("issueType") String issueType, @Param("status") String status);

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
    ArrayList<Integer> daysNeededToCompleteIssueTypeJiraIssues(@Param("projectName") String projectName, @Param("issueType") String issueType);

    @Query(value = "SELECT EXTRACT(DAY FROM j.resolutionDateTime - j.createdDateTime) as intervalDays FROM JiraIssue j WHERE j.projectName = :projectName AND j.resolutionDateTime is not null ORDER BY intervalDays ASC")
    ArrayList<Integer> daysNeededToCompleteTotalJiraIssues(@Param("projectName") String projectName);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.projectName = :projectName AND dueDateTime <= CURRENT_TIMESTAMP and j.resolution is null")
    Integer unfinishedJiraIssuesByToday(@Param("projectName") String projectName);

    @Query(value = "WITH created AS(SELECT TO_CHAR(created_date_time, 'YYYY-MM') AS created_month, COUNT(id) AS createdJiraCount, SUM(story_point) AS createdJiraStoryPoints FROM issues WHERE project_name =?1 GROUP BY created_month ORDER BY created_month),"+
        "resolved AS(SELECT TO_CHAR(resolution_date_time, 'YYYY-MM') AS resolved_month,  COUNT(id) AS resolvedJiraCount, SUM(story_point) AS resolvedJiraStoryPoints FROM issues WHERE project_name =?1 AND resolution_date_time is not null GROUP BY resolved_month ORDER BY resolved_month)" +
        "SELECT created.created_month, resolved.resolved_month, created.createdJiraCount, created.createdJiraStoryPoints, resolved.resolvedJiraCount, resolved.resolvedJiraStoryPoints FROM created LEFT JOIN resolved ON created.created_month = resolved.resolved_month", nativeQuery = true)
    ArrayList<Object[]> numberOfIssuesCreatedAndResolvedInAMonth(@Param("projectName") String projectName);

    @Query(value = "WITH created AS(SELECT TO_CHAR(created_date_time, 'YYYY-MM') AS created_month, COUNT(id) AS createdJiraCount, SUM(story_point) AS createdJiraStoryPoints FROM issues WHERE project_name =?1 AND issue_type =?2 GROUP BY created_month ORDER BY created_month),"+
        "resolved AS(SELECT TO_CHAR(resolution_date_time, 'YYYY-MM') AS resolved_month,  COUNT(id) AS resolvedJiraCount, SUM(story_point) AS resolvedJiraStoryPoints FROM issues WHERE project_name =?1 AND issue_type =?2 AND resolution_date_time is not null GROUP BY resolved_month ORDER BY resolved_month)" +
        "SELECT created.created_month, resolved.resolved_month, created.createdJiraCount, created.createdJiraStoryPoints, resolved.resolvedJiraCount, resolved.resolvedJiraStoryPoints FROM created LEFT JOIN resolved ON created.created_month = resolved.resolved_month", nativeQuery = true)
    ArrayList<Object[]> numberOfIssuesCreatedAndResolvedInAMonthByIssueType(@Param("projectName") String projectName, @Param("issueType") String issueType);
}
