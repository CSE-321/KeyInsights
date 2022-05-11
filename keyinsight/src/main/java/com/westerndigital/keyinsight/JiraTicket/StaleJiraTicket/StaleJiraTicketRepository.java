package com.westerndigital.keyinsight.JiraTicket.StaleJiraTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaleJiraTicketRepository 
    extends JpaRepository<StaleJiraTicket, Long> {
    
}
