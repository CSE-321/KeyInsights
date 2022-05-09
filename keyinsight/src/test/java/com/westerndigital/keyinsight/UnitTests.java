package com.westerndigital.keyinsight;

import com.westerndigital.keyinsight.JiraUser.JiraUser;
import com.westerndigital.keyinsight.JiraUser.JiraUserRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UnitTests {

    @Autowired
    private JiraUserRepository jiraUserRepository;

    // test user is being saved to the database
    @Test
    public void saveUserTest() {
        // create a new user
        JiraUser user = new JiraUser("john", "1234", 
            "http://jira.cloud.stm:8080");

        // save the user to the database
        jiraUserRepository.save(user);

        // get the usernames to compare
        String username = user.getUsername();

        String usernameFromDb = jiraUserRepository
            .findByUsername(user.getUsername())
            .getUsername();

        // test whether the user is saved to the database
        assertThat(usernameFromDb).isEqualTo(username);
    }

    // test updated notification settings are being saved to the database
    // @Test
    // public void saveUpdatedNotificationSettingsTest() {

    // }

    // test retrieval of notification settings from the database
    // @Test
    // public void getNotificationSettingsTest() {

    // }
    
}
