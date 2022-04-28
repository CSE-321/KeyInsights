package com.westerndigital.keyinsight.JiraIssue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Table(name = "Issues")
@Getter
@Setter
public class JiraIssue {
    @Id
    private Integer id;
    private String name;
    private String projectName;
    private String teamType;
    private String status;
    private OffsetDateTime createdDateTime;
    private OffsetDateTime updatedDateTime;
    private OffsetDateTime dueDateTime;
    private OffsetDateTime resolutionDateTime;
    private Float storyPoint;
    private String subType;
    private String priority;
    private String resolution;
    private String assignee;
    private String assigneeAvatarUrl;
}
