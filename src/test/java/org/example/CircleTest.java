package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CircleTest {

    @Test
    public void testGetArea() {
        Point center = new Point(new RationalFraction(0, 1), new RationalFraction(0, 1));
        Circle circle = new Circle(2.0, center);

        double expectedArea = Math.PI * 4.0;
        Assert.assertEquals(circle.getArea(), expectedArea, 1e-9);
    }

    @Test
    public void testGetPerimeter() {
        Point center = new Point(new RationalFraction(0, 1), new RationalFraction(0, 1));
        Circle circle = new Circle(3.0, center);

        double expectedPerimeter = 2 * Math.PI * 3.0;
        Assert.assertEquals(circle.getPerimeter(), expectedPerimeter, 1e-9);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testZeroRadiusThrowsException() {
        Point center = new Point(new RationalFraction(0, 1), new RationalFraction(0, 1));
        new Circle(0, center);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeRadiusThrowsException() {
        Point center = new Point(new RationalFraction(0, 1), new RationalFraction(0, 1));
        new Circle(-5.0, center);
    }
}