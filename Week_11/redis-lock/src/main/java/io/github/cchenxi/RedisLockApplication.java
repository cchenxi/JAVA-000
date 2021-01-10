package io.github.cchenxi;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.locks.Lock;

@SpringBootApplication
public class RedisLockApplication implements CommandLineRunner {

    @Autowired
    private RedissonClient redissonClient;

    public static void main(String[] args) {
        SpringApplication.run(RedisLockApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Lock lock = redissonClient.getLock("lock");
        lock.lock();


    }
}
