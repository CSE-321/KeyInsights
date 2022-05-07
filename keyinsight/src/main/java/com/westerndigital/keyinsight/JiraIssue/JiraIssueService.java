package com.westerndigital.keyinsight.JiraIssue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class JiraIssueService {

    private final JiraIssueRepository issueRepository;

    public JiraIssueService(JiraIssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public void saveSingleIssue(JiraIssue issue) {
        issueRepository.save(issue);
    }

    public JiraIssue findById(String key) {
        return issueRepository.findById(key).orElse(new JiraIssue());
    }

    public List<String> getAllIssueType(String projectName) {
        return issueRepository.getAllIssueType(projectName);
    }

    public List<Object[]> totalJiraCountAndStoryPoints(String projectName) {
        return issueRepository.totalJiraCountAndStoryPoints(projectName);
    }

    public List<Object[]> totalJiraCountAndStoryPointsFromStatus(String projectName, String status) {
        return issueRepository.totalJiraCountAndStoryPointsFromStatus(projectName, status);
    }

    public Integer totalJiraSecondTypeIssueCount(String projectName, String secondType) {
        return issueRepository.totalJiraSecondTypeIssueCount(projectName, secondType);
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

    public List<Object[]> totalIssueTypeJiraCountAndStoryPoints(String projectName, String issueType) {
        return issueRepository.totalIssueTypeJiraCountAndStoryPoints(projectName, issueType);
    }

    public List<Object[]> totalIssueTypeJiraCountAndStoryPointsFromStatus(String projectName, String issueType,
            String status) {
        return issueRepository.totalIssueTypeJiraCountAndStoryPointsFromStatus(projectName, issueType, status);
    }

    public Integer totalIssueTypeJiraSecondTypeIssueCount(String projectName, String issueType, String secondType) {
        return issueRepository.totalIssueTypeJiraSecondTypeIssueCount(projectName, issueType, secondType);
    }

    public Integer totalIssueTypeJiraStatusIssueCount(String projectName, String issueType, String status) {
        return issueRepository.totalIssueTypeJiraStatusIssueCount(projectName, issueType, status);
    }

    public Integer totalIssueTypeJiraPriorityIssueCount(String projectName, String issueType, String priority) {
        return issueRepository.totalIssueTypeJiraPriorityIssueCount(projectName, issueType, priority);
    }

    public Integer totalIssueTypeJiraPriorityOppositeResolutionIssueCount(String projectName, String issueType,
            String priority, String resolution1, String resolution2, String resolution3) {
        return issueRepository.totalIssueTypeJiraPriorityOppositeResolutionIssueCount(projectName, issueType, priority,
                resolution1, resolution2, resolution3);
    }

    public Integer totalIssueTypeJiraResolutionIssueCount(String projectName, String issueType, String resolution) {
        return issueRepository.totalIssueTypeJiraResolutionIssueCount(projectName, issueType, resolution);
    }

	public List<Integer> daysNeededToCompleteTotalJiraIssues(String projectName) {
		return issueRepository.daysNeededToCompleteTotalJiraIssues(projectName);
	}

	public List<Integer> daysNeededToCompleteIssueTypeJiraIssues(String projectName, String issueType) {
		return issueRepository.daysNeededToCompleteIssueTypeJiraIssues(projectName, issueType);
	}

	public List<Object[]> numberOfIssuesCreatedAndResolvedInAMonth(String projectName) {
		return issueRepository.numberOfIssuesCreatedAndResolvedInAMonth(projectName);
	}

    public List<Object[]> numberOfIssuesCreatedAndResolvedInAMonthByIssueType(String projectName,
            String issueType) {
        return issueRepository.numberOfIssuesCreatedAndResolvedInAMonthByIssueType(projectName, issueType);
    }

    public List<Object[]> assigneeTotalCompleteInformation(String projectName, String issueType, String status1, String status2, String priority){
        return issueRepository.assigneeTotalCompleteInformation(projectName, issueType, status1, status2, priority);
    }

    public void deleteAll(){
        issueRepository.deleteAll();
    }
}
