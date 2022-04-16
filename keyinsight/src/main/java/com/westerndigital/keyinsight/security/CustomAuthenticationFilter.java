package com.westerndigital.keyinsight.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.westerndigital.keyinsight.JiraUser.JiraUser;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication
    .UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class CustomAuthenticationFilter 
    extends AbstractAuthenticationProcessingFilter {

    protected CustomAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {

        String username, password, serverUrl;

        // extract data from the request JSON
        ObjectMapper requestMapper = new ObjectMapper();
        try {
            JiraUser jiraUser = requestMapper.readValue(
                request.getInputStream(), JiraUser.class);
            
            username = jiraUser.getUsername();
            password = jiraUser.getPassword();
            serverUrl = jiraUser.getServerUrl();

            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Server URL: " + serverUrl);
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

        CustomAuthenticationToken authRequest = new 
            CustomAuthenticationToken(username, password, serverUrl);

        return authRequest;
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
