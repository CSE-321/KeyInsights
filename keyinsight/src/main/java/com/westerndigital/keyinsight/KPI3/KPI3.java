package com.westerndigital.keyinsight.KPI3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class KPI3 {
    @Id
    @SequenceGenerator(name = "kpi3_sequence", sequenceName = "kpi3_sequence", allocationSize = 1)
    @GeneratedValue(generator = "kpi3_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String teamType;
    private String createdMonth;
    private String resolvedMonth;
    private Integer createdJiraCount;
    private Float createdJiraStoryPoints;
    private Integer resolvedJiraCount;
    private Float resolvedJiraStoryPoints;
}
