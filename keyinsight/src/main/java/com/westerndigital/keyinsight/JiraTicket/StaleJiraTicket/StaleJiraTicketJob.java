package com.westerndigital.keyinsight.JiraTicket.StaleJiraTicket;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class StaleJiraTicketJob extends QuartzJobBean {
    
    @Override
    protected void executeInternal(JobExecutionContext context)
        throws JobExecutionException {

        // scan the database for Jira tickets whose status is critical,
        // incomplete, and has had no activity for the given duration of time
        System.out.println("StaleJiraTicketJob");

    }
}
