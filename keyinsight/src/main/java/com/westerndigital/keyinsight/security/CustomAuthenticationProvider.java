package com.westerndigital.keyinsight.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.westerndigital.keyinsight.JiraUser.JiraUser;
import com.westerndigital.keyinsight.JiraUser.JiraUserRepository;
import com.westerndigital.keyinsight.security.authenticator.JiraAuthenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomAuthenticationProvider implements 
    AuthenticationProvider {

    @Autowired
    private JiraAuthenticator jiraAuthenticator;

    @Autowired
    private JiraUserRepository jiraUserRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws 
        AuthenticationException {

        String serverUrl = String.format(
            "http://%s", request.getParameter("serverUrl"));

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();         

        // attempt to retrieve the user from the database of previously 
        // authenticated users
        JiraUser user = (JiraUser) jiraUserRepository.findByUsername(username);

        // authenticate with JIRA if the user is not in the database
        if (user == null) {
            jiraAuthenticator.authenticate(username, password, serverUrl);
            user = (JiraUser) jiraUserRepository.findByUsername(username);
        }

        String jiraUsername = user.getUsername();
        String jiraPassword = user.getPassword();
        String jiraServerUrl = user.getServerUrl();
        List<SimpleGrantedAuthority> authorities = user.getAuthorities();

        return new CustomAuthenticationToken(jiraUsername, jiraPassword, 
            jiraServerUrl, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthenticationToken.class);
    }
}
