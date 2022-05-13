package com.westerndigital.keyinsight.Email.DAOs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResourceDigest {
    private String teamType;
    private String newJiraCount;
    private String newJiraStoryPoint;
    private String closedJiraCount;
    private String closedJiraStoryPoint;

    public ResourceDigest(){};
}
