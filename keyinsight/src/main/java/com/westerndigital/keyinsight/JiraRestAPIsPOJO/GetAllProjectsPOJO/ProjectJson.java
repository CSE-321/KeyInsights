package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetAllProjectsPOJO;

import java.util.List;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.AvatarUrls;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.ProjectCategory;

public class ProjectJson {
    private String expand;
    private String self;
    private String id;
    private String key;
    private String name;
    private List<AvatarUrls> avatarUrls;
    private List<ProjectCategory> projectCategory;
    private String projectTypeKey;

    public ProjectJson(){}

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

    public List<AvatarUrls> getAvatarUrls() {
        return this.avatarUrls;
    }

    public void setAvatarUrls(List<AvatarUrls> avatarUrls) {
        this.avatarUrls = avatarUrls;
    }

    public List<ProjectCategory> getProjectCategory() {
        return this.projectCategory;
    }

    public void setProjectCategory(List<ProjectCategory> projectCategory) {
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

