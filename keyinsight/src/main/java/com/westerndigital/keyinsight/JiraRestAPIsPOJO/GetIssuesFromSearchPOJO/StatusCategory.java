package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

public class StatusCategory {
    private String self;
    private Integer id;
    private String key;
    private String colorName;
    private String name;

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
