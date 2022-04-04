package com.westerndigital.keyinsight.Issue;

import org.springframework.stereotype.Service;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public void addIssue(Issue issue) {
        issueRepository.save(issue);
    }

}
