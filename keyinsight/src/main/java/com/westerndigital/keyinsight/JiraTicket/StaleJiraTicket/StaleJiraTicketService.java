package com.westerndigital.keyinsight.JiraTicket.StaleJiraTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaleJiraTicketService {
    
    @Autowired
    private StaleJiraTicketRepository staleJiraTicketRepository;
}
