package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Parent {
    private String id;
    private String key;
    private String self;
    private Fields fields;
}
