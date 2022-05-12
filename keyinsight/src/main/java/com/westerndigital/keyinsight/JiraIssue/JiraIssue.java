package com.westerndigital.keyinsight.JiraIssue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.time.OffsetDateTime;
@Entity
@Table(name = "Issues")
@Data
public class JiraIssue {
    @Id
    private String id;
    private Integer issueNumber;
    private String name;
    private String projectName;
    private String projectUniqueId;
    private String teamType;
    private String status;
    private OffsetDateTime createdDateTime;
    private OffsetDateTime updatedDateTime;
    private OffsetDateTime dueDateTime;
    private OffsetDateTime resolutionDateTime;
    private Double storyPoint;
    private String subType;
    private String priority;
    private String resolution;
    private String assignee;
    private String assigneeAvatarUrl;
}
