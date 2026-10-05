package by.bsu.lab4.entity;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MusicStyleTest {

    @Test
    public void testValueOf() {
        Assert.assertEquals(MusicStyle.valueOf("JAZZ"), MusicStyle.JAZZ);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testValueOfUnknownThrowsException() {
        MusicStyle.valueOf("DISCO");
    }

    @Test
    public void testDeclarationOrder() {
        Assert.assertEquals(MusicStyle.values().length, 5);
        Assert.assertTrue(MusicStyle.POP.compareTo(MusicStyle.ROCK) < 0);
    }
}
