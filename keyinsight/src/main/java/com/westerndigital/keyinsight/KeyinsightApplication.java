package com.westerndigital.keyinsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.atlassian.jira.rest.client.api.domain.Issue;

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

		ArrayList<String> projectName = new ArrayList<String>();
		ArrayList<String> projectLeadName = new ArrayList<String>();
		ArrayList<String> issueName = new ArrayList<String>();
		ArrayList<String> issueMainType = new ArrayList<String>();
		ArrayList<String> issueFromProject = new ArrayList<String>();
		ArrayList<String> issueStoryPoints = new ArrayList<String>();
		ArrayList<String> issueSecondaryType = new ArrayList<String>();
		ArrayList<String> issuePriority = new ArrayList<String>();
		ArrayList<String> issueResolution = new ArrayList<String>();
		ArrayList<String> issueStatus = new ArrayList<String>();

		int projectCount = 0;
		int issueCount = 0;
		Iterable<BasicProject> allProjects = myJiraClient.getAllProject();
		for (BasicProject project : allProjects) {
			String projectUrl = project.getKey();
			projectCount += 1;
			Project singleProject = myJiraClient.getProject(projectUrl);
			projectName.add(singleProject.getName());
			projectLeadName.add(singleProject.getLead().getDisplayName());
			String id = "10";
			while (Integer.parseInt(id) != 1) {
				Iterable<Issue> allIssues = myJiraClient.getAllIssues(singleProject.getName(), issueCount);
				for (Issue issue : allIssues) {
					id = issue.getKey();
					id = id.substring(id.indexOf('-') + 1);
					issueName.add(issue.getKey());
					issueMainType.add(issue.getIssueType().getName());
					issueFromProject.add(issue.getProject().getName());

					if (issue.getField("customfield_10618").getValue() == null) {
						issueStoryPoints.add("-1");
					} else if (issue.getField("customfield_10618").getValue() != null) {
						issueStoryPoints.add(issue.getField("customfield_10618").getValue().toString());
					}

					if (issue.getPriority() == null) {
						issuePriority.add("-1");
					} else if (issue.getPriority() != null) {
						issuePriority.add(issue.getPriority().getName());
					}

					if (issue.getField("customfield_12628").getValue() == null) {
						issueSecondaryType.add("-1");
					} else if (issue.getField("customfield_12628").getValue() != null) {
						String secondaryTypeValueJsonString = issue.getField("customfield_12628").getValue().toString();
						ObjectMapper mapper = new ObjectMapper();
						JsonNode node = mapper.readTree(secondaryTypeValueJsonString);
						String SecondaryTypeValue = node.get("value").asText();
						// https://stackoverflow.com/questions/5245840/how-to-convert-jsonstring-to-jsonobject-in-java
						issueSecondaryType.add(SecondaryTypeValue);
					}

					if (issue.getResolution() == null) {
						issueResolution.add("-1");
					} else if (issue.getResolution() != null) {
						issueResolution.add(issue.getResolution().getName());
					}
					issueStatus.add(issue.getStatus().getName());
					issueCount += 1;
				}
			}
		}
		System.out.println("There were " + projectCount + " project(s)");
		System.out.println("The Project Name(s) were: " + projectName.get(0));
		System.out.println("The Project Lead(s) was; " + projectLeadName.get(0));
		System.out.println("There were " + issueCount + " issue(s) that I was able to pull");
		System.out.println("The Issue Name(s) were: " + issueName.get(0));
		System.out.println("The Issue Project were: " + issueFromProject.get(0));
		System.out.println("The Issue Main Type were: " + issueMainType.get(0));
		System.out.println("The Issue Story Points were: " + issueStoryPoints.get(0));
		System.out.println("The Issue Secondary Type were: " + issueSecondaryType.get(0));
		System.out.println("The Issue Priority were: " + issuePriority.get(0));
		System.out.println("The Issue Resolution were: " + issueResolution.get(0));
		System.out.println("The Issue Status were: " + issueStatus.get(0));

		// This part of the code grabs information associated to that issue
		// Currently, I have hard coded in the issue and only grabbing the summary
		// String issueKey = "B8X4-10275";
		// Issue issue = myJiraClient.getSingleIssue(issueKey);
		// System.out.println("Issue Type B8X4-10277: " + issue.getResolution());
		// Eventually, I'll need to grab the issue keys I got before and pass them
		// through as a parameter.

		// This part of the code grabs information about issueFields assoicated to that
		// issue
		// Iterable<IssueField> allIssueFields = issue.getFields();
		// for (IssueField issueField : allIssueFields) {
		// System.out.println(issueField.getId() + " : " + issueField.getName());
		// }
		// Found out that Story Points are customfield_10618 for this server B8X4
		// Found out that Second Type that has Bugs are customfield_12628 for this
		// server B8X4
		// found out that Cancelled Projects are under resolution so issue.getResolution

		myJiraClient.restClient.close();
	}

	private JiraRestClient getJiraRestClient() {
		return new AsynchronousJiraRestClientFactory()
				.createWithBasicHttpAuthentication(getUri(this.jiraUrl), this.username, this.password);
	}

	private URI getUri(String URL) {
		return URI.create(URL);
	}

	private Iterable<BasicProject> getAllProject() {
		return restClient.getProjectClient().getAllProjects().claim();
	}

	private Project getProject(String key) {
		return restClient.getProjectClient().getProject(key).claim();
	}

	private Iterable<Issue> getAllIssues(String projectName, int currentLocation) {
		String jql = String.format("project = %s", projectName);
		return restClient.getSearchClient().searchJql(
				jql, -1, currentLocation, null)
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

// % of bugs : can use issue.getField(customfield_12628) for B8X4 server to get
// issues that are bugs

// % of reopen tickets : we can use the status for reopen and percentage it to
// the total jira?

// % of critical Request : for this one, in the JQL we could use priority =
// critical

// % of critical requests not completed : for this one, in the JQL, could use
// priority = critical and status not
// closed (assumption closed means completed)

// % of cancelled tickets : cancelled issues we can use
// issue.getResolution().getName() and find issues with resolution Project
// Cancelled
