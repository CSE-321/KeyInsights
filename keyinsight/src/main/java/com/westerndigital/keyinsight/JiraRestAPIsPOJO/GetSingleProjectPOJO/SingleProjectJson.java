package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO;

import java.util.List;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.AvatarUrls;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.ProjectCategory;

public class SingleProjectJson {
    private String expand;
    private String self;
    private String id;
    private String key;
    private String description;
    private List<Lead> lead;
    private List<String> components;
    private List<IssueTypes> issueTypes;
    private String assigneeType;
    private List<Versions> versions;
    private String name;
    private List<Roles> roles;
    private List<AvatarUrls> avatarUrls;
    private List<ProjectCategory> projectCategory;
    private String projectTypeKey;
    private Boolean archived;

    public SingleProjectJson() {
    }

    public String getExpand() {
        return this.expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lead> getLead() {
        return this.lead;
    }

    public void setLead(List<Lead> lead) {
        this.lead = lead;
    }

    public List<String> getComponents() {
        return this.components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public List<IssueTypes> getIssueTypes() {
        return this.issueTypes;
    }

    public void setIssueTypes(List<IssueTypes> issueTypes) {
        this.issueTypes = issueTypes;
    }

    public String getAssigneeType() {
        return this.assigneeType;
    }

    public void setAssigneeType(String assigneeType) {
        this.assigneeType = assigneeType;
    }

    public List<Versions> getVersions() {
        return this.versions;
    }

    public void setVersions(List<Versions> versions) {
        this.versions = versions;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Roles> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public List<AvatarUrls> getAvatarUrls() {
        return this.avatarUrls;
    }

    public void setAvatarUrls(List<AvatarUrls> avatarUrls) {
        this.avatarUrls = avatarUrls;
    }

    public List<ProjectCategory> getProjectCategory() {
        return this.projectCategory;
    }

    public void setProjectCategory(List<ProjectCategory> projectCategory) {
        this.projectCategory = projectCategory;
    }

    public String getProjectTypeKey() {
        return this.projectTypeKey;
    }

    public void setProjectTypeKey(String projectTypeKey) {
        this.projectTypeKey = projectTypeKey;
    }

    public Boolean isArchived() {
        return this.archived;
    }

    public Boolean getArchived() {
        return this.archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

}
