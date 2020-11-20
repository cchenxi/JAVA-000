package io.github.cchenxi.db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        userService.clear();
    }

    @Test
    public void test() {
        User user = new User("chenxi", "123456", "1234567890", "cc");
        userService.create(user);

        List<User> users = userService.getAllUsers();
        Assert.assertEquals(users.get(0).getName(), "chenxi");

        userService.update("chenxi", "654321");

        List<User> users1 = userService.getAllUsers();
        Assert.assertEquals(users1.get(0).getPassword(), "654321");
    }
}
