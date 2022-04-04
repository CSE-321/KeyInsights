package com.westerndigital.keyinsight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.westerndigital.keyinsight.Project;

@Repository
public class JdbcProjectRepository implements 
    ProjectRepository {
    
    private JdbcTemplate jdbc;    

    @Autowired
    public JdbcProjectRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    };

    @Override
    public Iterable<Project> getAll() {

        return null;
    }
    
}