package com.westerndigital.keyinsight.Scheduler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
public class SchedulerJob {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String jobName;
    private String jobGroup;
    private String jobStatus;

    @Transient
    private Class<? extends QuartzJobBean> jobClass;

    private String jobCronExpression;

    public SchedulerJob(String jobName, String jobGroup, String jobStatus, 
        Class<? extends QuartzJobBean> jobClass, String jobCronExpression) {

        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.jobStatus = jobStatus;
        this.jobClass = jobClass;
        this.jobCronExpression = jobCronExpression;
    }
}
