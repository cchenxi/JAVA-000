package io.github.cchenxi.hmily.demo.order.service;

import java.math.BigDecimal;

/**
 * Date: 2020-12-19
 *
 * @author chenxi
 */
public interface OrderService {
    String trans(BigDecimal dollarAmount);
}
