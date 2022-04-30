package com.westerndigital.keyinsight.KPI2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KPI2 {
    private String teamType;
    private Double averageDayToCompleteIssue;
    private Double medianDayToCompleteIssues;
    private Integer minimumDayToCompleteIssues;
    private Integer maximumDayToCompleteIssues;
}
