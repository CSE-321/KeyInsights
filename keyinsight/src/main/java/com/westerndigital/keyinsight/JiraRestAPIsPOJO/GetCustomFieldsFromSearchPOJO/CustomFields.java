package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetCustomFieldsFromSearchPOJO;

import java.util.Objects;

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

    public CustomFields() {
    }

    public CustomFields(String id, String name, String description, String type, String searcherKey, String self, Integer numericId, Boolean isLocked, Boolean isManaged, Boolean isAllProjects, Integer projectsCount, Integer screensCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.searcherKey = searcherKey;
        this.self = self;
        this.numericId = numericId;
        this.isLocked = isLocked;
        this.isManaged = isManaged;
        this.isAllProjects = isAllProjects;
        this.projectsCount = projectsCount;
        this.screensCount = screensCount;
    }

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

    public CustomFields id(String id) {
        setId(id);
        return this;
    }

    public CustomFields name(String name) {
        setName(name);
        return this;
    }

    public CustomFields description(String description) {
        setDescription(description);
        return this;
    }

    public CustomFields type(String type) {
        setType(type);
        return this;
    }

    public CustomFields searcherKey(String searcherKey) {
        setSearcherKey(searcherKey);
        return this;
    }

    public CustomFields self(String self) {
        setSelf(self);
        return this;
    }

    public CustomFields numericId(Integer numericId) {
        setNumericId(numericId);
        return this;
    }

    public CustomFields isLocked(Boolean isLocked) {
        setIsLocked(isLocked);
        return this;
    }

    public CustomFields isManaged(Boolean isManaged) {
        setIsManaged(isManaged);
        return this;
    }

    public CustomFields isAllProjects(Boolean isAllProjects) {
        setIsAllProjects(isAllProjects);
        return this;
    }

    public CustomFields projectsCount(Integer projectsCount) {
        setProjectsCount(projectsCount);
        return this;
    }

    public CustomFields screensCount(Integer screensCount) {
        setScreensCount(screensCount);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CustomFields)) {
            return false;
        }
        CustomFields customFields = (CustomFields) o;
        return Objects.equals(id, customFields.id) && Objects.equals(name, customFields.name) && Objects.equals(description, customFields.description) && Objects.equals(type, customFields.type) && Objects.equals(searcherKey, customFields.searcherKey) && Objects.equals(self, customFields.self) && Objects.equals(numericId, customFields.numericId) && Objects.equals(isLocked, customFields.isLocked) && Objects.equals(isManaged, customFields.isManaged) && Objects.equals(isAllProjects, customFields.isAllProjects) && Objects.equals(projectsCount, customFields.projectsCount) && Objects.equals(screensCount, customFields.screensCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, type, searcherKey, self, numericId, isLocked, isManaged, isAllProjects, projectsCount, screensCount);
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
