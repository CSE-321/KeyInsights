package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import java.util.Objects;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.AvatarUrls;

public class Creator {
    private String self;
    private String name;
    private String key;
    private String emailAddress;
    private AvatarUrls avatarUrls;
    private String displayName;
    private Boolean active;
    private String timeZone;

    public Creator() {
    }

    public Creator(String self, String name, String key, String emailAddress, AvatarUrls avatarUrls, String displayName, Boolean active, String timeZone) {
        this.self = self;
        this.name = name;
        this.key = key;
        this.emailAddress = emailAddress;
        this.avatarUrls = avatarUrls;
        this.displayName = displayName;
        this.active = active;
        this.timeZone = timeZone;
    }

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Creator self(String self) {
        setSelf(self);
        return this;
    }

    public Creator name(String name) {
        setName(name);
        return this;
    }

    public Creator key(String key) {
        setKey(key);
        return this;
    }

    public Creator emailAddress(String emailAddress) {
        setEmailAddress(emailAddress);
        return this;
    }

    public Creator avatarUrls(AvatarUrls avatarUrls) {
        setAvatarUrls(avatarUrls);
        return this;
    }

    public Creator displayName(String displayName) {
        setDisplayName(displayName);
        return this;
    }

    public Creator active(Boolean active) {
        setActive(active);
        return this;
    }

    public Creator timeZone(String timeZone) {
        setTimeZone(timeZone);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Creator)) {
            return false;
        }
        Creator creator = (Creator) o;
        return Objects.equals(self, creator.self) && Objects.equals(name, creator.name) && Objects.equals(key, creator.key) && Objects.equals(emailAddress, creator.emailAddress) && Objects.equals(avatarUrls, creator.avatarUrls) && Objects.equals(displayName, creator.displayName) && Objects.equals(active, creator.active) && Objects.equals(timeZone, creator.timeZone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(self, name, key, emailAddress, avatarUrls, displayName, active, timeZone);
    }

    @Override
    public String toString() {
        return "{" +
            " self='" + getSelf() + "'" +
            ", name='" + getName() + "'" +
            ", key='" + getKey() + "'" +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", avatarUrls='" + getAvatarUrls() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", active='" + isActive() + "'" +
            ", timeZone='" + getTimeZone() + "'" +
            "}";
    }
    
    
}
