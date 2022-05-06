package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetCustomFieldsFromSearchPOJO;

public class CustomFields {
    private String id;
    private String name;
    private String description;
    private String type;
    private String searcherKey;
    private String self;
    private Integer numericId;
    private Boolean isLocked;
    private Boolean isManaged;
    private Boolean isAllProjects;
    private Integer projectsCount;
    private Integer screensCount;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSearcherKey() {
        return this.searcherKey;
    }

    public void setSearcherKey(String searcherKey) {
        this.searcherKey = searcherKey;
    }

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Integer getNumericId() {
        return this.numericId;
    }

    public void setNumericId(Integer numericId) {
        this.numericId = numericId;
    }

    public Boolean isIsLocked() {
        return this.isLocked;
    }

    public Boolean getIsLocked() {
        return this.isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Boolean isIsManaged() {
        return this.isManaged;
    }

    public Boolean getIsManaged() {
        return this.isManaged;
    }

    public void setIsManaged(Boolean isManaged) {
        this.isManaged = isManaged;
    }

    public Boolean isIsAllProjects() {
        return this.isAllProjects;
    }

    public Boolean getIsAllProjects() {
        return this.isAllProjects;
    }

    public void setIsAllProjects(Boolean isAllProjects) {
        this.isAllProjects = isAllProjects;
    }

    public Integer getProjectsCount() {
        return this.projectsCount;
    }

    public void setProjectsCount(Integer projectsCount) {
        this.projectsCount = projectsCount;
    }

    public Integer getScreensCount() {
        return this.screensCount;
    }

    public void setScreensCount(Integer screensCount) {
        this.screensCount = screensCount;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", searcherKey='" + getSearcherKey() + "'" +
            ", self='" + getSelf() + "'" +
            ", numericId='" + getNumericId() + "'" +
            ", isLocked='" + isIsLocked() + "'" +
            ", isManaged='" + isIsManaged() + "'" +
            ", isAllProjects='" + isIsAllProjects() + "'" +
            ", projectsCount='" + getProjectsCount() + "'" +
            ", screensCount='" + getScreensCount() + "'" +
            "}";
    }

}
