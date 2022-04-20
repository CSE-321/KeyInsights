package com.westerndigital.keyinsight.security.filter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {
        
    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain) {

        try {
            filterChain.doFilter(request, response);
        } catch(Exception e) {
            log.error("Error while processing filter: ", e.getMessage());
        }
    }
}
