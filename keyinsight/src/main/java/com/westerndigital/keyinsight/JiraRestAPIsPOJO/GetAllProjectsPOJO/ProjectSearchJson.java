package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetAllProjectsPOJO;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs.AvatarUrls;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs.ProjectCategory;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProjectSearchJson {
    private String expand;
    private String self;
    private String id;
    private String key;
    private String name;
    private AvatarUrls avatarUrls;
    private ProjectCategory projectCategory;
    private String projectTypeKey;  
}

