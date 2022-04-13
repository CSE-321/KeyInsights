package com.westerndigital.keyinsight.JiraIssue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraIssueRepository extends JpaRepository<JiraIssue, Integer> {
    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j")
    Integer totalJiraIssueCount();

    @Query(value = "SELECT SUM(j.storyPoint) FROM JiraIssue j")
    Optional<Float> totalJiraIssueStoryPoint();

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.teamType = ?1")
    Integer totalTeamTypeJiraIssueCount(String teamType);

    @Query(value = "SELECT SUM(j.storyPoint) FROM JiraIssue j WHERE j.teamType = ?1")
    Optional<Float> totalTeamTypeJiraIssueStoryPoint(String teamType);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.teamType = ?1 AND j.status = ?2")
    Integer totalTeamTypeJiraStatusIssueCount(String teamType, String status);

    @Query(value = "SELECT SUM(j.storyPoint) FROM JiraIssue j WHERE j.teamType = ?1 AND j.status = ?2")
    Optional<Float> totalTeamTypeJiraStatusIssueStoryPoint(String teamType, String status);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.teamType = ?1 AND j.subType = ?2")
    Integer totalTeamTypeJiraSubTypeIssueCount(String teamType, String subType);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.teamType = ?1 AND j.priority = ?2")
    Integer totalTeamTypeJiraPriorityIssueCount(String teamType, String priority);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.teamType = ?1 AND j.priority = ?2 AND j.resolution = ?3")
    Integer totalTeamTypeJiraPriorityResolutionIssueCount(String teamType, String priority, String resolution);

    @Query(value = "SELECT COUNT(j.id) FROM JiraIssue j WHERE j.teamType = ?1 AND j.resolution = ?2")
    Integer totalTeamTypeJiraResolutionIssueCount(String teamType, String resolution);


}
