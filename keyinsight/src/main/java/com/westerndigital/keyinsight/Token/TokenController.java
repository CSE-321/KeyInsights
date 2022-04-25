package com.westerndigital.keyinsight.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/token")
public class TokenController {

    @Autowired
    public TokenService tokenService;
    
    @GetMapping("/refresh")
    public void refreshToken(HttpServletRequest request, 
        HttpServletResponse response) {

        final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || 
            header.startsWith(AUTHORIZATION_HEADER_PREFIX) == false) {

            // handle error
        }

        String token = header.substring(AUTHORIZATION_HEADER_PREFIX.length());
        String username = tokenService.getUsernameFromToken(token);

        
    }
}
