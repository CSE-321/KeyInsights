package com.westerndigital.keyinsight.Scheduler;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import static org.quartz.SimpleTrigger.REPEAT_INDEFINITELY;

import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class JobScheduleCreator {
        
    // create trigger for jobs
    public CronTrigger createCronTrigger(String triggerName, 
        Date triggerStartTime, String triggerCronExpression,
        int triggerMisfireInstruction) {

        // create the trigger factory bean
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();

        // set the trigger properties
        factoryBean.setName(triggerName);
        factoryBean.setStartTime(triggerStartTime);
        factoryBean.setCronExpression(triggerCronExpression);

        // configure how often the trigger is repeated
        factoryBean.setCronExpression(triggerCronExpression);

        // configure what happens when the trigger misfires
        factoryBean.setMisfireInstruction(triggerMisfireInstruction);

        try {
            factoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            // TODO: handle exception
        }

        return factoryBean.getObject();
    }

    // create job
    public JobDetail createJob(Class<? extends QuartzJobBean> jobClass,
        boolean isDurable, ApplicationContext context, String jobName,
        String jobGroup) {
        
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();

        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(isDurable);
        factoryBean.setApplicationContext(context);
        factoryBean.setName(jobName);
        factoryBean.setGroup(jobGroup);

        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

}
