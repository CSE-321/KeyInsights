package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleUser;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Groups {
    private Integer size;
    private List<GroupInfo> items;    
}
