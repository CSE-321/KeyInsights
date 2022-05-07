package com.westerndigital.keyinsight.Token;

import java.io.IOException;
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
    private TokenService tokenService;

    @Autowired
    private JiraUserService jiraUserService;
    
    @GetMapping("/refresh")
    public void refreshToken(HttpServletRequest request, 
        HttpServletResponse response) throws IOException {

        final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || 
            header.startsWith(AUTHORIZATION_HEADER_PREFIX) == false) {

            // handle header error
        }

        String token = header.substring(AUTHORIZATION_HEADER_PREFIX.length());
        
        // look up the user in the database
        String username = tokenService.getUsernameFromToken(token);
        JiraUser authenticatedUser = jiraUserService
            .loadUserByUsername(username);

        if (authenticatedUser == null) {
            // handle user not found in database
        } else {
            try {
                Map<String, String> tokens = tokenService.generateNewTokens(
                    authenticatedUser); 

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                
                new ObjectMapper().writeValue(response.getOutputStream(), 
                    tokens);

            } catch (Exception e) {
                throw new IOException();
            }
        }

        
    }
}
