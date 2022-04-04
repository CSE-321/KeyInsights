package com.westerndigital.keyinsight.User;

import com.westerndigital.keyinsight.User.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate jdbc;
    
    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    };
    
    @Override
    public Iterable<User> getAll() {

        return null;
    }
}
