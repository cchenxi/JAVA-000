package io.github.cchenxi.hmily.demo.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Date: 2020-12-19
 *
 * @author chenxi
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final TransService transService;

    @Autowired(required = false)
    public OrderServiceImpl(TransService transService) {
        this.transService = transService;
    }

    @Override
    public String trans(BigDecimal dollarAmount) {
        transService.doTrans(dollarAmount);
        return "success";
    }
}
