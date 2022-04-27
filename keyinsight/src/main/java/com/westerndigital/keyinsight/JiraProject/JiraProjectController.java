package com.westerndigital.keyinsight.JiraProject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.orm.jpa.*;

import java.util.List;

//@Controller

@RequestMapping("api/v1/project")

public class JiraProjectController {
@Autowired
private JiraProjectService jiraProjectService;

public JiraProjectController(JiraProjectService jiraProjectService){
    this.jiraProjectService = jiraProjectService;
}
@GetMapping
public ResponseEntity<List<JiraProject>> getAllProjects() {
    return new ResponseEntity<>(jiraProjectService.getAllProjects(), HttpStatus.OK);
}

}