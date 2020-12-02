package io.github.cchenxi.w7.db.fx01.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 1.0 根据具体的Service方法 选择不同的数据源
 * Date: 2020-12-02
 *
 * @author chenxi
 */
@Service("cs1")
@Slf4j
public class CustomerService1 implements CustomerService {
    @Autowired
    private JdbcTemplate masterJdbcTemplate;

    @Autowired
    private JdbcTemplate slave01JdbcTemplate;

    @Override
    public void addCustomer() {
        try {
            Connection connection = masterJdbcTemplate.getDataSource().getConnection();
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
    public void getAllCustomers() {
        String sql = "select * from `t_customer`;";

        List<Map<String, Object>> list = slave01JdbcTemplate.queryForList(sql);
//        List<Customer> list = secondaryJdbcTemplate.queryForList(sql, new RowMapper<Customer>() {
//            @Override
//            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
//                Customer customer = new Customer();
//                customer.setPkId(new BigInteger(resultSet.getString("pk_id")));
//                customer.setUsername(resultSet.getString("username"));
//                customer.setPassword(resultSet.getString("password"));
//                customer.setNickName(resultSet.getString("nick_name"));
//                return customer;
//            }
//        });

        System.out.println(list);
    }

}
