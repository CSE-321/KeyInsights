package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

public class Issuelinks {
    private String id;
    private String self;
    private Issuelinktype type;
    private Issues outwardIssue;
    private Issues inwardIssue;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Issuelinktype getType() {
        return this.type;
    }

    public void setType(Issuelinktype type) {
        this.type = type;
    }

    public Issues getOutwardIssue() {
        return this.outwardIssue;
    }

    public void setOutwardIssue(Issues outwardIssue) {
        this.outwardIssue = outwardIssue;
    }

    public Issues getInwardIssue() {
        return this.inwardIssue;
    }

    public void setInwardIssue(Issues inwardIssue) {
        this.inwardIssue = inwardIssue;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", self='" + getSelf() + "'" +
            ", type='" + getType() + "'" +
            ", outwardIssue='" + getOutwardIssue() + "'" +
            ", inwardIssue='" + getInwardIssue() + "'" +
            "}";
    }
}
