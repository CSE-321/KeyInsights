package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Type {
    private String self;
    private String value;
    private Integer id;
    private Boolean disabled;
}
