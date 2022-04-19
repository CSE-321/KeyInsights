package com.westerndigital.keyinsight.JiraIssue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

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
    private DateTime createdDateTime;
    private DateTime updatedDateTime;
    private DateTime dueDateTime;
    private Float storyPoint;
    private String subType;
    private String priority;
    private String resolution;
    private String assignee;
    private String assigneeAvatarUrl;

    public JiraIssue() {

    }

}
