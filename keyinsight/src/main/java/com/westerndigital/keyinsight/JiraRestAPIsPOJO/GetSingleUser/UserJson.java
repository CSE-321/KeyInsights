package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleUser;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.AvatarUrls;

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
    private String expand;

    public UserJson() {
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

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public AvatarUrls getAvatarUrls() {
        return this.avatarUrls;
    }

    public void setAvatarUrls(AvatarUrls avatarUrls) {
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

    public Boolean isDeleted() {
        return this.deleted;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getExpand() {
        return this.expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    @Override
    public String toString() {
        return "{" +
            " self='" + getSelf() + "'" +
            ", key='" + getKey() + "'" +
            ", name='" + getName() + "'" +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", avatarUrls='" + getAvatarUrls() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", active='" + isActive() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", timeZone='" + getTimeZone() + "'" +
            ", locale='" + getLocale() + "'" +
            ", expand='" + getExpand() + "'" +
            "}";
    }
    
}
