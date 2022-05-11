package com.westerndigital.keyinsight.security.authenticator;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.UserPOJO.GroupInfo;
import com.westerndigital.keyinsight.JiraRestAPIsPOJO.UserPOJO.UserJson;
import com.westerndigital.keyinsight.JiraRole.JiraRole;
import com.westerndigital.keyinsight.JiraUser.JiraUser;
import com.westerndigital.keyinsight.JiraUser.JiraUserRepository;
import com.westerndigital.keyinsight.Notification.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

@Component
public class JiraAuthenticatorImplementation implements JiraAuthenticator {

    @Autowired
    private JiraUserRepository jiraUserRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String username, String password,
            String serverUrl) {

        serverUrl = "http://jira.cloud-stm.com:8080/";
        System.out.println(username + " " + password + " " + serverUrl);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String restUrl = serverUrl + "rest/api/latest/user?username=" + username + "&expand=groups";
            HttpResponse<JsonNode> getSingleUser = Unirest.get(restUrl).basicAuth(username, password)
                    .header("Accept", "application/json").asJson();
            List<UserJson> UserJsons = mapper.readValue(getSingleUser.getBody().getArray().toString(),
                    new TypeReference<List<UserJson>>() {
                    });
            try {
                for(UserJson UserJson : UserJsons){
                    if (jiraUserRepository.findByUsername(username) == null) {
                        String jiraUsername = UserJson.getName();
                        String jiraPassword = passwordEncoder.encode(password);
                        String jiraServerUrl = UserJson.getSelf();
                        String jiraEmail = UserJson.getEmailAddress();
                        JiraUser user = new JiraUser(jiraUsername, jiraPassword, jiraEmail, jiraServerUrl);
                        if (UserJson.getGroups().getSize() != 0) {
                            List<GroupInfo> groupInfoList = UserJson.getGroups().getItems();
                            for (GroupInfo groupInfo : groupInfoList) {
                                if (groupInfo.getName().equals("jira-users")) {
                                    user.addRole(JiraRole.ROLE_USER);
                                }
                                if (groupInfo.getName().equals("jira-administrators")) {
                                    user.addRole(JiraRole.ROLE_ADMIN);
                                }
                            }
                        }
                        jiraUserRepository.save(user);
                    }
                }
                
                return true;
            } catch (Exception e) {
                throw new UsernameNotFoundException("Username not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}