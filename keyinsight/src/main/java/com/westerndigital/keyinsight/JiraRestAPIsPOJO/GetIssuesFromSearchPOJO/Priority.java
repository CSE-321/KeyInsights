package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Priority {
    private String self;
    private String iconUrl;
    private String name;
    private Integer id;
}
