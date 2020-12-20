package io.github.cchenxi.hmily.demo.order.service;

import java.math.BigDecimal;

/**
 * 交易
 * Date: 2020-12-20
 *
 * @author chenxi
 */
public interface TransService {
    void doTrans(BigDecimal dollarAmount);
}
