package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetCustomFieldsFromSearchPOJO;

import java.util.ArrayList;
import java.util.Objects;

public class CustomFieldJson {
    private Integer maxResults;
    private Integer startAt;
    private Integer total;
    private boolean isLast;
    private ArrayList<CustomFields> values;

    public CustomFieldJson() {
    }

    public CustomFieldJson(Integer maxResults, Integer startAt, Integer total, boolean isLast, ArrayList<CustomFields> values) {
        this.maxResults = maxResults;
        this.startAt = startAt;
        this.total = total;
        this.isLast = isLast;
        this.values = values;
    }

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

    public CustomFieldJson maxResults(Integer maxResults) {
        setMaxResults(maxResults);
        return this;
    }

    public CustomFieldJson startAt(Integer startAt) {
        setStartAt(startAt);
        return this;
    }

    public CustomFieldJson total(Integer total) {
        setTotal(total);
        return this;
    }

    public CustomFieldJson isLast(boolean isLast) {
        setIsLast(isLast);
        return this;
    }

    public CustomFieldJson values(ArrayList<CustomFields> values) {
        setValues(values);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CustomFieldJson)) {
            return false;
        }
        CustomFieldJson customFieldJson = (CustomFieldJson) o;
        return Objects.equals(maxResults, customFieldJson.maxResults) && Objects.equals(startAt, customFieldJson.startAt) && Objects.equals(total, customFieldJson.total) && isLast == customFieldJson.isLast && Objects.equals(values, customFieldJson.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxResults, startAt, total, isLast, values);
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
