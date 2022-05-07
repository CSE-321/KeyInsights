package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetCustomFieldsFromSearchPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomFields {
    private String id;
    private String name;
    private String description;
    private String type;
    private String searcherKey;
    private String self;
    private Integer numericId;
    private Boolean isLocked;
    private Boolean isManaged;
    private Boolean isAllProjects;
    private Integer projectsCount;
    private Integer screensCount;
}
