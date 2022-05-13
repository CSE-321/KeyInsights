package com.westerndigital.keyinsight.JiraIssue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JiraIssueService {

    @Autowired
    private JiraIssueRepository issueRepository;

    public void saveSingleIssue(JiraIssue issue) {
        issueRepository.save(issue);
    }

    public JiraIssue findById(String key) {
        return issueRepository.findById(key).orElse(new JiraIssue());
    }

    public List<String> getAllTeamType(String projectName) {
        return issueRepository.getAllTeamType(projectName);
    }

    public List<Object[]> totalJiraCountAndStoryPoints(String projectName) {
        return issueRepository.totalJiraCountAndStoryPoints(projectName);
    }

    public List<Object[]> totalJiraCountAndStoryPointsFromStatus(String projectName, String status) {
        return issueRepository.totalJiraCountAndStoryPointsFromStatus(projectName, status);
    }

    public Integer totalJiraSubTypeIssueCount(String projectName, String subType) {
        return issueRepository.totalJiraSubTypeIssueCount(projectName, subType);
    }

    public Integer totalJiraStatusIssueCount(String projectName, String status) {
        return issueRepository.totalJiraStatusIssueCount(projectName, status);
    }

    public Integer totalJiraPriorityIssueCount(String projectName, String priority) {
        return issueRepository.totalJiraPriorityIssueCount(projectName, priority);
    }

    public Integer totalJiraPriorityResolutionIssueCount(String projectName, String priority, String resolution){
        return issueRepository.totalJiraPriorityResolutionIssueCount(projectName, priority, resolution);
    }

    public Integer totalJiraPriorityOppositeResolutionIssueCount(String projectName, String priority,
            String resolution1, String resolution2, String resolution3) {
        return issueRepository.totalJiraPriorityOppositeResolutionIssueCount(projectName, priority, resolution1,
                resolution2, resolution3);
    }

    public Integer totalJiraResolutionIssueCount(String projectName, String resolution) {
        return issueRepository.totalJiraResolutionIssueCount(projectName, resolution);
    }

    public List<Object[]> totalTeamTypeJiraCountAndStoryPoints(String projectName, String teamType) {
        return issueRepository.totalTeamTypeJiraCountAndStoryPoints(projectName, teamType);
    }

    public List<Object[]> totalTeamTypeJiraCountAndStoryPointsFromStatus(String projectName, String teamType,
            String status) {
        return issueRepository.totalTeamTypeJiraCountAndStoryPointsFromStatus(projectName, teamType, status);
    }

    public Integer totalTeamTypeJiraSubTypeIssueCount(String projectName, String teamType, String subType) {
        return issueRepository.totalTeamTypeJiraSubTypeIssueCount(projectName, teamType, subType);
    }

    public Integer totalTeamTypeJiraStatusIssueCount(String projectName, String teamType, String status) {
        return issueRepository.totalTeamTypeJiraStatusIssueCount(projectName, teamType, status);
    }

    public Integer totalTeamTypeJiraPriorityIssueCount(String projectName, String teamType, String priority) {
        return issueRepository.totalTeamTypeJiraPriorityIssueCount(projectName, teamType, priority);
    }

    public Integer totalTeamTypeJiraPriorityOppositeResolutionIssueCount(String projectName, String teamType,
            String priority, String resolution1, String resolution2, String resolution3) {
        return issueRepository.totalTeamTypeJiraPriorityOppositeResolutionIssueCount(projectName, teamType, priority,
                resolution1, resolution2, resolution3);
    }

    public Integer totalTeamTypeJiraResolutionIssueCount(String projectName, String teamType, String resolution) {
        return issueRepository.totalTeamTypeJiraResolutionIssueCount(projectName, teamType, resolution);
    }

	public List<Integer> daysNeededToCompleteTotalJiraIssues(String projectName) {
		return issueRepository.daysNeededToCompleteTotalJiraIssues(projectName);
	}

	public List<Integer> daysNeededToCompleteTeamTypeJiraIssues(String projectName, String teamType) {
		return issueRepository.daysNeededToCompleteTeamTypeJiraIssues(projectName, teamType);
	}

	public List<Object[]> numberOfIssuesCreatedAndResolvedInAMonth(String projectName) {
		return issueRepository.numberOfIssuesCreatedAndResolvedInAMonth(projectName);
	}

    public List<Object[]> numberOfIssuesCreatedAndResolvedInAMonthByTeamType(String projectName,
            String teamType) {
        return issueRepository.numberOfIssuesCreatedAndResolvedInAMonthByTeamType(projectName, teamType);
    }

    public List<Object[]> assigneeTotalCompleteInformation(String projectName, String teamType, String status1, String status2, String priority){
        return issueRepository.assigneeTotalCompleteInformation(projectName, teamType, status1, status2, priority);
    }

    public Integer unfinishedJiraIssuesByToday(String projectName){
        return issueRepository.unfinishedJiraIssuesByToday(projectName);
    }

    public List<Object[]> topXUnfinishedJiraIssuesByToday(String projectName, Integer limitNumber){
        return issueRepository.topXUnifinishedJiraIssuesByToday(projectName, limitNumber);
    }

    public Integer criticalIssuesNotUpdatedCount(String projectName, String priority, Integer interval){
        return issueRepository.criticalIssuesNotUpdatedCount(projectName, priority, interval);
    }

    public List<Object[]> criticalIssuesNotUpdatedInfo(String projectName, String priority, Integer interval, Integer limitNumber){
        return issueRepository.criticalIssuesNotUpdatedInfo(projectName, priority, interval, limitNumber);
    }

    public List<Object[]> resourceWorkloadDigestCreated(String projectName, String teamType, Integer interval){
        return issueRepository.resourceWorkloadDigestCreated(projectName, teamType, interval);
    }

    public List<Object[]> resourceWorkloadDigestClosed(String projectName, String teamType, Integer interval){
        return issueRepository.resourceWorkloadDigestClosed(projectName, teamType, interval);
    }

    public List<Object[]> projectDigestCreated(String projectName, String teamType, Integer interval){
        return issueRepository.projectDigestCreated(projectName, teamType, interval);
    }

    public List<Object[]> projectDigestClosed(String projectName, String teamType, Integer interval){
        return issueRepository.projectDigestClosed(projectName, teamType, interval);
    }

    public Integer projectDigestBugsCreated(String projectName, String teamType, String subType, Integer interval){
        return issueRepository.projectDigestBugsCreated(projectName, teamType, subType, interval);
    }

    public Integer projectDigestBugsClosed(String projectName, String teamType, String subType, Integer interval){
        return issueRepository.projectDigestBugsClosed(projectName, teamType, subType, interval);
    }

    public Integer projectDigestAssigneeCount(String projectName, String teamType, Integer interval){
        return issueRepository.projectDigestAssigneeCount(projectName, teamType, interval);
    }

    public void deleteAll(){
        issueRepository.deleteAll();
    }
}
