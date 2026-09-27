package org.example;

public class Point {
    private RationalFraction x;
    private RationalFraction y;
public Point(RationalFraction x, RationalFraction y){
    this.x = x;
    this.y = y;
}
    public RationalFraction getX() {
        return x;
    }
    public RationalFraction getY() {
        return y;
    }
    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
}
