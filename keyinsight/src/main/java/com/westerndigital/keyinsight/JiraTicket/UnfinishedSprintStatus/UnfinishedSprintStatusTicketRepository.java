package com.westerndigital.keyinsight.JiraTicket.UnfinishedSprintStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnfinishedSprintStatusTicketRepository 
    extends JpaRepository<UnfinishedSprintStatusTicket, Long> {
    
}
