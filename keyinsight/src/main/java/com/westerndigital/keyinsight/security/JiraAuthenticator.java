package com.westerndigital.keyinsight.security;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.westerndigital.keyinsight.JiraUser.JiraUser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JiraAuthenticator {
    
    public boolean authenticate(String username, String password, String serverUrl) {
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
                    throw new UsernameNotFoundException("Username not found");
                }

                // String jiraUsername = jiraUser.getName();
                // String jiraEmail = jiraUser.getEmailAddress();
                // String jiraServerUrl = jiraUser.getSelf().toString();

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
