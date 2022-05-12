package com.westerndigital.keyinsight.JiraTicket.UnchangedSprintStatus;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class UnchangedSprintStatusTicketJob extends QuartzJobBean {
   
    @Override
    protected void executeInternal(JobExecutionContext context)
        throws JobExecutionException {
        
        // scan the database for Jira tickets whose sprint status has
        // not changed for the given duration of time
        

    }
}
