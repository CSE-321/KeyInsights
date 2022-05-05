package com.westerndigital.keyinsight;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

import com.westerndigital.keyinsight.ScheduledJob.ScanJiraIssuesJob;

@SpringBootApplication
@EnableScheduling
public class KeyinsightApplication {

	public static void main(String[] args) throws IOException, SchedulerException {
		SpringApplication.run(KeyinsightApplication.class, args);

		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		JobDetail scanStaleJiraIssues = JobBuilder
			.newJob(ScanJiraIssuesJob.class)
			.build();

		Trigger scanStableJiraIssuesTrigger = TriggerBuilder.newTrigger()
			.startNow()
			.withSchedule(SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInHours(24)
				.repeatForever())
			.build();

		scheduler.start();
		scheduler.scheduleJob(scanStaleJiraIssues, scanStableJiraIssuesTrigger);
	}

}