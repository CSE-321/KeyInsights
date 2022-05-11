package com.westerndigital.keyinsight.JiraTicket.NextSprint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NextSprintTicketRepository 
    extends JpaRepository<NextSprintTicket, Long> {
    
}
