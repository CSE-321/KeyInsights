package com.westerndigital.keyinsight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.westerndigital.keyinsight.Issue.IssueRepository;
import com.westerndigital.keyinsight.NotificationSettings.NotificationSettingsRepository;
import com.westerndigital.keyinsight.Project.ProjectRepository;
import com.westerndigital.keyinsight.Server.ServerRepository;
import com.westerndigital.keyinsight.User.UserRepository;
import com.westerndigital.keyinsight.JiraRestJavaClient.JiraRestJavaClient;

import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.domain.BasicProject;

import io.github.cdimascio.dotenv.Dotenv;

@Component
public class LoadDatabase implements CommandLineRunner {

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
        try {
            Dotenv dotenv = Dotenv.load();
            JiraRestJavaClient myJiraClient = new JiraRestJavaClient(dotenv.get("JIRA_USERNAME"),
                    dotenv.get("JIRA_PASSWORD"), dotenv.get("JIRA_URL"));
            Iterable<BasicProject> allProjects = myJiraClient.getAllProject();
            System.out.println("hi");

        } catch (RestClientException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
