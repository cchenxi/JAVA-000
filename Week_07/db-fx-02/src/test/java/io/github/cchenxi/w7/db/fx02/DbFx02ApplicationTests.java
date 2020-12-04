package io.github.cchenxi.w7.db.fx02;

import io.github.cchenxi.w7.db.fx02.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DbFx02ApplicationTests {
    @Autowired
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        // 清空表
        customerService.clear();
    }

    @Test
    public void test() throws Exception {
        // 测试添加一条记录
        customerService.addCustomer();
        log.info("count = {}", customerService.getCustomersCount());

        // 测试添加一条记录后抛出异常 触发事务回滚
        try {
            customerService.addCustomerRollback();
        } catch (Exception e) {
            log.info("count = {}", customerService.getCustomersCount());
        }
    }
}
