package com.westerndigital.keyinsight.Token;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.westerndigital.keyinsight.JiraUser.JiraUser;
import com.westerndigital.keyinsight.JiraUser.JiraUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/token")
public class TokenController {

    @Autowired
    public TokenService tokenService;

    @Autowired
    public JiraUserService jiraUserService;
    
    @GetMapping("/refresh")
    public void refreshToken(HttpServletRequest request, 
        HttpServletResponse response) {

        final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // check the header
        if (header == null || 
            header.startsWith(AUTHORIZATION_HEADER_PREFIX) == false) {

            // handle error
        }

        try {
            // extract the refresh token from the header
            String refresh_token = header
                .substring(AUTHORIZATION_HEADER_PREFIX.length());

            String username = tokenService.getUsernameFromToken(refresh_token);

            // get the user data from the database
            JiraUser user = jiraUserService.loadUserByUsername(username);

            // generate a new access token
            String requestUrl = request.getRequestURI();
            String access_token = tokenService
                .generateNewAccessToken(user, requestUrl);

            // set the access token in the response
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", access_token);
            tokens.put("refresh_token", refresh_token);

            new ObjectMapper().writeValue(response.getOutputStream(), tokens);

        } catch (Exception e) {
            // handle exception
        }
        
    }
}
