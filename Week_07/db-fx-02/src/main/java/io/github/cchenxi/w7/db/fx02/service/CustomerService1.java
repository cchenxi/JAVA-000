package io.github.cchenxi.w7.db.fx02.service;

import io.github.cchenxi.w7.db.fx02.exception.RollbackException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Date: 2020-12-04
 *
 * @author chenxi
 */
@Service
public class CustomerService1 implements CustomerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void clear() {
        jdbcTemplate.execute("truncate `t_customer`;");
    }

    @Override
    public void addCustomer() {
        jdbcTemplate.execute("insert into `t_customer` (`pk_id`, `username`, `password`, `nick_name`) values (1, 'u1', 'p1', 'n1');");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void addCustomerRollback() throws RollbackException {
        jdbcTemplate.execute("insert into `t_customer` (`pk_id`, `username`, `password`, `nick_name`) values (2, 'u2', 'p2', 'n2');");
        throw new RollbackException();
    }

    @Override
    public long getCustomersCount() {
        return jdbcTemplate.queryForObject("select count(1) from `t_customer`", Long.class);
    }
}
