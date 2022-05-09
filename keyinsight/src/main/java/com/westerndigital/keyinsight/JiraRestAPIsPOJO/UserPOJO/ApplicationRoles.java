package com.westerndigital.keyinsight.JiraRestAPIsPOJO.UserPOJO;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApplicationRoles {
    private Integer size;
    private List[] items;
}
