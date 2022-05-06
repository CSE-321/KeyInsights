package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

public class Watches {
    private String self;
    private Integer watchCount;
    private Boolean isWatching;

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Integer getWatchCount() {
        return this.watchCount;
    }

    public void setWatchCount(Integer watchCount) {
        this.watchCount = watchCount;
    }

    public Boolean isIsWatching() {
        return this.isWatching;
    }

    public Boolean getIsWatching() {
        return this.isWatching;
    }

    public void setIsWatching(Boolean isWatching) {
        this.isWatching = isWatching;
    }

    @Override
    public String toString() {
        return "{" +
            " self='" + getSelf() + "'" +
            ", watchCount='" + getWatchCount() + "'" +
            ", isWatching='" + isIsWatching() + "'" +
            "}";
    }
}
