package com.westerndigital.keyinsight.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.westerndigital.keyinsight.security.authenticator.JiraAuthenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements 
    AuthenticationProvider {

    @Autowired
    private JiraAuthenticator jiraAuthenticator;

    @Autowired
    private HttpServletRequest request;

    @Override
    public Authentication authenticate(Authentication authentication) throws 
        AuthenticationException {

        String serverUrl = String.format(
            "http://%s", request.getParameter("serverUrl"));

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();         

        if (jiraAuthenticator.authenticate(username, password, serverUrl)) {
            List<GrantedAuthority> authorities = 
                new ArrayList<GrantedAuthority>();

            return new CustomAuthenticationToken(username, password, 
                serverUrl, authorities);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthenticationToken.class);
    }
}
