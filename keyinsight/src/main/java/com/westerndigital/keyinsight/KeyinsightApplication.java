package com.westerndigital.keyinsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;

import io.github.cdimascio.dotenv.Dotenv;

import com.westerndigital.keyinsight.JiraRestJavaClient.JiraRestJavaClient;

@SpringBootApplication
public class KeyinsightApplication {

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(KeyinsightApplication.class, args);

		// This part of the code connects the basic user authication to the JIRA server
		// using an .env file
		try {
			long start = System.nanoTime();
			Dotenv dotenv = Dotenv.load();
			JiraRestJavaClient myJiraClient = new JiraRestJavaClient(dotenv.get("JIRA_USERNAME"),
					dotenv.get("JIRA_PASSWORD"), dotenv.get("JIRA_URL"));

			// This part of the code grabs all projects that the user has access to from the
			// JIRA Server

			ArrayList<String> projectName = new ArrayList<String>();
			ArrayList<String> projectLeadName = new ArrayList<String>();
			ArrayList<String> projectLeadAvatarUri = new ArrayList<String>();
			ArrayList<String> issueName = new ArrayList<String>();
			ArrayList<String> issueMainType = new ArrayList<String>();
			ArrayList<String> issueFromProject = new ArrayList<String>();
			ArrayList<Float> issueStoryPoints = new ArrayList<Float>();
			ArrayList<String> issueSecondaryType = new ArrayList<String>();
			ArrayList<String> issuePriority = new ArrayList<String>();
			ArrayList<String> issueResolution = new ArrayList<String>();
			ArrayList<String> issueStatus = new ArrayList<String>();
			ArrayList<String> issueCreateDate = new ArrayList<String>();
			ArrayList<String> issueCreateTime = new ArrayList<String>();
			ArrayList<String> issueDueDate = new ArrayList<String>();
			ArrayList<String> issueDueTime = new ArrayList<String>();
			ArrayList<String> issueUpdatedDate = new ArrayList<String>();
			ArrayList<String> issueUpdatedTime = new ArrayList<String>();
			ArrayList<String> issueAssignee = new ArrayList<String>();

			HashMap<String, String> fieldValues = new HashMap<String, String>();

			int projectCount = 0;
			int issueCount = 0;
			Iterable<BasicProject> allProjects = myJiraClient.getAllProject();
			for (BasicProject project : allProjects) {
				String projectUrl = project.getKey();
				Project singleProject = myJiraClient.getProject(projectUrl);
				projectName.add(singleProject.getName());
				User projectLead = myJiraClient.getUser(singleProject.getLead().getName());
				projectLeadName.add(projectLead.getDisplayName());
				projectLeadAvatarUri.add(projectLead.getAvatarUri().toString());
				String id = "10";
				while (Integer.parseInt(id) != 1) {
					Iterable<Issue> allIssues = myJiraClient.getAllIssues(singleProject.getName(), issueCount);
					for (Issue issue : allIssues) {
						id = issue.getKey();
						id = id.substring(id.indexOf('-') + 1);
						issueName.add(issue.getKey());
						issueMainType.add(issue.getIssueType().getName());
						issueFromProject.add(issue.getProject().getName());
						issueStatus.add(issue.getStatus().getName());

						if (issueCount == 0) {
							Iterable<IssueField> allIssueFields = issue.getFields();
							for (IssueField issueField : allIssueFields) {
								fieldValues.put(issueField.getName(), issueField.getId());
								// System.out.println(issueField.getId() + " : " + issueField.getName());
							}
							// Found out that Story Points are customfield_10618 for this server B8X4
							// Found out that Second Type that has Bugs are customfield_12628 for this
							// server B8X4
						}
						// System.out.println(issueCount);

						String createDate = String.format("%d-%d-%d",
								issue.getCreationDate().getYear(),
								issue.getCreationDate().getMonthOfYear(),
								issue.getCreationDate().getDayOfMonth());

						issueCreateDate.add(createDate);

						String createTime = String.format("%d:%d",
								issue.getCreationDate().getHourOfDay(),
								issue.getCreationDate().getMinuteOfHour());

						issueCreateTime.add(createTime);

						if (issue.getDueDate() == null) {
							issueDueDate.add(null);
							issueDueTime.add(null);
						} else if (issue.getDueDate() != null) {
							String dueDate = String.format("%d-%d-%d", issue.getDueDate().getYear(),
									issue.getDueDate().getMonthOfYear(),
									issue.getDueDate().getDayOfMonth());

							issueDueDate.add(dueDate);

							String dueTime = String.format("%d:%d", issue.getDueDate().getHourOfDay(),
									issue.getDueDate().getMinuteOfHour());

							issueDueTime.add(dueTime);
						}

						String updatedDate = String.format("%d-%d-%d",
								issue.getUpdateDate().getYear(),
								issue.getUpdateDate().getMonthOfYear(),
								issue.getUpdateDate().getDayOfMonth());

						issueUpdatedDate.add(updatedDate);

						String updatedTime = String.format("%d:%d",
								issue.getUpdateDate().getHourOfDay(),
								issue.getUpdateDate().getMinuteOfHour());

						issueUpdatedTime.add(updatedTime);

						if (issue.getField(fieldValues.get("Story Points")).getValue() == null) {
							issueStoryPoints.add(null);
						} else if (issue.getField(fieldValues.get("Story Points")).getValue() != null) {
							float storyPoints = Float
									.parseFloat(issue.getField(fieldValues.get("Story Points")).getValue().toString());
							issueStoryPoints.add(storyPoints);
						}

						if (issue.getField(fieldValues.get("Type")).getValue() == null) {
							issueSecondaryType.add(null);
						} else if (issue.getField(fieldValues.get("Type")).getValue() != null) {
							String secondaryTypeValueJsonString = issue.getField(fieldValues.get("Type")).getValue()
									.toString();
							ObjectMapper mapper = new ObjectMapper();
							JsonNode node = mapper.readTree(secondaryTypeValueJsonString);
							String SecondaryTypeValue = node.get("value").asText();
							// https://
							// stackoverflow.com/questions/5245840/how-to-convert-jsonstring-to-jsonobject-in-java
							issueSecondaryType.add(SecondaryTypeValue);
						}

						// found out that Cancelled Projects are under resolution so issue.getResolution
						if (issue.getResolution() == null) {
							issueResolution.add("null");
						} else if (issue.getResolution() != null) {
							issueResolution.add(issue.getResolution().getName());
						}

						if (issue.getPriority() == null) {
							issuePriority.add("null");
						} else if (issue.getPriority() != null) {
							issuePriority.add(issue.getPriority().getName());
						}

						if (issue.getAssignee() == null) {
							issueAssignee.add("null");
						} else if (issue.getAssignee() != null) {
							issueAssignee.add(issue.getAssignee().getDisplayName());
						}
						issueCount += 1;
					}
					// System.out.println("There were " + projectCount + " project(s)");
					// System.out.println("The Project Name(s) were: " + projectName.get(0));
					// System.out.println("The Project Lead(s) was; " + projectLeadName.get(0));
					// System.out.println("There were " + issueCount + " issue(s) that I was able to
					// pull");
					// System.out.println("The Issue Name(s) were: " + issueName.get(0));
					// System.out.println("The Issue Project were: " + issueFromProject.get(0));
					// System.out.println("The Issue Main Type were: " + issueMainType.get(0));
					// System.out.println("The Issue Story Points were: " +
					// issueStoryPoints.get(0));
					// System.out.println("The Issue Secondary Type were: " +
					// issueSecondaryType.get(0));
					// System.out.println("The Issue Priority were: " + issuePriority.get(0));
					// System.out.println("The Issue Resolution were: " + issueResolution.get(0));
					// System.out.println("The Issue Status were: " + issueStatus.get(0));
					// System.out.println("The Issue Create Date were: " + issueCreateDate.get(0));
					// System.out.println("The Issue Create Time were: " + issueCreateTime.get(0));
					// System.out.println("The Issue Updated Date were: " +
					// issueUpdatedDate.get(0));
					// System.out.println("The Issue Updated Time were: " +
					// issueUpdatedTime.get(0));
					// System.out.println("The Issue Due Date were: " + issueDueDate.get(0));
					// System.out.println("The Issue Due Time were: " + issueDueTime.get(0));
					// System.out.println("The Issue Assignee was: " + issueAssignee.get(0));
				}
				projectCount += 1;
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
			System.out.println("The Issue Create Date were: " + issueCreateDate.get(0));
			System.out.println("The Issue Create Time were: " + issueCreateTime.get(0));
			System.out.println("The Issue Assignee was: " + issueAssignee.get(0));

			myJiraClient.getRestClient().close();
			long finish = System.nanoTime();
			long timeElapsed = finish - start;
			long convert = TimeUnit.MINUTES.convert(timeElapsed, TimeUnit.NANOSECONDS);
			System.out.println("It took this program: " + convert + " minutes");

		} catch (RestClientException e) {
			System.out.println(e.getLocalizedMessage());
		}

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
