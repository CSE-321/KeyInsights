package com.westerndigital.keyinsight.JiraRestAPIsPOJO.GetIssuesFromSearchPOJO;

import java.util.Objects;

public class Type {
    private String self;
    private String value;
    private Integer id;
    private Boolean disabled;

    public Type() {
    }

    public Type(String self, String value, Integer id, Boolean disabled) {
        this.self = self;
        this.value = value;
        this.id = id;
        this.disabled = disabled;
    }

    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isDisabled() {
        return this.disabled;
    }

    public Boolean getDisabled() {
        return this.disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Type self(String self) {
        setSelf(self);
        return this;
    }

    public Type value(String value) {
        setValue(value);
        return this;
    }

    public Type id(Integer id) {
        setId(id);
        return this;
    }

    public Type disabled(Boolean disabled) {
        setDisabled(disabled);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Type)) {
            return false;
        }
        Type type = (Type) o;
        return Objects.equals(self, type.self) && Objects.equals(value, type.value) && Objects.equals(id, type.id) && Objects.equals(disabled, type.disabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(self, value, id, disabled);
    }

    @Override
    public String toString() {
        return "{" +
            " self='" + getSelf() + "'" +
            ", value='" + getValue() + "'" +
            ", id='" + getId() + "'" +
            ", disabled='" + isDisabled() + "'" +
            "}";
    }
    
}
