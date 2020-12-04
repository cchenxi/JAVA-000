package io.github.cchenxi.w7.db.fx02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DbFx02Application {

    public static void main(String[] args) {
        SpringApplication.run(DbFx02Application.class, args);
    }
}
