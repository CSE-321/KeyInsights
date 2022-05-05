package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO;

public class IssueTypes {
    private String self;
    private String id;
    private String description;
    private String iconUrl;
    private String name;
    private Boolean subtask;
    private String avatarId;


    public IssueTypes() {
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isSubtask() {
        return this.subtask;
    }

    public Boolean getSubtask() {
        return this.subtask;
    }

    public void setSubtask(Boolean subtask) {
        this.subtask = subtask;
    }

    public String getAvatarId() {
        return this.avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    @Override
    public String toString() {
        return "{" +
            " self='" + getSelf() + "'" +
            ", id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", iconUrl='" + getIconUrl() + "'" +
            ", name='" + getName() + "'" +
            ", subtask='" + isSubtask() + "'" +
            ", avatarId='" + getAvatarId() + "'" +
            "}";
    }
    
}
