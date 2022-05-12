package com.westerndigital.keyinsight.Scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import static org.quartz.SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW;

import java.util.Date;

import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class SchedulerJobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private SchedulerJobRepository schedulerJobRepository;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private JobScheduleCreator jobScheduleCreator;
    
    // schedule new job
    public void scheduleJob(SchedulerJob schedulerJob) {
        // get the scheduler from the factory bean
        scheduler = schedulerFactoryBean.getScheduler();

        Class<? extends QuartzJobBean> jobClass = schedulerJob.getJobClass();
        String jobName = schedulerJob.getJobName();
        String jobGroup = schedulerJob.getJobGroup();
        String jobCronExpression = schedulerJob.getJobCronExpression();

        final String SCHEDULED = "SCHEDULED";
        final String PAUSED = "PAUSED";
        final String RESUMED = "RESUMED";
        final String UPDATED = "UPDATED";

        final int misfireInstruction = MISFIRE_INSTRUCTION_FIRE_NOW;

        try {
            // build the job
            JobDetail jobDetail = JobBuilder
                .newJob(jobClass)
                .withIdentity(jobName, jobGroup)
                .build();

            // check if the job is scheduled
            JobKey jobKey = jobDetail.getKey();
            if (scheduler.checkExists(jobKey) == false) {

                // job is not scheduled, create job
                jobDetail = jobScheduleCreator.createJob(
                    jobClass,
                    false, context, jobName, jobGroup);

                // create trigger
                Trigger trigger = jobScheduleCreator
                    .createCronTrigger(jobName, new Date(), jobCronExpression, 
                    misfireInstruction);

                // schedule job
                scheduler.scheduleJob(jobDetail, trigger);

                schedulerJob.setJobStatus(SCHEDULED);

                // save the job in the database
                schedulerJobRepository.save(schedulerJob);
            } else {
                // delete the job
                deleteJob(jobName, jobKey);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    // update job
    public void updateJob() {

    }

    // delete job
    public void deleteJob(SchedulerJob schedulerJob) {

        // try {
        //     String jobName = schedulerJob.getJobName();
        //     String jobGroup = schedulerJob.getJobGroup();

        //     SchedulerJob job = schedulerJobRepository.findByJobName(jobName);
        //     schedulerJobRepository.delete(job);

        //     JobKey jobKey = new JobKey(jobName, jobGroup);

        //     scheduler.deleteJob(jobKey);

        // } catch (SchedulerException e) {

        // }
    }

    // pause job
    public void pauseJob() {

    }

    // resume job
    public void resumeJob() {

    }

    private void deleteJob(String jobName, JobKey jobKey) {
        // delete the job from the database
        // schedulerJobRepository.delete()
    }
}
