package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GeometryUtilsTest {

    @Test
    public void testIsCollinearTrue() {
        // Точки (1,1), (2,2), (3,3) лежат на одной прямой y = x
        Point p1 = new Point(new RationalFraction(1, 1), new RationalFraction(1, 1));
        Point p2 = new Point(new RationalFraction(2, 1), new RationalFraction(2, 1));
        Point p3 = new Point(new RationalFraction(3, 1), new RationalFraction(3, 1));

        Assert.assertTrue(GeometryUtils.isCollinear(p1, p2, p3));
    }

    @Test
    public void testIsCollinearFalse() {
        // Точки (0,0), (0,1), (1,0) образуют треугольник, не лежат на одной прямой
        Point p1 = new Point(new RationalFraction(0, 1), new RationalFraction(0, 1));
        Point p2 = new Point(new RationalFraction(0, 1), new RationalFraction(1, 1));
        Point p3 = new Point(new RationalFraction(1, 1), new RationalFraction(0, 1));

        Assert.assertFalse(GeometryUtils.isCollinear(p1, p2, p3));
    }

    @Test
    public void testFindMinAndMaxAreaCircle() {
        Point center = new Point(new RationalFraction(0, 1), new RationalFraction(0, 1));

        Circle c1 = new Circle(1.0, center); // Площадь ~3.14
        Circle c2 = new Circle(5.0, center); // Площадь ~78.5
        Circle c3 = new Circle(3.0, center); // Площадь ~28.2

        Circle[] circles = new Circle[]{c1, c2, c3};

        Circle minCircle = GeometryUtils.findMinAreaCircle(circles);
        Circle maxCircle = GeometryUtils.findMaxAreaCircle(circles);

        Assert.assertEquals(minCircle, c1);
        Assert.assertEquals(maxCircle, c2);
    }
}