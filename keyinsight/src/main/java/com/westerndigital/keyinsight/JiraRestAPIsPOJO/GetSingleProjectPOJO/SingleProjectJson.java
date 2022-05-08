package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO;

import java.util.ArrayList;

import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs.AvatarUrls;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs.ProjectCategory;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
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
}
