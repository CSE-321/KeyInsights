package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class IssuesSearchJson {
    private String expand;
    private Integer startAt;
    private Integer maxResults;
    private Integer total;
    private List<Issues> issues;
}
