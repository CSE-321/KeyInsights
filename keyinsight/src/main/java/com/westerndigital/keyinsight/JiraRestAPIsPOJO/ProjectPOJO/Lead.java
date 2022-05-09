package com.westerndigital.keyinsight.JiraRestAPIsPOJO.ProjectPOJO;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs.AvatarUrls;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Lead {
    private String self;
    private String key;
    private String name;
    private AvatarUrls avatarUrls;
    private String displayName;
    private Boolean active;
}
