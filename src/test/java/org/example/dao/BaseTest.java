package org.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
}
