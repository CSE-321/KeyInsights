package com.westerndigital.keyinsight.JiraTicket.UnfinishedSprintStatus;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class UnfinishedSprintStatusTicketJob extends QuartzJobBean {
    
    @Override
    protected void executeInternal(JobExecutionContext context) 
        throws JobExecutionException {
        
        System.out.println("UnfinishedSprintStatusTicketJob");
    }
}
