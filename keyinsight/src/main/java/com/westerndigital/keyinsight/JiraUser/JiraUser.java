package com.westerndigital.keyinsight.JiraUser;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Table(name = "Users")
@Data
public class JiraUser implements UserDetails {

    @Id
    private String id;
    private String username;
    // private String email;
    private String password;
    private String serverUrl;

    public JiraUser() {}

    // public JiraUser(String username, String email, String serverUrl) {
    //     this.username = username;
    //     this.email = email;
    //     this.serverUrl = serverUrl;
    // }

    public JiraUser(String id, String username, String password, String serverUrl) {
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

}