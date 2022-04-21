package com.westerndigital.keyinsight.JiraRestJavaClient;

import java.net.URI;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JiraRestJavaClient {

    private String username;
    private String password;
    private String jiraUrl;
    private JiraRestClient restClient;

    public JiraRestJavaClient(String username, String password, String jiraUrl) {
        this.username = username;
        this.password = password;
        this.jiraUrl = jiraUrl;
        this.restClient = getJiraRestJavaClient();
    }

    private JiraRestClient getJiraRestJavaClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getUri(this.jiraUrl), this.username, this.password);
    }

    private URI getUri(String URL) {
        return URI.create(URL);
    }

    public Iterable<BasicProject> getAllProject() {
        return restClient.getProjectClient().getAllProjects().claim();
    }

    public Project getProject(String key) {
        return restClient.getProjectClient().getProject(key).claim();
    }

    public Iterable<Issue> getAllIssues(String projectName, int currentLocation) {
        String jql = String.format("project = %s", projectName);
        return restClient.getSearchClient().searchJql(
                jql, -1, currentLocation, null)
                .claim().getIssues();
    }

    public Iterable<Issue> getAllNewCreatedOrUpdatedLast30MinutesIssues(String projectName, int currentLocation) {
        String Last30Minutes = "-30m";
        String Last10Weeks = "-10w";
        String jql = String.format("project = %s and created >= \"%s\"or updated >= \"%s\"", projectName, Last10Weeks,
                Last30Minutes);
        return restClient.getSearchClient().searchJql(
                jql, -1, currentLocation, null)
                .claim().getIssues();
    }

    public Issue getSingleIssue(String issueKey) {
        return restClient.getIssueClient().getIssue(issueKey).claim();
    }

    public User getUser(String userName) {
        return restClient.getUserClient().getUser(userName).claim();
    }

}
