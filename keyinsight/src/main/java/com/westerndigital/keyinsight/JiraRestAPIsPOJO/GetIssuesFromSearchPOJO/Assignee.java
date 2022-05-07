package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs.AvatarUrls;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Assignee {
    private String self;
    private String name;
    private String key;
    private String emailAddress;
    private AvatarUrls avatarUrls;
    private String displayName;
    private Boolean active;
    private String timeZone;    
}
