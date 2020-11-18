package io.github.cchenxi.w5.h2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

import io.github.cchenxi.w5.h2.bean.BookService;

/**
 * 装配 {@link org.springframework.context.annotation.Bean} 的方式
 * Date: 2020-11-16
 *
 * @author chenxi
 */
public class BeanUsage {
    public static void main(String[] args) {
        // xml
        new BeanUsage().byXml();
        // java代码
        new BeanUsage().byConfiguration();
        // 组件扫描
        new BeanUsage().byComponentScan();
    }

    private void byXml() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/applicationContext.xml");

        Book book = applicationContext.getBean("book", Book.class);
        System.out.println(book);

        // 静态工厂方法
        Book bookByStaticMethod = applicationContext.getBean("book-by-static-method", Book.class);
        System.out.println(bookByStaticMethod);

        // 实例方法
        Book bookByInstanceMethod = applicationContext.getBean("book-by-instance-method", Book.class);
        System.out.println(bookByInstanceMethod);
        Book bookByInstanceMethodn = applicationContext.getBean("book-by-instance-method-n", Book.class);
        System.out.println(bookByInstanceMethodn);
    }

    private void byConfiguration() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(Config.class);
        applicationContext.refresh();

        Book book = applicationContext.getBean(Book.class);
        System.out.println(JSON.toJSONString(book));

        applicationContext.close();
    }

    private void byComponentScan() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/applicationContext.xml");
        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);
    }
}
