package com.westerndigital.keyinsight.JavaIssue;

import org.springframework.stereotype.Service;

@Service
public class JavaIssueService {

    private final JavaIssueRepository issueRepository;

    public JavaIssueService(JavaIssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public void addIssue(JavaIssue issue) {
        issueRepository.save(issue);
    }

}
