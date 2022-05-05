package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO;

import java.util.List;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.AvatarUrls;

public class Lead {
    private String self;
    private String key;
    private String name;
    private List<AvatarUrls> avatarUrls;
    private String displayName;
    private Boolean active;

    public Lead() {
    }

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AvatarUrls> getAvatarUrls() {
        return this.avatarUrls;
    }

    public void setAvatarUrls(List<AvatarUrls> avatarUrls) {
        this.avatarUrls = avatarUrls;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean isActive() {
        return this.active;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
}