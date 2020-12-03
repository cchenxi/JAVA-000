package io.github.cchenxi.w7.db.fx02;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbFx02ApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test0() {
        String sql = "select * from `t_customer`;";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println(list);
    }

    @Test(expected = RuntimeException.class)
    @Transactional(rollbackFor = RuntimeException.class)
    public void test1() throws SQLException {
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        String sql = "insert into `t_customer` (`pk_id`, `username`, `password`, `nick_name`) values (?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 1; i <= 1; i++) {
            preparedStatement.setInt(1, i);
            preparedStatement.setString(2, "u-" + i);
            preparedStatement.setString(3, "p-" + i);
            preparedStatement.setString(4, "n-" + i);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        throw new RuntimeException("error");
    }

    @Test
    public void test2() {
        String sql = "select * from `t_customer`;";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println(list);
    }
}
