package io.github.cchenxi.w5.h2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Date: 2020-11-16
 *
 * @author chenxi
 */
@Configuration
public class Config {

    @Bean
    public Book book() {
        Book book = new Book();
        book.setId(2);
        book.setName("《深入理解Java虚拟机》");
        book.setAuthor("周志明");
        return book;
    }
}
