package com.westerndigital.keyinsight.Email.DAOs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResourceDigest {
    private String teamType;
    private String newJiraCount;
    private Double newJiraStoryPoint;
    private String closedJiraCount;
    private Double closedJiraStoryPoint;

    public ResourceDigest(){};
}
