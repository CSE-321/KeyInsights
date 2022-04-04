package com.westerndigital.keyinsight;

import com.westerndigital.keyinsight.Issue;

public interface IssueRepository {

    // get all JIRA issues from the database
    Iterable<Issue> getAll();
}
