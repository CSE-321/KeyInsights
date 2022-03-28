package com.westerndigital.keyinsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.atlassian.httpclient.api.Request;
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

		// This part of the code attempts to grabs all the issues associated with that
		// project

		// Currently, I have hard coded it to go with the single BX84 project on the
		// server

		// int issueCount = 0;
		// Iterable<Issue> allIssues = myJiraClient.getAllIssues();
		// for (Issue issue : allIssues) {
		// System.out.println(issue.getKey() + " " + issue.getIssueType().getName());
		// issueCount += 1;
		// }
		// System.out.println("There were " + issueCount + " issue(s) that I was able to
		// pull");
		// Eventually, I'll need to grab the project names I got before and pass them
		// through as a parameter.

		// This part of the code grabs information associated to that issue
		// Currently, I have hard coded in the issue and only grabbing the summary
		String issueKey = "B8X4-10277";
		Issue issue = myJiraClient.getSingleIssue(issueKey);
		// System.out.println("Summary of the issue B8X4-10277: " + issue.getSummary());
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

	private Issue getSingleIssue(String issueKey) {
		return restClient.getIssueClient().getIssue(issueKey).claim();
	}

}

// https://www.baeldung.com/jira-rest-api
// https://javadoc.io/doc/com.atlassian.jira/jira-rest-java-client-api/latest/overview-summary.html
// https://javadoc.io/static/com.atlassian.jira/jira-rest-java-client-api/5.0.4/overview-summary.html

// KPI Request 1:
// Project Status
// overview Columns:
// need Mike to help us get access to new features/bugs, otherwises can grab
// everything else

// Type(Functional Team) : use issue.getIssueType().getName() and can get all
// the types in it
// Total JIRA # & story Points : use the count feature in SQL and use
// issue.getField(customfield_10618) for story points, some have none so will
// need conditionals
// Closed JIRA # & story Points : same concept but in the JQL query, will need
// to have status is Closed
// Not Started JIRA # & story Points : same concept but in the JQL query, will
// need to have status is Waiting
// WIP JIRA & # story Points : same concept but in the JQL query, will need to
// have status is In Progress
// % of new features : still looking have to ask mike for info
// % of bugs : still looking have to ask mike for info
// % of reopen tickets : we can use the status for reopen and percentage it to
// the total jira?
// % of critical Request : for this one, in the JQL we could use priority =
// critical
// % of critical requests not completed : for this one, in the JQL, could use
// priority = critical and status not
// closed (assumption closed means completed)
// % of cancelled tickets : not sure how to figure it out
// B8X4-9137 ANA-LV