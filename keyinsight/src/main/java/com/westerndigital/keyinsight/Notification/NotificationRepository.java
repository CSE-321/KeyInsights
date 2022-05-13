package com.westerndigital.keyinsight.Notification;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository 
    extends JpaRepository<Notification, Long> {

    @Query(
        value = "select n from Notification n " +
                "where n.jiraUsername = :jiraUsername and " + 
                    "n.projectName = :projectName")
    public Optional<Notification> findByJiraUsernameAndProject(
            String jiraUsername, String projectName);

    public Optional<Notification> findByJiraUsername(String jiraUsername);
}
