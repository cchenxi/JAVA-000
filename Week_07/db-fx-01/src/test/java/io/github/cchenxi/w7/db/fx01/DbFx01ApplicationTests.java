package io.github.cchenxi.w7.db.fx01;

import io.github.cchenxi.w7.db.fx01.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbFx01ApplicationTests {
    @Autowired
    @Qualifier("cs1")
    private CustomerService cs1;

    @Test
    public void test() {
        cs1.addCustomer();
        cs1.getAllCustomers();
    }

    @Autowired
    @Qualifier("cs2")
    private CustomerService cs2;

    @Test
    public void test2() throws Exception {
        cs2.addCustomer();
        cs2.getAllCustomers();
    }
}
