package com.westerndigital.keyinsight.JiraRestAPIsPOJO.ProjectPOJO;

import java.util.List;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs.AvatarUrls;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs.ProjectCategory;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProjectJson {
    private String expand;
    private String self;
    private String id;
    private String key;
    private String description;
    private Lead lead;
    private List<String> components;
    private List<IssueTypes> issueTypes;
    private String assigneeType;
    private List<Versions> versions;
    private String name;
    private Roles roles;
    private AvatarUrls avatarUrls;
    private ProjectCategory projectCategory;
    private String projectTypeKey;
    private Boolean archived;
}
