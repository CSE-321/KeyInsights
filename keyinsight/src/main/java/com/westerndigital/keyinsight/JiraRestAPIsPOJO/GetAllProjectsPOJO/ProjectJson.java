package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetAllProjectsPOJO;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.AvatarUrls;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.ProjectCategory;

public class ProjectJson {
    private String expand;
    private String self;
    private String id;
    private String key;
    private String name;
    private AvatarUrls avatarUrls;
    private ProjectCategory projectCategory;
    private String projectTypeKey;

    public String getExpand() {
        return this.expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public AvatarUrls getAvatarUrls() {
        return this.avatarUrls;
    }

    public void setAvatarUrls(AvatarUrls avatarUrls) {
        this.avatarUrls = avatarUrls;
    }

    public ProjectCategory getProjectCategory() {
        return this.projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

    public String getProjectTypeKey() {
        return this.projectTypeKey;
    }

    public void setProjectTypeKey(String projectTypeKey) {
        this.projectTypeKey = projectTypeKey;
    }


    @Override
    public String toString() {
        return "{" +
            " expand='" + getExpand() + "'" +
            ", self='" + getSelf() + "'" +
            ", id='" + getId() + "'" +
            ", key='" + getKey() + "'" +
            ", name='" + getName() + "'" +
            ", avatarUrls='" + getAvatarUrls() + "'" +
            ", projectCategory='" + getProjectCategory() + "'" +
            ", projectTypeKey='" + getProjectTypeKey() + "'" +
            "}";
    }

    
}

