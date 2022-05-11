package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Issuelinks {
    private String id;
    private String self;
    private Issuelinktype type;
    private Issues outwardIssue;
    private Issues inwardIssue;
}
