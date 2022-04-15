package com.westerndigital.keyinsight.JiraIssue;

import org.springframework.stereotype.Service;

import java.util.List;

import com.westerndigital.keyinsight.KPI1.KPI1;

@Service
public class JiraIssueService {

    private final JiraIssueRepository issueRepository;

    public JiraIssueService(JiraIssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    //public void addIssue(JiraIssue issue) {
        //issueRepository.save(issue);
    //}
    public List<KPI1> getKP1() {
        return null;
    }

}
