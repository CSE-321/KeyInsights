package com.westerndigital.keyinsight.JiraUser;

import org.springframework.beans.factory.annotation.Autowired;

public class JiraUserService {

    @Autowired
    private JiraUserRepository jiraUserRepository;

    public void saveUser(JiraUser jiraUser) {
        jiraUserRepository.save(jiraUser);
    }

    public long getCount() {
        return jiraUserRepository.count();
    }

}

