package io.github.cchenxi.w5.h1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Date: 2020-11-15
 *
 * @author chenxi
 */
public class DynamicProxyAopMain {
    public static void main(String[] args) {
        Person person = new PersonBean();

        Person proxy = (Person) Proxy.newProxyInstance(
                Person.class.getClassLoader(),
                person.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before...");
                        long startTime = System.currentTimeMillis();

                        Object result = method.invoke(person, args);

                        // after
                        long cost = System.currentTimeMillis() - startTime;
                        System.out.println("after...，cost:" + cost);

                        return result;
                    }
                }
        );

        proxy.eating();
    }
}
