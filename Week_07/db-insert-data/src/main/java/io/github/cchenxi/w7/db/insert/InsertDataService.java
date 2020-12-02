package io.github.cchenxi.w7.db.insert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 插入数据
 * Date: 2020-12-02
 *
 * @author chenxi
 */
@Service
@Slf4j
public class InsertDataService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 通过 {@link JdbcTemplate#execute(String)} 方法插入
     */
    public void insertByJdbcExecute() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            jdbcTemplate.execute("insert into `t_order` (`pk_id`) values ("+ i+")");
        }
        log.info("cost " + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * 通过 {@link PreparedStatement#addBatch()}
     */
    public void insertByPrepareStatementAddBatch() {
        long start = System.currentTimeMillis();
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            String sql = "insert into `t_order` (`pk_id`) values (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < 100_0000; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            log.error("", e);
        }
        log.info("cost " + (System.currentTimeMillis() - start) + " ms");
    }
}
