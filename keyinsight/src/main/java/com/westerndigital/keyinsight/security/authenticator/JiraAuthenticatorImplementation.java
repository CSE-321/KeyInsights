package com.westerndigital.keyinsight.security.authenticator;

import java.net.URI;
import java.net.URISyntaxException;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.internal.async
    .AsynchronousJiraRestClientFactory;
import com.westerndigital.keyinsight.JiraUser.JiraUser;
import com.westerndigital.keyinsight.JiraUser.JiraUserService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JiraAuthenticatorImplementation implements JiraAuthenticator {

    private JiraUserService jiraUserService;

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

                BCryptPasswordEncoder bCryptPasswordEncoder = 
                    new BCryptPasswordEncoder();

                // save the authenticated user to the database
                String jiraId = jiraUser.getAccountId();
                String jiraUsername = jiraUser.getName();
                String jiraPassword = bCryptPasswordEncoder.encode(password);
                String jiraServerUrl = jiraUser.getSelf().toString();

                JiraUser user = new JiraUser(jiraId, jiraUsername, 
                    jiraPassword, jiraServerUrl);

                saveUserToDatabase(user);

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

    public void saveUserToDatabase(JiraUser user) {
        System.out.println("Saving user to the database");
    }
}
