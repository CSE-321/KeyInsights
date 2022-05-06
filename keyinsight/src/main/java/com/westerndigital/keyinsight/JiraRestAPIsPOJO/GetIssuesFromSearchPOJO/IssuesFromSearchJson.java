package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import java.util.ArrayList;
public class IssuesFromSearchJson {
    private String expand;
    private Integer startAt;
    private Integer maxResults;
    private Integer total;
    private ArrayList<Issues> issues;

    public String getExpand() {
        return this.expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public Integer getStartAt() {
        return this.startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public ArrayList<Issues> getIssues() {
        return this.issues;
    }

    public void setIssues(ArrayList<Issues> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "{" +
            " expand='" + getExpand() + "'" +
            ", startAt='" + getStartAt() + "'" +
            ", maxResults='" + getMaxResults() + "'" +
            ", total='" + getTotal() + "'" +
            ", issues='" + getIssues() + "'" +
            "}";
    }

}
