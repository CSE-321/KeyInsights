package com.westerndigital.keyinsight.JiraIssue;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller

@RequestMapping("api/v1/issues")

public class JiraIssueController {
    @Autowired
    private JiraIssueService jiraIssueService;

    public JiraIssueController(JiraIssueService jiraIssueService) {
        this.jiraIssueService = jiraIssueService;
    }

    @PostMapping(path = "/issues")
    public ResponseEntity<HttpStatus> addIssue(JiraIssue issue) {
        if (addIssue(issue) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(HttpStatus.CREATED);
        }

    }

}
