package com.westerndigital.keyinsight.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.westerndigital.keyinsight.JiraUser.JiraUser;
import com.westerndigital.keyinsight.JiraUser.JiraUserService;

import org.springframework.security.authentication
    .AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication
    .UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter 
    extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {

        String username, password, serverUrl;
        Collection<? extends GrantedAuthority> authorities;

        // extract data from the request JSON
        ObjectMapper requestMapper = new ObjectMapper();
        try {
            // map JSON data to Java object representing the JIRA user
            JiraUser jiraUserFromRequest = requestMapper.readValue(
                request.getInputStream(), JiraUser.class);
            
            username = jiraUserFromRequest.getUsername();
            password = jiraUserFromRequest.getPassword();
            serverUrl = jiraUserFromRequest.getServerUrl();
            authorities = jiraUserFromRequest.getAuthorities();

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

        String jiraUser = authentication.getPrincipal().toString();

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        // 30 minutes
        final int ACCESS_TOKEN_EXPIRATION_MILLIS = 30 * 60 * 1000; 

        // 60 minutes
        final int REFRESH_TOKEN_EXPIRATION_MILLIS = 60 * 60 * 1000;

        String access_token = generateToken(jiraUser, 
            ACCESS_TOKEN_EXPIRATION_MILLIS, algorithm);

        String refresh_token = generateToken(jiraUser, 
            REFRESH_TOKEN_EXPIRATION_MILLIS, algorithm);

        response.setHeader("access_token", access_token);
        response.setHeader("refresh_token", refresh_token);

    }

    private String generateToken(String jiraUser, int expiration, 
        Algorithm algorithm) {

        return JWT.create()
            .withIssuer(jiraUser)
            .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
            .sign(algorithm);
    }
}
