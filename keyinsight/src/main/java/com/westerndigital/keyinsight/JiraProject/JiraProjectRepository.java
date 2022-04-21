package com.westerndigital.keyinsight.JiraProject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JiraProjectRepository extends JpaRepository<JiraProject, Integer> {
    JiraProject findByName(String name);
    List<JiraProject> findAll();
}