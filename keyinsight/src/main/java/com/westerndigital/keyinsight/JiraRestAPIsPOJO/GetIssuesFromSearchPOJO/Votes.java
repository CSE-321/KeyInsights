package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Votes {
    private String self;
    private Integer votes;
    private Boolean hasVoted;
}
