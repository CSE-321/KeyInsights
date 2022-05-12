package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Watches {
    private String self;
    private Integer watchCount;
    private Boolean isWatching;
}
