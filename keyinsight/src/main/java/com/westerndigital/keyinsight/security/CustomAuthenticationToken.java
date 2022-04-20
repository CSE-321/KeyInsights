package com.westerndigital.keyinsight.security;

import java.util.Collection;

import org.springframework.security.authentication
    .UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthenticationToken extends 
    UsernamePasswordAuthenticationToken {

    private Object serverUrl;
    
    public CustomAuthenticationToken(Object username, Object password, 
        Object serverUrl, Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);

        this.serverUrl = serverUrl;
    }

    public String getServerUrl() {
        return serverUrl.toString();
    }
}
