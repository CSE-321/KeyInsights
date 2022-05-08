package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Roles {
    @JsonProperty("Zephyr QA")
    private String ZephyrQA;
    @JsonProperty("Developers")
    private String Developers;
    @JsonProperty("Power Users")
    private String PowerUsers;
    @JsonProperty("Administrators")
    private String Administrators;
    @JsonProperty("Users")
    private String Users;    
}
