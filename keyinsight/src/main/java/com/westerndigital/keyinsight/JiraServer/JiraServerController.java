package com.westerndigital.keyinsight.JiraServer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

//@Controller

@RequestMapping("/api/v1/server")

public class JiraServerController {

    @Autowired
    private JiraServerService serverService;

    public JiraServerController(JiraServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping
    public ResponseEntity<List<JiraServer>> getAllServers() {
        return new ResponseEntity<>(serverService.getAllServers(), HttpStatus.OK);
    }
}
