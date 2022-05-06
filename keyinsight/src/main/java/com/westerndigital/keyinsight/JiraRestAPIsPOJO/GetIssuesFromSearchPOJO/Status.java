package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import java.util.Objects;

public class Status {
    private String self;
    private String description;
    private String iconUrl;
    private String name;
    private Integer id;
    private StatusCategory statusCategory;

    public Status() {
    }

    public Status(String self, String description, String iconUrl, String name, Integer id, StatusCategory statusCategory) {
        this.self = self;
        this.description = description;
        this.iconUrl = iconUrl;
        this.name = name;
        this.id = id;
        this.statusCategory = statusCategory;
    }

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusCategory getStatusCategory() {
        return this.statusCategory;
    }

    public void setStatusCategory(StatusCategory statusCategory) {
        this.statusCategory = statusCategory;
    }

    public Status self(String self) {
        setSelf(self);
        return this;
    }

    public Status description(String description) {
        setDescription(description);
        return this;
    }

    public Status iconUrl(String iconUrl) {
        setIconUrl(iconUrl);
        return this;
    }

    public Status name(String name) {
        setName(name);
        return this;
    }

    public Status id(Integer id) {
        setId(id);
        return this;
    }

    public Status statusCategory(StatusCategory statusCategory) {
        setStatusCategory(statusCategory);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Status)) {
            return false;
        }
        Status status = (Status) o;
        return Objects.equals(self, status.self) && Objects.equals(description, status.description) && Objects.equals(iconUrl, status.iconUrl) && Objects.equals(name, status.name) && Objects.equals(id, status.id) && Objects.equals(statusCategory, status.statusCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(self, description, iconUrl, name, id, statusCategory);
    }

    @Override
    public String toString() {
        return "{" +
            " self='" + getSelf() + "'" +
            ", description='" + getDescription() + "'" +
            ", iconUrl='" + getIconUrl() + "'" +
            ", name='" + getName() + "'" +
            ", id='" + getId() + "'" +
            ", statusCategory='" + getStatusCategory() + "'" +
            "}";
    }

}
