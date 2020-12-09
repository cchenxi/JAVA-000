package io.github.cchenxi.shardingdemo.service;

import io.github.cchenxi.shardingdemo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Date: 2020-12-09
 *
 * @author chenxi
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addOrder(final Order order) {
        jdbcTemplate.update("insert into `t_order` (`buyer_id`, `sku_id`) values (?, ?)",
                order.getBuyerId(), order.getSkuId());
    }

    @Override
    @Transactional
    public void deleteOrder(final BigInteger orderId, final BigInteger buyerId) {
        jdbcTemplate.update("delete from `t_order` where pk_id = ? and buyer_id = ?", orderId, buyerId);
    }

    @Override
    public void updateOrder(final Order order) {
        jdbcTemplate.update("update `t_order` set `sku_id` = ? where `pk_id` = ? and `buyer_id` = ?",
                order.getSkuId(), order.getPkId(), order.getBuyerId());
    }

    @Override
    public Order getOrder(final BigInteger orderId, final BigInteger buyerId) {
        return jdbcTemplate.queryForObject("select * from `t_order` where pk_id = ? and buyer_id = ?",
                (rs, rowNum) -> new Order()
                        .setPkId(new BigInteger(rs.getString("pk_id")))
                        .setSkuId(new BigInteger(rs.getString("sku_id")))
                        .setBuyerId(new BigInteger(rs.getString("buyer_id"))),
                orderId, buyerId);
    }

    @Override
    public List<Order> getOrderList() {
        return jdbcTemplate.query("select * from `t_order`",
                (rs, rowNum) -> new Order()
                        .setPkId(new BigInteger(rs.getString("pk_id")))
                        .setSkuId(new BigInteger(rs.getString("sku_id")))
                        .setBuyerId(new BigInteger(rs.getString("buyer_id"))));
    }

    @Override
    public long getCount() {
        return jdbcTemplate.queryForObject("select count(1) from `t_order`", Long.class);
    }
}
