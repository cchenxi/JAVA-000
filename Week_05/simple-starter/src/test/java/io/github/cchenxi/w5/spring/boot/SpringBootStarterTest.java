package io.github.cchenxi.w5.spring.boot;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.github.cchenxi.w5.spring.boot.obj.Book;
import io.github.cchenxi.w5.spring.boot.obj.Klass;
import io.github.cchenxi.w5.spring.boot.obj.School;
import io.github.cchenxi.w5.spring.boot.obj.Student;

/**
 * UT
 * Date: 2020-11-18
 *
 * @author chenxi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStarterTest.class)
@SpringBootApplication
@ActiveProfiles("common")
public class SpringBootStarterTest {

    @Resource
    private School school;

    @Resource
    private Klass klass;

    @Test
    public void test() {
        school.ding();
        klass.dong();
    }
}
