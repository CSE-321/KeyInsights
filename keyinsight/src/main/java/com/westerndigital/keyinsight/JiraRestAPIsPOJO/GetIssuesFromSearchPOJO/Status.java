package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Status {
    private String self;
    private String description;
    private String iconUrl;
    private String name;
    private Integer id;
    private StatusCategory statusCategory;
}
