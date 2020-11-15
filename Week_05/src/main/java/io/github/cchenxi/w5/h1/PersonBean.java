package io.github.cchenxi.w5.h1;

/**
 * Date: 2020-11-15
 *
 * @author chenxi
 */
public class PersonBean implements Person {
    @Override
    public void eating() {
        System.out.println("eating...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
