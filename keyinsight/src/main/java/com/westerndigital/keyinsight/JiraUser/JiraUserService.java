package com.westerndigital.keyinsight.JiraUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JiraUserService implements UserDetailsService {

    @Autowired
    private JiraUserRepository jiraUserRepository;

    @Override
    public JiraUser loadUserByUsername(String username) {
        return jiraUserRepository.findByUsername(username);
    }
    
    public void saveUser(JiraUser jiraUser) {
        jiraUserRepository.save(jiraUser);
    }



}

