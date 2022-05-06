package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class Versions {
    private String self;
    private String id;
    private String name;
    private String description;
    private Boolean archived;
    private Boolean released;
    private LocalDate startDate;
    private LocalDate releaseDate;
    private Boolean overdue;
    // private LocalDate userStartDate;
    // private LocalDate userReleaseDate;
    private String projectId;

    public Versions() {
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean isReleased() {
        return this.released;
    }

    public Boolean getReleased() {
        return this.released;
    }

    public void setReleased(Boolean released) {
        this.released = released;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean isOverdue() {
        return this.overdue;
    }

    public Boolean getOverdue() {
        return this.overdue;
    }

    public void setOverdue(Boolean overdue) {
        this.overdue = overdue;
    }

    // public LocalDate getUserStartDate() {
    //     return this.userStartDate;
    // }

    // public void setUserStartDate(LocalDate userStartDate) {
    //     this.userStartDate = userStartDate;
    // }

    // public LocalDate getUserReleaseDate() {
    //     return this.userReleaseDate;
    // }

    // public void setUserReleaseDate(LocalDate userReleaseDate) {
    //     this.userReleaseDate = userReleaseDate;
    // }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "{" +
            " self='" + getSelf() + "'" +
            ", id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", archived='" + isArchived() + "'" +
            ", released='" + isReleased() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", overdue='" + isOverdue() + "'" +
            // ", userStartDate='" + getUserStartDate() + "'" +
            // ", userReleaseDate='" + getUserReleaseDate() + "'" +
            ", projectId='" + getProjectId() + "'" +
            "}";
    }

}
