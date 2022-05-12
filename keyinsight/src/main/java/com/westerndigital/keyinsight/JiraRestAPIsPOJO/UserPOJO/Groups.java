package com.westerndigital.keyinsight.JiraRestAPIsPOJO.UserPOJO;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Groups {
    private Integer size;
    private List<GroupInfo> items;    
}
