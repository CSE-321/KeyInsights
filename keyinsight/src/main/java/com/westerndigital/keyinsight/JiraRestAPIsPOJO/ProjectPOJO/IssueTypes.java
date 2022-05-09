package com.westerndigital.keyinsight.JiraRestAPIsPOJO.ProjectPOJO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class IssueTypes {
    private String self;
    private String id;
    private String description;
    private String iconUrl;
    private String name;
    private Boolean subtask;
    private String avatarId;    
}
