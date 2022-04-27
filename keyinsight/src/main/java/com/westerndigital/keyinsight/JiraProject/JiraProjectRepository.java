package com.westerndigital.keyinsight.JiraProject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JiraProjectRepository extends JpaRepository<JiraProject, Integer> {
    Optional<JiraProject> findByName(String name);
    List<JiraProject> findAll();
}