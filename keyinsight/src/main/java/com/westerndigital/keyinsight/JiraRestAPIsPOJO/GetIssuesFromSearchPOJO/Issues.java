package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import java.util.ArrayList;

public class Issues {
    private String expand;
    private String id;
    private String self;
    private String key;
    private ArrayList<Fields> fields;

    public Issues() {
    }

    public String getExpand() {
        return this.expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

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

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<Fields> getFields() {
        return this.fields;
    }

    public void setFields(ArrayList<Fields> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "{" +
            " expand='" + getExpand() + "'" +
            ", id='" + getId() + "'" +
            ", self='" + getSelf() + "'" +
            ", key='" + getKey() + "'" +
            ", fields='" + getFields() + "'" +
            "}";
    }

}
