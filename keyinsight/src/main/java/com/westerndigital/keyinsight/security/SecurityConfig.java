package com.westerndigital.keyinsight.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders
    .AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration
    .EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration
    .WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication
    .UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    // private final UserDetailsService userDetailsService;
    // private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();        
    // }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) 
        throws Exception {

        auth
            .authenticationProvider(customAuthenticationProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CustomAuthenticationFilter customAuthenticationFilter = new 
            CustomAuthenticationFilter();

        customAuthenticationFilter
            .setAuthenticationManager(authenticationManager());

        http
            .cors().and().csrf().disable()
            .addFilterAt(customAuthenticationFilter, 
                UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/login").permitAll();
            // .anyRequest()
            // .authenticated();
    }
}
