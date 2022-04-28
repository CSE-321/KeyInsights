package com.westerndigital.keyinsight.security;

import com.westerndigital.keyinsight.security.filter.CustomAuthenticationFilter;
import com.westerndigital.keyinsight.security.filter.CustomAuthorizationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders
    .AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration
    .EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration
    .WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication
    .UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // get the authentication manager to authenticate in the controller
    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) 
        throws Exception {

        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CustomAuthenticationFilter customAuthenticationFilter = new 
            CustomAuthenticationFilter();

        customAuthenticationFilter
            .setAuthenticationManager(authenticationManager());

        // enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // set session management to stateless
        http = http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and();
        
        // add the custom authentication filter to the list of filters
        // that the login request goes through
        http
            .addFilterAt(customAuthenticationFilter, 
                UsernamePasswordAuthenticationFilter.class);

        // add the custom authorization filter to validate the JWT tokens
        // before the custom authentication filter
        http
            .addFilterBefore(new CustomAuthorizationFilter(),
                CustomAuthenticationFilter.class);

        // require that all requests to the API to be authenticated
        http
            .authorizeRequests()
            .anyRequest()
            // temporarily disable authentication for all back-end endpoints
            // .permitAll();
            .authenticated();
    }
}
