package com.westerndigital.keyinsight.JiraTicket.NextSprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NextSprintTicketService {
    
    @Autowired
    private NextSprintTicketRepository nextSprintTicketRepository;
}
