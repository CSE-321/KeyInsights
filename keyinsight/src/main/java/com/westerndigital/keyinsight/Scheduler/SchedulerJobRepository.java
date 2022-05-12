package com.westerndigital.keyinsight.Scheduler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerJobRepository extends JpaRepository<SchedulerJob, Long> {
    public SchedulerJob findByJobName(String jobName);
}
