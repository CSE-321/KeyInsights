package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StatusCategory {
    private String self;
    private Integer id;
    private String key;
    private String colorName;
    private String name;
}
