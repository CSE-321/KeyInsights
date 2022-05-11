package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProjectCategory {
    private String self;
    private String id;
    private String name;
    private String description;
}
