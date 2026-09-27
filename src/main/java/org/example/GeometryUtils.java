package org.example;

import java.util.ArrayList;
import java.util.List;

public class GeometryUtils {

    public static boolean isCollinear(Point p1, Point p2, Point p3) {
        double x1 = p1.getX().toDouble();
        double y1 = p1.getY().toDouble();
        double x2 = p2.getX().toDouble();
        double y2 = p2.getY().toDouble();
        double x3 = p3.getX().toDouble();
        double y3 = p3.getY().toDouble();

        double val = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        return Math.abs(val) < 1e-9;
    }

    public static Circle findMinAreaCircle(Circle[] circles) {
        if (circles == null || circles.length == 0) return null;
        Circle minCircle = circles[0];
        for (Circle c : circles) {
            if (c.getArea() < minCircle.getArea()) {
                minCircle = c;
            }
        }
        return minCircle;
    }
    public static Circle findMaxAreaCircle(Circle[] circles) {
        if (circles == null || circles.length == 0) return null;
        Circle maxCircle = circles[0];
        for (Circle c : circles) {
            if (c.getArea() > maxCircle.getArea()) {
                maxCircle = c;
            }
        }
        return maxCircle;
    }
}