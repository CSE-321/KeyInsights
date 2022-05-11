package com.westerndigital.keyinsight.JiraTicket.UnchangedSprintStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnchangedSprintStatusTicketRepository 
    extends JpaRepository<UnchangedSprintStatusTicket, Long> {
    
}
