package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetCustomFieldsFromSearchPOJO;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomFieldJson {
    private Integer maxResults;
    private Integer startAt;
    private Integer total;
    private boolean isLast;
    private List<CustomFields> values;
}
