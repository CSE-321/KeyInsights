package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Issues {
    private String expand;
    private String id;
    private String self;
    private String key;
    private Fields fields;
}
