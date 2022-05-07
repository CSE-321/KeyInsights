package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO;

import java.util.ArrayList;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs.AvatarUrls;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs.ProjectCategory;

public class SingleProjectJson {
    private String expand;
    private String self;
    private String id;
    private String key;
    private String description;
    private Lead lead;
    private ArrayList<String> components;
    private ArrayList<IssueTypes> issueTypes;
    private String assigneeType;
    private ArrayList<Versions> versions;
    private String name;
    private Roles roles;
    private AvatarUrls avatarUrls;
    private ProjectCategory projectCategory;
    private String projectTypeKey;
    private Boolean archived;

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

    public Lead getLead() {
        return this.lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }

    public ArrayList<String> getComponents() {
        return this.components;
    }

    public void setComponents(ArrayList<String> components) {
        this.components = components;
    }

    public ArrayList<IssueTypes> getIssueTypes() {
        return this.issueTypes;
    }

    public void setIssueTypes(ArrayList<IssueTypes> issueTypes) {
        this.issueTypes = issueTypes;
    }

    public String getAssigneeType() {
        return this.assigneeType;
    }

    public void setAssigneeType(String assigneeType) {
        this.assigneeType = assigneeType;
    }

    public ArrayList<Versions> getVersions() {
        return this.versions;
    }

    public void setVersions(ArrayList<Versions> versions) {
        this.versions = versions;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Roles getRoles() {
        return this.roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public AvatarUrls getAvatarUrls() {
        return this.avatarUrls;
    }

    public void setAvatarUrls(AvatarUrls avatarUrls) {
        this.avatarUrls = avatarUrls;
    }

    public ProjectCategory getProjectCategory() {
        return this.projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
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

    @Override
    public String toString() {
        return "{" +
            " expand='" + getExpand() + "'" +
            ", self='" + getSelf() + "'" +
            ", id='" + getId() + "'" +
            ", key='" + getKey() + "'" +
            ", description='" + getDescription() + "'" +
            ", lead='" + getLead() + "'" +
            ", components='" + getComponents() + "'" +
            ", issueTypes='" + getIssueTypes() + "'" +
            ", assigneeType='" + getAssigneeType() + "'" +
            ", versions='" + getVersions() + "'" +
            ", name='" + getName() + "'" +
            ", roles='" + getRoles() + "'" +
            ", avatarUrls='" + getAvatarUrls() + "'" +
            ", projectCategory='" + getProjectCategory() + "'" +
            ", projectTypeKey='" + getProjectTypeKey() + "'" +
            ", archived='" + isArchived() + "'" +
            "}";
    }

}
