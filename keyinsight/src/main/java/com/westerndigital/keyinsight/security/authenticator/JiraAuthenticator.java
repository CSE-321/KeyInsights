package com.westerndigital.keyinsight.security.authenticator;

import com.westerndigital.keyinsight.JiraUser.JiraUser;

public interface JiraAuthenticator {

    public boolean authenticate(String username, String password, 
        String serverUrl);
    
    public void saveUserToDatabase(JiraUser user);
}