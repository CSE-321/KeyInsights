package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetCustomFieldsFromSearchPOJO;

import java.util.ArrayList;

public class CustomFieldJson {
    private Integer maxResults;
    private Integer startAt;
    private Integer total;
    private boolean isLast;
    private ArrayList<CustomFields> values;

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public Integer getStartAt() {
        return this.startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public boolean isIsLast() {
        return this.isLast;
    }

    public boolean getIsLast() {
        return this.isLast;
    }

    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    public ArrayList<CustomFields> getValues() {
        return this.values;
    }

    public void setValues(ArrayList<CustomFields> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "{" +
            " maxResults='" + getMaxResults() + "'" +
            ", startAt='" + getStartAt() + "'" +
            ", total='" + getTotal() + "'" +
            ", isLast='" + isIsLast() + "'" +
            ", values='" + getValues() + "'" +
            "}";
    }

}
