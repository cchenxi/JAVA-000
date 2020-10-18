package io.github.cchenxi.w1.demo;

/**
 * {@link LocalVariableTest}
 * Date: 2020-10-18
 *
 * @author chenxi
 */
public class LocalVariableTest {
    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        int num1 = 1;
        int num2 = 2;
        ma.submit(num1);
        ma.submit(num2);
        double avg = ma.getAvg();
    }
}
