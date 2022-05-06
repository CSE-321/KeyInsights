package com.westerndigital.keyinsight.ScheduledJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class CompileWorkloadDigestJob extends QuartzJobBean {

    // public void execute(JobExecutionContext context) 
    //     throws JobExecutionException {

    // }
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException{
        
    }
}
