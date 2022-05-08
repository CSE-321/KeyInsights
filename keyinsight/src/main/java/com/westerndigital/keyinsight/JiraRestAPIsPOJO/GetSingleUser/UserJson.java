package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleUser;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs.AvatarUrls;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserJson {
    private String self;
    private String key;
    private String name;
    private String emailAddress;
    private AvatarUrls avatarUrls;
    private String displayName;
    private Boolean active;
    private Boolean deleted;
    private String timeZone;
    private String locale;
    private Groups groups;
    private ApplicationRoles applicationRoles;
    private String expand;
}
