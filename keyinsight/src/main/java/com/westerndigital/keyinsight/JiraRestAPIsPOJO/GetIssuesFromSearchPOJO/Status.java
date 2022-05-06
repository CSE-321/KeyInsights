package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

public class Status {
    private String self;
    private String description;
    private String iconUrl;
    private String name;
    private Integer id;
    private StatusCategory statusCategory;

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
