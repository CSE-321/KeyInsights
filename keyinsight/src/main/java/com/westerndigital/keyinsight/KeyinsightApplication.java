package com.westerndigital.keyinsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import java.io.IOException;
import java.net.URI;

import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;

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

		// This part of the code connects the basic user authication to the JIRA server
		// using an .env file
		Dotenv dotenv = Dotenv.load();
		KeyinsightApplication myJiraClient = new KeyinsightApplication(dotenv.get("JIRA_USERNAME"),
				dotenv.get("JIRA_PASSWORD"), dotenv.get("JIRA_URL"));
		//

		// This part of the code grabs all projects that the user has access to from the
		// JIRA Server
		// int projectCount = 0;
		// Iterable<BasicProject> allProjects = myJiraClient.getAllProject();
		// for (BasicProject project : allProjects) {
		// System.out.println(project.getName());
		// projectCount += 1;
		// }
		// System.out.println("There were " + projectCount + " project(s)");
		//

		// This part of the code attempts to grabs all the issues associated with that
		// project

		// Currently, I have hard coded it to go with the single BX84 project on the
		// server

		// int issueCount = 0;
		// Iterable<Issue> allIssues = myJiraClient.getAllIssues();
		// for (Issue issue : allIssues) {
		// System.out.println(
		// issue.getKey() + " " + issue.getIssueType().getName());
		// issueCount += 1;
		// }
		// System.out.println("There were " + issueCount + " issue(s) that I was able to
		// pull");
		// Eventually, I'll need to grab the project names I got before and pass them
		// through as a parameter.

		// This part of the code grabs information associated to that issue
		// Currently, I have hard coded in the issue and only grabbing the summary
		String issueKey = "B8X4-10277";
		Issue issue = myJiraClient.getIssue(issueKey);
		System.out.println("Summary of the issue B8X4-10277: " + issue.getSummary());
		// Eventually, I'll need to grab the issue keys I got before and pass them
		// through as a parameter.

		// This part of the code grabs information about issueFields assoicated to that
		// issue
		Iterable<IssueField> allIssueFields = issue.getFields();
		for (IssueField issueField : allIssueFields) {
			System.out.println(issueField.getId() + " : " + issueField.getName());
		}
		// Found out that Story Points are customfield_10618 for this server

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

	private Iterable<Issue> getAllIssues() {
		return restClient.getSearchClient().searchJql(
				"project = B8X4", -1, 0, null)
				.claim().getIssues();
	}

	private Issue getIssue(String issueKey) {
		return restClient.getIssueClient().getIssue(issueKey).claim();
	}

}

// https://www.baeldung.com/jira-rest-api
// https://javadoc.io/doc/com.atlassian.jira/jira-rest-java-client-api/latest/overview-summary.html
// https://javadoc.io/static/com.atlassian.jira/jira-rest-java-client-api/5.0.4/overview-summary.html