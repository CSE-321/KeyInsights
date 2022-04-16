package com.westerndigital.keyinsight.JiraUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    // automatically generate an ID
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", 
        allocationSize = 1)
    @GeneratedValue(generator = "user_sequence", 
        strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String serverUrl;

    private final Set<GrantedAuthority> authorities = new HashSet<>();

    public JiraUser() {

    }

    public JiraUser(String username, String email, String serverUrl) {
        this.username = username;
        this.email = email;
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

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

}