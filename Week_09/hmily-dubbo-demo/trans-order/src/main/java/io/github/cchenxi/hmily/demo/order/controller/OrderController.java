package io.github.cchenxi.hmily.demo.order.controller;

import io.github.cchenxi.hmily.demo.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 兑换入口
 * Date: 2020-12-19
 *
 * @author chenxi
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/trans")
    public String trans(@RequestParam(value = "dollarAmount") BigDecimal dollarAmount) {
        orderService.trans(dollarAmount);
        return "success";
    }
}
