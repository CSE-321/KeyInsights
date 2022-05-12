package com.westerndigital.keyinsight.JiraRestAPIsPOJO.UserPOJO;

import java.util.Objects;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GroupInfo {
    private String name;
    private String self;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GroupInfo)) {
            return false;
        }
        GroupInfo groupInfo = (GroupInfo) o;
        return Objects.equals(name, groupInfo.name) && Objects.equals(self, groupInfo.self);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, self);
    }
}
