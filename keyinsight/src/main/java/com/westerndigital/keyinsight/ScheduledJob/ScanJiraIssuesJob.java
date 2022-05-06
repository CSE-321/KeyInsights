package com.westerndigital.keyinsight.ScheduledJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ScanJiraIssuesJob extends QuartzJobBean {
    
    // public void execute(JobExecutionContext context) 
    //     throws JobExecutionException {

    //     // scan for stale Jira issues
    //    // System.out.println("Hello every 30 seconds");

    //     // scan for Jira issues with unchanged sprint statuses

    //}
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException{
        
    }
}
