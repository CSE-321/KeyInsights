package com.westerndigital.keyinsight.Email.DAOs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProjectDigest {
    private String teamType;
    private String assigneeCount;
    private String newJiraCount;
    private Double newJiraStoryPoint;
    private String closedJiraCount;
    private Double closedJiraStoryPoint;
    private String newJiraBugCount;
    private String closedJiraBugCount;
    
    public ProjectDigest(){};    
}
