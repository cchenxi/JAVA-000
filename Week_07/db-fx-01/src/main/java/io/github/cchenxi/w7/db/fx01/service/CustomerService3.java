package io.github.cchenxi.w7.db.fx01.service;

import io.github.cchenxi.w7.db.fx01.core.DataSource;
import io.github.cchenxi.w7.db.fx01.core.DataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Date: 2020-12-02
 *
 * @author chenxi
 */
@Service("cs3")
@Slf4j
public class CustomerService3 implements CustomerService {

    @Autowired
    private JdbcTemplate dynamicJdbcTemplate;

    @Override
    public void addCustomer() {
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
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
