package com.westerndigital.keyinsight.JiraTicket.UnchangedSprintStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnchangedSprintStatusTicketService {
    
    @Autowired
    private UnchangedSprintStatusTicketRepository 
        unchangedSprintStatusTicketRepository;
}
