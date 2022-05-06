package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import java.util.Objects;

public class StatusCategory {
    private String self;
    private Integer id;
    private String key;
    private String colorName;
    private String name;

    public StatusCategory() {
    }

    public StatusCategory(String self, Integer id, String key, String colorName, String name) {
        this.self = self;
        this.id = id;
        this.key = key;
        this.colorName = colorName;
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

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getColorName() {
        return this.colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusCategory self(String self) {
        setSelf(self);
        return this;
    }

    public StatusCategory id(Integer id) {
        setId(id);
        return this;
    }

    public StatusCategory key(String key) {
        setKey(key);
        return this;
    }

    public StatusCategory colorName(String colorName) {
        setColorName(colorName);
        return this;
    }

    public StatusCategory name(String name) {
        setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof StatusCategory)) {
            return false;
        }
        StatusCategory statusCategory = (StatusCategory) o;
        return Objects.equals(self, statusCategory.self) && Objects.equals(id, statusCategory.id) && Objects.equals(key, statusCategory.key) && Objects.equals(colorName, statusCategory.colorName) && Objects.equals(name, statusCategory.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(self, id, key, colorName, name);
    }

    @Override
    public String toString() {
        return "{" +
            " self='" + getSelf() + "'" +
            ", id='" + getId() + "'" +
            ", key='" + getKey() + "'" +
            ", colorName='" + getColorName() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
    
    
}
