package com.westerndigital.keyinsight.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    
    // automatically generate an ID
    @Id
    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1)
    @GeneratedValue(
        generator = "user_sequence",
        strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String serverUrl;

    public User() {

    }

    public User(String email, String serverUrl) {
        this.email = email;
        this.serverUrl = serverUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}