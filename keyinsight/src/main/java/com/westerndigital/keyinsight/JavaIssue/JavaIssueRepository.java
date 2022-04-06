package com.westerndigital.keyinsight.JavaIssue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JavaIssueRepository extends JpaRepository<JavaIssue, Long> {

}
