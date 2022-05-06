package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleProjectPOJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Roles {
    @JsonProperty("Zephyr QA")
    private String ZephyrQA;
    @JsonProperty("Developers")
    private String Developers;
    @JsonProperty("Power Users")
    private String PowerUsers;
    @JsonProperty("Administrators")
    private String Administrators;
    @JsonProperty("Users")
    private String Users;

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

    public String getAdministrators() {
        return this.Administrators;
    }

    public void setAdministrators(String Administrators) {
        this.Administrators = Administrators;
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
            ", Administrators='" + getAdministrators() + "'" +
            ", Users='" + getUsers() + "'" +
            "}";
    }
    
}
