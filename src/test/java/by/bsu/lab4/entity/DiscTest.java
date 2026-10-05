package by.bsu.lab4.entity;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DiscTest {
    private Disc disc;

    @BeforeMethod
    public void setUp() {
        disc = new Disc("My disc");
    }

    @Test
    public void testNewDiscIsEmpty() {
        Assert.assertEquals(disc.getName(), "My disc");
        Assert.assertTrue(disc.getCompositions().isEmpty());
    }

    @Test
    public void testAddComposition() {
        Song song = new Song("Yesterday", "The Beatles", 125, MusicStyle.POP, "English");
        disc.addComposition(song);

        Assert.assertEquals(disc.getCompositions().size(), 1);
        Assert.assertSame(disc.getCompositions().get(0), song);
    }

    @Test
    public void testToString() {
        Song song = new Song("Yesterday", "The Beatles", 125, MusicStyle.POP, "English");
        disc.addComposition(song);

        Assert.assertEquals(disc.toString(), "Disc[name=My disc, compositions=[" + song + "]]");
    }
}
