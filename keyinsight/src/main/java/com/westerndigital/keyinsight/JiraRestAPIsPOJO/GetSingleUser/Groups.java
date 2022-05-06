package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetSingleUser;

import java.util.List;

public class Groups {
    private Integer size;
    private List[] items;

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List[] getItems() {
        return this.items;
    }

    public void setItems(List[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "{" +
            " size='" + getSize() + "'" +
            ", items='" + getItems() + "'" +
            "}";
    }
    
}
