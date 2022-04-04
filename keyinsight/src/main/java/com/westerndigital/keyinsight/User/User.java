package com.westerndigital.keyinsight.User;

public class User {
    
    private Long id;
    private String email;

    User(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
