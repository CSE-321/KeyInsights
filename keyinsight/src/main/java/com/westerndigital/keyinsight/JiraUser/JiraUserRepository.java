package com.westerndigital.keyinsight.JiraUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JiraUserRepository extends JpaRepository<JiraUser, Long> {

    JiraUser findByUsername(String username);
}
