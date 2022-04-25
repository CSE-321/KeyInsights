package com.westerndigital.keyinsight.JiraProject;

import java.util.List;

import com.westerndigital.keyinsight.JiraProject.JiraProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JiraProjectService {
    @Autowired
    private final JiraProjectRepository projectRepository;

    public JiraProjectService(JiraProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    public List<JiraProject> getAllProjects() {
        return projectRepository.findAll();
    }
}
