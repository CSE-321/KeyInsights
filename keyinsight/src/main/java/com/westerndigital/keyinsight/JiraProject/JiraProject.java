package com.westerndigital.keyinsight.JiraProject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.OffsetDateTime;
@Entity
@Table(name = "Projects")
public class JiraProject {

    @Id
    private String id;
    private String name;
    private String projectLead;
    private String projectLeadAvatarUrl;
    private OffsetDateTime createdDate; // uses the create date of the first issue
    private Integer numberOfIssues;

    public JiraProject() {
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

    public String getProjectLead() {
        return this.projectLead;
    }

    public void setProjectLead(String projectLead) {
        this.projectLead = projectLead;
    }

    public String getProjectLeadAvatarUrl() {
        return this.projectLeadAvatarUrl;
    }

    public void setProjectLeadAvatarUrl(String projectLeadAvatarUrl) {
        this.projectLeadAvatarUrl = projectLeadAvatarUrl;
    }

    public OffsetDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getNumberOfIssues() {
        return this.numberOfIssues;
    }

    public void setNumberOfIssues(Integer numberOfIssues) {
        this.numberOfIssues = numberOfIssues;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", projectLead='" + getProjectLead() + "'" +
            ", projectLeadAvatarUrl='" + getProjectLeadAvatarUrl() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", numberOfIssues='" + getNumberOfIssues() + "'" +
            "}";
    }
}