package com.westerndigital.keyinsight.JiraUser;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Table(name = "Users")
@Data
public class JiraUser implements UserDetails {

    @Id
    private String id;
    private String username;
    private String password;
    private String serverUrl;

    public JiraUser() {}

    public JiraUser(String id, String username, String password, 
        String serverUrl) {

        this.username = username;
        this.password = password;
        this.serverUrl = serverUrl;
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return new HashSet<SimpleGrantedAuthority>();
    }

}