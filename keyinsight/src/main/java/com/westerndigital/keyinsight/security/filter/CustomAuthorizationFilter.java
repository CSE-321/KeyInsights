package com.westerndigital.keyinsight.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Servlet;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private Dotenv dotEnv = Dotenv.load();
        
    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        // check the endpoint
        if (request.getServletPath().equals("/api/login") ||
            request.getServletPath().equals("/api/token/refresh")) {

            // go to the next filter for logging in or refreshing token 
            filterChain.doFilter(request, response);
        }

        final String AUTHORIZATION_HEADER_PREFIX = "Bearer "; 

        // get the request header
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // if it doesn't, then continue passing the request to the next filter
        if (header == null || 
            header.startsWith(AUTHORIZATION_HEADER_PREFIX) == false) {

            filterChain.doFilter(request, response);
        } else {
            // extract the authorization token
            String token = header
                .substring(AUTHORIZATION_HEADER_PREFIX.length());

            final String secretKey = dotEnv.get("SECRET_KEY");
            Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
           
            // decode the token
            DecodedJWT decodedToken = verifier.verify(token);

            // extract user data from the token
            String username = decodedToken.getSubject();

            
        }
    }
}
