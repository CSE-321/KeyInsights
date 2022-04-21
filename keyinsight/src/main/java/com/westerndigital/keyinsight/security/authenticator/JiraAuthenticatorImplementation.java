package com.westerndigital.keyinsight.security.authenticator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.internal.async
    .AsynchronousJiraRestClientFactory;
import com.westerndigital.keyinsight.JiraRole.JiraRole;
import com.westerndigital.keyinsight.JiraUser.JiraUser;
import com.westerndigital.keyinsight.JiraUser.JiraUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JiraAuthenticatorImplementation implements JiraAuthenticator {

    @Autowired
    private JiraUserRepository jiraUserRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String username, String password, 
        String serverUrl) {

        serverUrl = "http://jira.cloud-stm.com:8080/";

        try {
            URI jiraServerUri = new URI(serverUrl);

            JiraRestClientFactory jiraRestClientFactory = 
                new AsynchronousJiraRestClientFactory();

            JiraRestClient jiraRestClient = jiraRestClientFactory
                .createWithBasicHttpAuthentication(
                    jiraServerUri, username, password);

            try {
                User jiraUser =  jiraRestClient.getUserClient()
                    .getUser(username).get();

                // save the authenticated user to the database
                String jiraUsername = jiraUser.getName();
                String jiraPassword = passwordEncoder.encode(password);
                String jiraServerUrl = jiraUser.getSelf().toString();

                JiraUser user = new JiraUser(jiraUsername, 
                    jiraPassword, jiraServerUrl);

                Collection<SimpleGrantedAuthority> authorities = 
                    user.getAuthorities();

                jiraUser.getGroups().getItems().forEach(group -> {
                    if (group == "jira-administrators") {
                        authorities.add(new SimpleGrantedAuthority(
                            JiraRole.ROLE_ADMIN));
                    } 

                    if (group == "jira-users") {
                        authorities.add(new SimpleGrantedAuthority(
                            JiraRole.ROLE_USER));
                    }
                });


                jiraUserRepository.save(user);
                    
                return true;

            } catch(Exception e) {
                throw new UsernameNotFoundException(
                    "Username not found");
            }
            
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return false;
    }
}
