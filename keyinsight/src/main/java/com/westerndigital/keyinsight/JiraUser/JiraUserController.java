package com.westerndigital.keyinsight.JiraUser;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class JiraUserController {

    private final JiraUserService jiraUserService;

    public JiraUserController(JiraUserService jiraUserService) {
        this.jiraUserService = jiraUserService;
    }

    @GetMapping
    public ResponseEntity<List<JiraUser>> getUsers() {
        List<JiraUser> jiraUsers = jiraUserService.getJiraUsers();

        return ResponseEntity.ok(jiraUsers);
    }
}
