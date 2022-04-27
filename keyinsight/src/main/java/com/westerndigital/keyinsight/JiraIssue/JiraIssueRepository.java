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

    @Query(value = "SELECT EXTRACT(DAY FROM j.resolutionDateTime - j.createdDateTime) as intervalDays FROM JiraIssue j WHERE j.projectName = :projectName AND j.resolutionDateTime is not null AND j.status in (:status1) ORDER BY intervalDays ASC")
    ArrayList<Integer> medianOfOpenResolvedJiraIssues(@Param("projectName") String projectName, @Param("status1") String status1);

    /* This calculates the median between jira starting and jira ending
WITH cse AS (
SELECT EXTRACT(DAY FROM resolution_date_time - created_date_time) as intervalDays
FROM issues
WHERE project_name = 'B8X4' AND resolution_date_time is not null
)

SELECT PERCENTILE_DISC(0.5) WITHIN GROUP(ORDER BY intervalDays)
FROM cse
    */

    /* this query finds issues created between 2 dates
SELECT *
FROM issues
WHERE project_name = 'B8X4' AND created_date_time >= '2020-11-01' AND created_date_time < '2021-12-31'
    */

    /* Count for jira issues resolved within last 7 days
SELECT COUNT(id)
FROM issues
WHERE project_name = 'B8X4'
AND status in ('Resolved')
AND resolution_date_time > current_date - interval '7 days'
    */

    /*Count for jira issues resolved
SELECT COUNT(id)
FROM issues
WHERE project_name = 'B8X4'
AND status in ('Resolved')
    */

    /*Story point sfor jira issue solved
SELECT SUM(story_point)
FROM issues
WHERE project_name = 'B8X4'
AND status in ('Resolved')
    */




}
