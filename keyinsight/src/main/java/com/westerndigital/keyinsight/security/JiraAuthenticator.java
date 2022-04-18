package com.westerndigital.keyinsight.security;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.internal.async
    .AsynchronousJiraRestClientFactory;
import com.westerndigital.keyinsight.JiraUser.JiraUserService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JiraAuthenticator {

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    // @Autowired
    private JiraUserService jiraUserService;

    public boolean authenticate(String username, String password, 
        String serverUrl) {

        serverUrl = "http://jira.cloud-stm.com:8080/";

        try {
            URI jiraServerUri = new URI(serverUrl);

            JiraRestClientFactory jiraRestClientFactory = 
                new AsynchronousJiraRestClientFactory();

            JiraRestClient jiraRestClient = jiraRestClientFactory
                .createWithBasicHttpAuthentication(jiraServerUri, username, 
                    password);

            try {
                User jiraUser =  jiraRestClient.getUserClient()
                    .getUser(username).get();

                if (jiraUser == null) {
                    throw new UsernameNotFoundException(
                        "Username not found");
                }

                BCryptPasswordEncoder bCryptPasswordEncoder = 
                    new BCryptPasswordEncoder();

                // save the authenticated user to the database
                String jiraId = jiraUser.getAccountId();
                String jiraUsername = jiraUser.getName();
                String jiraPassword = bCryptPasswordEncoder.encode(password);
                String jiraServerUrl = jiraUser.getSelf().toString();

                System.out.println(jiraId);
                System.out.println(jiraUsername);
                System.out.println(jiraPassword);
                System.out.println(jiraServerUrl);

                // JiraUser user = new JiraUser(jiraId, jiraUsername, 
                //     jiraPassword, jiraServerUrl);

                // jiraUserService = new JiraUserService();
                // jiraUserService.saveUser(user);

                return true;

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return false;

    }
}
