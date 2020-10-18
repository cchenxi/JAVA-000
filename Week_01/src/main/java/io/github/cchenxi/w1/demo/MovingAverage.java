package io.github.cchenxi.w1.demo;

/**
 * 移动平均数
 * Date: 2020-10-18
 *
 * @author chenxi
 */
public class MovingAverage {
    private int count;
    private double sum = 0.0D;

    public void submit(double value) {
        this.count++;
        this.sum += value;
    }

    public double getAvg() {
        if (0 == this.count) {
            return sum;
        }
        return this.sum / this.count;
    }
}
