package com.westerndigital.keyinsight.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.westerndigital.keyinsight.JiraUser.JiraUser;

import org.springframework.security.authentication
    .AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication
    .UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter 
    extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {

        Object username, password, serverUrl;
        Collection<GrantedAuthority> authorities;

        // extract data from the request JSON
        ObjectMapper requestMapper = new ObjectMapper();
        try {
            // map JSON data to Java object representing the JIRA user
            JiraUser jiraUser = requestMapper.readValue(
                request.getInputStream(), JiraUser.class);
            
            username = jiraUser.getUsername();
            password = jiraUser.getPassword();
            serverUrl = jiraUser.getServerUrl();
            authorities = jiraUser.getAuthorities();

        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

        // create a custom authentication token that with the login data
        // that will be passed to the authentication provider
        CustomAuthenticationToken authRequest = new CustomAuthenticationToken(
            username, password, serverUrl, authorities);

        setDetails(request, authRequest);

        // return the custom authentication token
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authentication
    ) throws IOException, ServletException {

    }
}
