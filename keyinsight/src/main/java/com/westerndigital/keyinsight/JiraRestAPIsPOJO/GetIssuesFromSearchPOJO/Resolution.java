package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

public class Resolution {
    private String self;
    private Integer id;
    private String description;
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
