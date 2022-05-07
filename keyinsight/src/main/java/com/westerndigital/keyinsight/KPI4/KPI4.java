package com.westerndigital.keyinsight.KPI4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class KPI4 {
    @Id
    @SequenceGenerator(name = "kpi4_sequence", sequenceName = "kpi4_sequence", allocationSize = 1)
    @GeneratedValue(generator = "kpi4_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String assigneeName;//assignee Name
    private String teamType;
    private Integer jiraCount;
    private Double storyPoint;
    private Double PercentageCompleted;
    private Double PercentageWIP;
    private Double PercentageNotstarted;
    private Double PercentageCriticalNotstarted;
}
