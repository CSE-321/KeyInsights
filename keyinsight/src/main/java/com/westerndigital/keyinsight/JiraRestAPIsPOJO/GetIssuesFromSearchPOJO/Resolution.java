package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import java.util.Objects;

public class Resolution {
    private String self;
    private Integer id;
    private String description;
    private String name;

    public Resolution() {
    }

    public Resolution(String self, Integer id, String description, String name) {
        this.self = self;
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resolution self(String self) {
        setSelf(self);
        return this;
    }

    public Resolution id(Integer id) {
        setId(id);
        return this;
    }

    public Resolution description(String description) {
        setDescription(description);
        return this;
    }

    public Resolution name(String name) {
        setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Resolution)) {
            return false;
        }
        Resolution resolution = (Resolution) o;
        return Objects.equals(self, resolution.self) && Objects.equals(id, resolution.id) && Objects.equals(description, resolution.description) && Objects.equals(name, resolution.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(self, id, description, name);
    }

    @Override
    public String toString() {
        return "{" +
            " self='" + getSelf() + "'" +
            ", id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
    
    
}
