package com.westerndigital.keyinsight.JiraTicket.UnfinishedSprintStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnfinishedSprintStatusTicketService {
    
    @Autowired
    private UnfinishedSprintStatusTicketRepository 
        unfinishedSprintStatusTicketRepository;
}
