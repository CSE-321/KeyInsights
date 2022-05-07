package com.westerndigital.keyinsight.JiraProject;

import java.util.List;

import com.westerndigital.keyinsight.JiraProject.JiraProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JiraProjectService {
    @Autowired
    private JiraProjectRepository projectRepository;

    public List<JiraProject> getAllProjects() {
        return projectRepository.findAll();
    }

    public JiraProject findById(String id){
        return projectRepository.findById(id).orElse(new JiraProject());
    }

    public void saveSingleProject(JiraProject project){
        projectRepository.save(project);
    }

    public void deleteAll(){
        projectRepository.deleteAll();
    }
}
