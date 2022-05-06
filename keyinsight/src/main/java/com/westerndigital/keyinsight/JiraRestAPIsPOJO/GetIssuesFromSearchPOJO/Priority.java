package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import java.util.Objects;

public class Priority {
    private String self;
    private String iconUrl;
    private String name;
    private Integer id;

    public Priority() {
    }

    public Priority(String self, String iconUrl, String name, Integer id) {
        this.self = self;
        this.iconUrl = iconUrl;
        this.name = name;
        this.id = id;
    }

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
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

    public Priority self(String self) {
        setSelf(self);
        return this;
    }

    public Priority iconUrl(String iconUrl) {
        setIconUrl(iconUrl);
        return this;
    }

    public Priority name(String name) {
        setName(name);
        return this;
    }

    public Priority id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Priority)) {
            return false;
        }
        Priority priority = (Priority) o;
        return Objects.equals(self, priority.self) && Objects.equals(iconUrl, priority.iconUrl) && Objects.equals(name, priority.name) && Objects.equals(id, priority.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(self, iconUrl, name, id);
    }

    @Override
    public String toString() {
        return "{" +
            " self='" + getSelf() + "'" +
            ", iconUrl='" + getIconUrl() + "'" +
            ", name='" + getName() + "'" +
            ", id='" + getId() + "'" +
            "}";
    }    
}
