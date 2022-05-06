package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

public class Parent {
    private String id;
    private String key;
    private String self;
    private Fields fields;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Fields getFields() {
        return this.fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", key='" + getKey() + "'" +
            ", self='" + getSelf() + "'" +
            ", fields='" + getFields() + "'" +
            "}";
    }

}
