package com.westerndigital.keyinsight.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsAuthenticationProvider implements 
    AuthenticationProvider {

    private JiraAuthenticator jiraAuthenticator;

    public CustomUserDetailsAuthenticationProvider(
        JiraAuthenticator jiraAuthenticator) {

        this.jiraAuthenticator = jiraAuthenticator;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws 
        AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();         
        String serverUrl = authentication.getDetails().toString();

        if (jiraAuthenticator.authenticate(username, password, serverUrl)) {
            List<GrantedAuthority> authorities = 
                new ArrayList<GrantedAuthority>();

            authorities.add(new SimpleGrantedAuthority(""));

            return new CustomAuthenticationToken(username, password, 
                serverUrl, authorities);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
