package com.westerndigital.keyinsight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.westerndigital.keyinsight.Issue;

@Repository
public class JdbcIssueRepository implements IssueRepository {
    
    private JdbcTemplate jdbc;    

    @Autowired
    public JdbcIssueRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    };

    @Override
    public Iterable<Issue> getAll() {

        return null;
    }
    
}
