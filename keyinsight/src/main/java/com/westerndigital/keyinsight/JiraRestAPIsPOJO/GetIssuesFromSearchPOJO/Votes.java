package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

public class Votes {
    private String self;
    private Integer votes;
    private Boolean hasVoted;

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Integer getVotes() {
        return this.votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Boolean isHasVoted() {
        return this.hasVoted;
    }

    public Boolean getHasVoted() {
        return this.hasVoted;
    }

    public void setHasVoted(Boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    @Override
    public String toString() {
        return "{" +
            " self='" + getSelf() + "'" +
            ", votes='" + getVotes() + "'" +
            ", hasVoted='" + isHasVoted() + "'" +
            "}";
    }
}
