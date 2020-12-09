package io.github.cchenxi.shardingdemo;

import io.github.cchenxi.shardingdemo.model.Order;
import io.github.cchenxi.shardingdemo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ShardingDemoApplicationTests {
    @Autowired
    private OrderService orderService;

    @Test
    public void testC() throws Exception {
        log.info("before count: {}", orderService.getCount());
        Order order = new Order().setBuyerId(new BigInteger("99")).setSkuId(new BigInteger("100"));
        orderService.addOrder(order);
        log.info("after count: {}", orderService.getCount());
    }

    @Test
    public void testU() throws Exception {
        Order order = new Order().setPkId(new BigInteger("543454992037031938")).setBuyerId(new BigInteger("3")).setSkuId(new BigInteger("5"));
        orderService.updateOrder(order);
        Order order2 = orderService.getOrder(new BigInteger("543454992037031938"), new BigInteger("3"));
        System.out.println(order2);
    }

    @Test
    public void testR1() throws Exception {
        Order order = orderService.getOrder(new BigInteger("543202504977395713"), new BigInteger("2"));
        System.out.println(order);
    }

    @Test
    public void testR2() throws Exception {
        List<Order> orderList = orderService.getOrderList();
        System.out.println(orderList);
    }

    @Test
    public void testD() throws Exception {
        log.info("before count: {}", orderService.getCount());
        orderService.deleteOrder(new BigInteger("543444432826380290"), new BigInteger("3"));
        log.info("after count: {}", orderService.getCount());
    }
}
