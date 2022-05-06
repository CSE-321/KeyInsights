package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

public class Issuelinktype {
    private String id;
    private String name;
    private String inward;
    private String outward;
    private String self;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInward() {
        return this.inward;
    }

    public void setInward(String inward) {
        this.inward = inward;
    }

    public String getOutward() {
        return this.outward;
    }

    public void setOutward(String outward) {
        this.outward = outward;
    }

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", inward='" + getInward() + "'" +
            ", outward='" + getOutward() + "'" +
            ", self='" + getSelf() + "'" +
            "}";
    }

}
