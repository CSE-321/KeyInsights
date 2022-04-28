package com.westerndigital.keyinsight.JiraUser;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.westerndigital.keyinsight.Notification.Notification;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Table(name = "Users")
@Data
public class JiraUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;
    private String username;
    private String password;
    private String serverUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Notification notification;
    
    @ElementCollection(targetClass = String.class)
    private List<String> roles = new ArrayList<String>();

    public JiraUser() {}

    public JiraUser(String username, String password, 
        String serverUrl) {

        this.username = username;
        this.password = password;
        this.serverUrl = serverUrl;

        notification = new Notification(this, serverUrl, null);
    }

    public void addRole(String role) {
        roles.add(role);
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
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = 
            new ArrayList<SimpleGrantedAuthority>();

        roles.forEach(role -> {
            System.out.println(role);
            authorities.add(new SimpleGrantedAuthority(role));
        });

        return authorities;
    }
}