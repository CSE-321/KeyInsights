package com.westerndigital.keyinsight.JiraTicket.NextSprint;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class NextSprintTicketJob extends QuartzJobBean {
   
    @Override
    protected void executeInternal(JobExecutionContext context) 
        throws JobExecutionException {

    }
}
