package com.westerndigital.keyinsight.security.authenticator;

public interface JiraAuthenticator {

    public boolean authenticate(String username, String password, 
        String serverUrl);
}