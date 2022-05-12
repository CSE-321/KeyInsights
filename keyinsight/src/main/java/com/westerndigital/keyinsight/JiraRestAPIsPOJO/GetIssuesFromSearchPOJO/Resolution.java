package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Resolution {
    private String self;
    private Integer id;
    private String description;
    private String name;
}
