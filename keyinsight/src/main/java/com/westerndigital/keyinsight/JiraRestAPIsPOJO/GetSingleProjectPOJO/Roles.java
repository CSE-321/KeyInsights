package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Roles {
    @JsonProperty("Zepher QA")
    private String ZephyrQA;
    private String Developers;
    @JsonProperty("Power Users")
    private String PowerUsers;
    private String Adminstrators;
    private String Users;

    public Roles() {
    }

    public String getZephyrQA() {
        return this.ZephyrQA;
    }

    public void setZephyrQA(String ZephyrQA) {
        this.ZephyrQA = ZephyrQA;
    }

    public String getDevelopers() {
        return this.Developers;
    }

    public void setDevelopers(String Developers) {
        this.Developers = Developers;
    }

    public String getPowerUsers() {
        return this.PowerUsers;
    }

    public void setPowerUsers(String PowerUsers) {
        this.PowerUsers = PowerUsers;
    }

    public String getAdminstrators() {
        return this.Adminstrators;
    }

    public void setAdminstrators(String Adminstrators) {
        this.Adminstrators = Adminstrators;
    }

    public String getUsers() {
        return this.Users;
    }

    public void setUsers(String Users) {
        this.Users = Users;
    }

    @Override
    public String toString() {
        return "{" +
            " ZephyrQA='" + getZephyrQA() + "'" +
            ", Developers='" + getDevelopers() + "'" +
            ", PowerUsers='" + getPowerUsers() + "'" +
            ", Adminstrators='" + getAdminstrators() + "'" +
            ", Users='" + getUsers() + "'" +
            "}";
    }

    
}
