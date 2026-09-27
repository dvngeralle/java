package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RationalFractionTest {

    @Test
    public void testToDouble() {
        RationalFraction fraction = new RationalFraction(1, 2);
        // Третий аргумент 1e-9 — это допустимая погрешность сравнения double
        Assert.assertEquals(fraction.toDouble(), 0.5, 1e-9);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testZeroDenominatorThrowsException() {
        // Должно выбросить исключение при знаменателе 0
        new RationalFraction(5, 0);
    }

    @Test
    public void testToString() {
        RationalFraction fraction = new RationalFraction(3, 4);
        Assert.assertEquals(fraction.toString(), "3/4");
    }
}