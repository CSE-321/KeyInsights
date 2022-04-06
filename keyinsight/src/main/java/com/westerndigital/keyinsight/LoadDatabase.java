package com.westerndigital.keyinsight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.westerndigital.keyinsight.Issue.IssueRepository;
import com.westerndigital.keyinsight.NotificationSettings
    .NotificationSettingsRepository;
import com.westerndigital.keyinsight.Project.ProjectRepository;
import com.westerndigital.keyinsight.Server.ServerRepository;
import com.westerndigital.keyinsight.User.User;
import com.westerndigital.keyinsight.User.UserRepository;

@Component
public class LoadDatabase implements CommandLineRunner {

    // @Autowired
    private User user;

    // inject repositories
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServerRepository serverRepository;
    
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private NotificationSettingsRepository notificationSettingsRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        serverRepository.deleteAll();
        projectRepository.deleteAll();
        issueRepository.deleteAll();
        notificationSettingsRepository.deleteAll();

        user = new User("alex@gmail.com", "jira.com");

        userRepository.save(user);

        // get data from JIRA REST client and load them to the database
    }
}
