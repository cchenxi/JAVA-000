package io.github.cchenxi.w7.db.fx01.service;

import io.github.cchenxi.w7.db.fx01.core.DataSource;
import io.github.cchenxi.w7.db.fx01.core.DataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 1.2 基于 {@link AbstractRoutingDataSource} 和自定义注解 {@link DataSource} 实现方法级的数据源自动切换
 * Date: 2020-12-02
 *
 * @author chenxi
 */
@Service("cs2")
@Slf4j
public class CustomerService2 implements CustomerService {

    @Autowired
    private JdbcTemplate dynamicJdbcTemplate;

    @Override
    public void addCustomer() {
        try {
            Connection connection = DataSourceUtils.getConnection(dynamicJdbcTemplate.getDataSource());
            try {
                log.info("connection url is {}", connection.getMetaData().getURL());
            } catch (SQLException e) {

            }
            String sql = "insert into `t_customer` (`pk_id`, `username`, `password`, `nick_name`) values (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i = 1; i <= 100; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "u-" + i);
                preparedStatement.setString(3, "p-" + i);
                preparedStatement.setString(4, "n-" + i);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            log.error("", e);
        }
    }

    @Override
    @DataSource(value = DataSourceType.READONLY)
    public void getAllCustomers() {
        Connection connection = DataSourceUtils.getConnection(dynamicJdbcTemplate.getDataSource());
        try {
            log.info("connection url is {}", connection.getMetaData().getURL());
        } catch (SQLException e) {

        }
        String sql = "select * from `t_customer`;";
        List<Map<String, Object>> list = dynamicJdbcTemplate.queryForList(sql);
        System.out.println(list);
    }
}
