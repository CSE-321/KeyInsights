package com.westerndigital.keyinsight.JiraServer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class JiraServerService {

    @Autowired
    private JiraServerRepository serverRepository;

    public JiraServerService(JiraServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public List<JiraServer> getAllServers() {

        return null;
    }

}
