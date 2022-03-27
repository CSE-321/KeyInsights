package com.westerndigital.keyinsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import java.io.IOException;
import java.net.URI;

import com.atlassian.jira.rest.client.api.domain.BasicProject;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class KeyinsightApplication {
	private String username;
	private String password;
	private String jiraUrl;
	private JiraRestClient restClient;

	private KeyinsightApplication(String username, String password, String jiraUrl) {
		this.username = username;
		this.password = password;
		this.jiraUrl = jiraUrl;
		this.restClient = getJiraRestClient();
	}

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(KeyinsightApplication.class, args);
		Dotenv dotenv = Dotenv.load();
		KeyinsightApplication myJiraClient = new KeyinsightApplication(dotenv.get("JIRA_USERNAME"),
				dotenv.get("JIRA_PASSWORD"), dotenv.get("JIRA_URL"));
		// System.out.println("Hi World");
		int projectCount = 0;
		Iterable<BasicProject> allProjects = myJiraClient.getAllProject();
		for (BasicProject project : allProjects) {
			System.out.println(project.getName());
			projectCount += 1;
		}
		System.out.println("There were " + projectCount + " project(s)");
		myJiraClient.restClient.close();
	}

	private JiraRestClient getJiraRestClient() {
		return new AsynchronousJiraRestClientFactory()
				.createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
	}

	private URI getJiraUri() {
		return URI.create(this.jiraUrl);
	}

	private Iterable<BasicProject> getAllProject() {
		return restClient.getProjectClient().getAllProjects().claim();
	}

}

// https://www.baeldung.com/jira-rest-api
// https://javadoc.io/doc/com.atlassian.jira/jira-rest-java-client-api/latest/overview-summary.html
// https://javadoc.io/static/com.atlassian.jira/jira-rest-java-client-api/5.0.4/overview-summary.html