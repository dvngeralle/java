package by.bsu.lab4.service;

import by.bsu.lab4.entity.ClassicalWork;
import by.bsu.lab4.entity.Disc;
import by.bsu.lab4.entity.InstrumentalPiece;
import by.bsu.lab4.entity.MusicComposition;
import by.bsu.lab4.entity.MusicStyle;
import by.bsu.lab4.entity.Song;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DiscServiceTest {
    private DiscService service;
    private Disc disc;

    @BeforeMethod
    public void setUp() {
        service = new DiscService();
        disc = new Disc("Test");
        disc.addComposition(new Song("A", "X", 100, MusicStyle.ROCK, "English"));
        disc.addComposition(new Song("B", "X", 200, MusicStyle.POP, "English"));
        disc.addComposition(new InstrumentalPiece("C", "X", 300, MusicStyle.ELECTRONIC, "synthesizer"));
        disc.addComposition(new ClassicalWork("D", "X", 400, MusicStyle.CLASSICAL, "Bach", 1));
        disc.addComposition(new InstrumentalPiece("E", "X", 500, MusicStyle.JAZZ, "piano"));
    }

    @Test
    public void testCalculateTotalDuration() {
        Assert.assertEquals(service.calculateTotalDuration(disc), 1500);
    }

    @Test
    public void testCalculateTotalDurationOfEmptyDisc() {
        Assert.assertEquals(service.calculateTotalDuration(new Disc("Empty")), 0);
    }

    @Test
    public void testSortByStyle() {
        service.sortByStyle(disc);

        List<MusicComposition> sorted = disc.getCompositions();
        String[] expectedTitles = {"B", "A", "E", "D", "C"};
        for (int i = 0; i < expectedTitles.length; i++) {
            Assert.assertEquals(sorted.get(i).getTitle(), expectedTitles[i]);
        }
    }

    @Test
    public void testFindByDurationIncludesBounds() {
        List<MusicComposition> found = service.findByDuration(disc, 200, 400);

        Assert.assertEquals(found.size(), 3);
        Assert.assertEquals(found.get(0).getTitle(), "B");
        Assert.assertEquals(found.get(1).getTitle(), "C");
        Assert.assertEquals(found.get(2).getTitle(), "D");
    }

    @Test
    public void testFindByDurationNothingFound() {
        Assert.assertTrue(service.findByDuration(disc, 600, 700).isEmpty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByDurationInvalidRangeThrowsException() {
        service.findByDuration(disc, 400, 200);
    }
}
