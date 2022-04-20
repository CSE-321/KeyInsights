package com.westerndigital.keyinsight.JiraRole;

import org.springframework.security.core.GrantedAuthority;

public class JiraRole implements GrantedAuthority {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMID = "ROLE_ADMIN";
    private String authority;

    public JiraRole() {}

    public String getAuthority() {
        return authority;
    }
}
