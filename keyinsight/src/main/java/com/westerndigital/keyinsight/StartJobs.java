package com.westerndigital.keyinsight;

import com.westerndigital.keyinsight.JiraTicket.StaleJiraTicket.StaleJiraTicketJob;
import com.westerndigital.keyinsight.JiraTicket.UnfinishedSprintStatus.UnfinishedSprintStatusTicketJob;
import com.westerndigital.keyinsight.Scheduler.SchedulerJob;
import com.westerndigital.keyinsight.Scheduler.SchedulerJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class StartJobs implements CommandLineRunner {

    @Autowired
    private SchedulerJobService schedulerJobService;
    
    @Override
    public void run(String... args) throws Exception {
        final String INITIAL_STATUS = "UNSCHEDULED";
        final String EVERY_MINUTE_CRON = "0 0/1 0 ? * * *";

        // scan the database for stale Jira tickets
        String staleJiraJobName = "staleJiraDbScan";
        String staleJiraJobGroup = "dbTicketScan";
        String staleJiraJobStatus = INITIAL_STATUS;
        Class<? extends QuartzJobBean> staleJiraJobClass = StaleJiraTicketJob.class;
        String staleJiraJobRepeat = EVERY_MINUTE_CRON;

        // create the job
        SchedulerJob staleJiraJob = new SchedulerJob(staleJiraJobName,
            staleJiraJobGroup, staleJiraJobStatus, staleJiraJobClass,
            staleJiraJobRepeat);

        schedulerJobService.scheduleJob(staleJiraJob);

        // scan the database for unfinished Jira tickets
        String unfinishedJiraJobName = "unfinishedJiraDbScan";
        String unfinishedJiraJobGroup = "dbTicketScan";
        String unfinishedJiraJobStatus = INITIAL_STATUS;
        Class<? extends QuartzJobBean> unfinishedJiraJobClass = UnfinishedSprintStatusTicketJob.class;
        String unfinishedJiraCronExpression = EVERY_MINUTE_CRON;
        
    }
}
