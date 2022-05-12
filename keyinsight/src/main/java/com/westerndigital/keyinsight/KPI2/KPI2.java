package com.westerndigital.keyinsight.KPI2;

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
public class KPI2 {
    @Id
    @SequenceGenerator(name = "kpi2_sequence", sequenceName = "kpi2_sequence", allocationSize = 1)
    @GeneratedValue(generator = "kpi2_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String teamType;
    private Double averageDayToCompleteIssue;
    private Double medianDayToCompleteIssues;
    private Integer minimumDayToCompleteIssues;
    private Integer maximumDayToCompleteIssues;
}
