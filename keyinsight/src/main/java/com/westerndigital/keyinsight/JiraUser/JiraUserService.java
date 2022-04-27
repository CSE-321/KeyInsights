package com.westerndigital.keyinsight.JiraUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
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

    public List<JiraUser> getJiraUsers() {
        return jiraUserRepository.findAll();
    }



}

