package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

public class Progress {
    private Integer progress;
    private Integer total;
    private Integer percent;

    public Integer getProgress() {
        return this.progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPercent() {
        return this.percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "{" +
            " progress='" + getProgress() + "'" +
            ", total='" + getTotal() + "'" +
            ", percent='" + getPercent() + "'" +
            "}";
    }

}
