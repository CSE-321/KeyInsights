package com.westerndigital.keyinsight.ScheduledJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScanJiraIssuesJob implements Job {
    
    public void execute(JobExecutionContext context) 
        throws JobExecutionException {

        // scan for stale Jira issues
        System.out.println("Hello every 30 seconds");

        // scan for Jira issues with unchanged sprint statuses

    }
}
