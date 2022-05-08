package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GeneralPOJOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AvatarUrls {
    @JsonProperty("48x48")
    private String size48;
    @JsonProperty("24x24")
    private String size24;
    @JsonProperty("16x16")
    private String size16;
    @JsonProperty("32x32")
    private String size32;
}

/* 
List of Resource
https://stackoverflow.com/questions/54179178/com-fasterxml-jackson-databind-exc-unrecognizedpropertyexception-with-json-array
*/