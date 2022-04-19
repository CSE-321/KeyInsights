package com.westerndigital.keyinsight.JiraIssue;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraIssueRepository extends JpaRepository<JiraIssue, Integer> {

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


}
