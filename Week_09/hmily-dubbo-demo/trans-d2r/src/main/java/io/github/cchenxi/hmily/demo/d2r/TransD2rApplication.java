package io.github.cchenxi.hmily.demo.d2r;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Date: 2020-12-13
 *
 * @author chenxi
 */
@SpringBootApplication
@MapperScan("io.github.cchenxi.hmily.demo.common.d2r.mapper")
@EnableDubbo(scanBasePackages = "io.github.cchenxi.hmily.demo.d2r.service")
public class TransD2rApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication();
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        SpringApplication.run(TransD2rApplication.class, args);
    }
}
