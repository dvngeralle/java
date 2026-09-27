package org.example;

public class RationalFraction {
    private int numerator;
    private int denominator;
public RationalFraction(int numerator, int denominator) {
    if (denominator == 0){
        throw new IllegalArgumentException("Знаменатель не может быть нулем!");
    }
    this.numerator = numerator;
    this.denominator = denominator;
}
    public double toDouble() {
        return (double) numerator / denominator;
    }
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
