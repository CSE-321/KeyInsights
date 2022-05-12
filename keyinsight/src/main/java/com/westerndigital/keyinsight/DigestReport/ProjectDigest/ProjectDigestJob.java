package com.westerndigital.keyinsight.DigestReport.ProjectDigest;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ProjectDigestJob extends QuartzJobBean {
    
        @Override
        protected void executeInternal(JobExecutionContext context) throws JobExecutionException{

        }
}