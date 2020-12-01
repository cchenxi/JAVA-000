package io.github.cchenxi.w7.db.fx01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbFx01ApplicationTests {
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;

    @Test
    public void testPrimary() {
        primaryJdbcTemplate.execute("insert into t1(id) values(111),(212);");
    }

    @Test
    public void testSecondary() {
        List<Integer> list = secondaryJdbcTemplate.query("select * from t1;", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return Integer.valueOf(resultSet.getString("id"));
            }
        });

        System.out.println(list);
    }
}
