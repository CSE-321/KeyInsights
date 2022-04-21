package com.westerndigital.keyinsight.Login;

import java.util.Collection;

import com.westerndigital.keyinsight.JiraUser.JiraUser;
import com.westerndigital.keyinsight.security.CustomAuthenticationToken;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public void login(@RequestBody JiraUser jiraUser) {
        String username = jiraUser.getUsername();
        String password = jiraUser.getPassword();
        String serverUrl = jiraUser.getServerUrl();
        Collection<? extends GrantedAuthority> authorities = 
            jiraUser.getAuthorities();

        authenticationManager.authenticate(
            new CustomAuthenticationToken(username, password, serverUrl, authorities)
        );

        System.out.println("AUTHENTICATION MANAGER SUCCESSFULLY AUTHENTICATED USER");
    }
}
