package com.westerndigital.keyinsight.Scheduler;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedulerConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private QuartzProperties quartzProperties;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        // create the job factory
        SchedulerJobFactory schedulerJobFactory = new SchedulerJobFactory();

        // set the application context
        schedulerJobFactory.setApplicationContext(applicationContext);

        // set the properties from the quartz.properties file
        Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());

        // create and expose the Scheduler as a bean for dependency injection
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        // set factory properties
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(properties);
        factory.setJobFactory(schedulerJobFactory);

        return factory;
    }
}
