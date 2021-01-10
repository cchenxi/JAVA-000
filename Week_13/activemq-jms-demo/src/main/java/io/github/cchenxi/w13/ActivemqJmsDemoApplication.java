package io.github.cchenxi.w13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ActivemqJmsDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivemqJmsDemoApplication.class, args);
    }
}
