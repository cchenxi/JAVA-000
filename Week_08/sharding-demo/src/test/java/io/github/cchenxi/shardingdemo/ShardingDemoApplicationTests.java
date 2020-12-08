package io.github.cchenxi.shardingdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingDemoApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        jdbcTemplate.execute("insert into `t_order` (`buyer_id`, `sku_id`) values (1, 1), (2, 2), (3, 3)");
    }
}
