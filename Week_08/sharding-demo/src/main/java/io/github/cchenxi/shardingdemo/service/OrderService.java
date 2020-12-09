package io.github.cchenxi.shardingdemo.service;

import io.github.cchenxi.shardingdemo.model.Order;

import java.math.BigInteger;
import java.util.List;

/**
 * Date: 2020-12-09
 *
 * @author chenxi
 */
public interface OrderService {
    /**
     * 生成订单
     *
     * @param order 订单
     */
    void addOrder(final Order order);

    /**
     * 删除订单
     *
     * @param orderId 订单id
     * @param buyerId 买家id
     */
    void deleteOrder(final BigInteger orderId, final BigInteger buyerId);

    /**
     * 更新订单
     *
     * @param order 订单
     */
    void updateOrder(final Order order);

    /**
     * 查询订单
     *
     * @return
     */
    Order getOrder(final BigInteger orderId, final BigInteger buyerId);

    /**
     * 查询订单
     *
     * @return
     */
    List<Order> getOrderList();

    /**
     *
     * @return
     */
    long getCount();
}
