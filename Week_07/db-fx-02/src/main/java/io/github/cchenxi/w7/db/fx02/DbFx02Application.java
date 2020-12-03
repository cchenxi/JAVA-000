package io.github.cchenxi.w7.db.fx02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DbFx02Application {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(DbFx02Application.class, args)) {
            String[] arrays = applicationContext.getBeanDefinitionNames();
            System.out.println(1);
        }
    }
}
