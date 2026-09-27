package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testToString() {
        RationalFraction x = new RationalFraction(1, 2);
        RationalFraction y = new RationalFraction(3, 4);
        Point point = new Point(x, y);

        Assert.assertEquals(point.toString(), "(1/2; 3/4)");
    }
}