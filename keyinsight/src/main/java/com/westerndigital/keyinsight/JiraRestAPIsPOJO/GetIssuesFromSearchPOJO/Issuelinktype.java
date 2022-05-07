package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Issuelinktype {
    private String id;
    private String name;
    private String inward;
    private String outward;
    private String self;
}
