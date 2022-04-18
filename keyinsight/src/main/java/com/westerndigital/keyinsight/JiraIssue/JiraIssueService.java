package com.westerndigital.keyinsight.JiraIssue;

import org.springframework.stereotype.Service;

@Service
public class JiraIssueService {

    private final JiraIssueRepository issueRepository;

    public JiraIssueService(JiraIssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public void addIssue(JiraIssue issue) {
        issueRepository.save(issue);
    }

}
