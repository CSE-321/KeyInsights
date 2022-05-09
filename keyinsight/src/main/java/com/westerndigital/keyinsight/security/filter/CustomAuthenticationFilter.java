package com.westerndigital.keyinsight.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.westerndigital.keyinsight.JiraUser.JiraUser;
import com.westerndigital.keyinsight.security.CustomAuthenticationToken;

import org.springframework.http.MediaType;
import org.springframework.security.authentication
    .AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication
    .UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter 
    extends UsernamePasswordAuthenticationFilter {

    // access token expiration time (30 minutes)
    final int ACCESS_TOKEN_EXPIRATION_MILLIS = 60 * 60 * 1000; 

    // refresh token expiration time (60 minutes)
    final int REFRESH_TOKEN_EXPIRATION_MILLIS = 24 * 60 * 60 * 1000;

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {

        String username, password, serverUrl;
        Collection<SimpleGrantedAuthority> authorities;

        // extract data from the request JSON
        ObjectMapper requestMapper = new ObjectMapper();

        try {
            // map JSON data to Java object representing the JIRA user
            JiraUser jiraUserFromRequest = requestMapper.readValue(
                request.getInputStream(), JiraUser.class);
            
            username = jiraUserFromRequest.getUsername();
            password = jiraUserFromRequest.getPassword();
            serverUrl = jiraUserFromRequest.getServerUrl();
            
            // get the user's roles (empty by default, needed for the token)
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

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        String jiraUser = authentication.getPrincipal().toString();
        String requestUrl = request.getRequestURI().toString();
        List<String> authorities = new ArrayList<>();

        authentication.getAuthorities().stream()
            .forEach(authority -> {
                authorities.add(authority.getAuthority());
            });

        String access_token = generateToken(
            jiraUser, 
            requestUrl,
            authorities,
            ACCESS_TOKEN_EXPIRATION_MILLIS, 
            algorithm);

        String refresh_token = generateToken(
            jiraUser, 
            requestUrl, 
            authorities,
            REFRESH_TOKEN_EXPIRATION_MILLIS, 
            algorithm);

        // create the response to send back to the client
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    private String generateToken(String jiraUser, String requestUrl, 
        List<String> authorities, int expiration, Algorithm algorithm) {

        return JWT.create()
            .withSubject(jiraUser)
            .withIssuer(requestUrl)
            .withClaim("role", authorities)
            .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
            .sign(algorithm);
    }
}
