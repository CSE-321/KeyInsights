package com.westerndigital.keyinsight.Notification;

import java.util.List;

import com.westerndigital.keyinsight.JiraUser.JiraUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository 
    extends JpaRepository<Notification, Long> {

    public List<Notification> findByJiraUser(JiraUser jiraUser);
}
