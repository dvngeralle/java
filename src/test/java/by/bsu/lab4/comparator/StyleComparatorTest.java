package by.bsu.lab4.comparator;

import by.bsu.lab4.entity.MusicComposition;
import by.bsu.lab4.entity.MusicStyle;
import by.bsu.lab4.entity.Song;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class StyleComparatorTest {
    private final StyleComparator comparator = new StyleComparator();

    private Song song(String title, MusicStyle style) {
        return new Song(title, "Artist", 100, style, "English");
    }

    @Test
    public void testCompareLess() {
        Assert.assertTrue(comparator.compare(song("a", MusicStyle.POP), song("b", MusicStyle.ROCK)) < 0);
    }

    @Test
    public void testCompareGreater() {
        Assert.assertTrue(comparator.compare(song("a", MusicStyle.ROCK), song("b", MusicStyle.POP)) > 0);
    }

    @Test
    public void testCompareEqual() {
        Assert.assertEquals(comparator.compare(song("a", MusicStyle.JAZZ), song("b", MusicStyle.JAZZ)), 0);
    }

    @Test
    public void testSortList() {
        List<MusicComposition> list = new ArrayList<>();
        list.add(song("rock", MusicStyle.ROCK));
        list.add(song("pop", MusicStyle.POP));
        list.sort(comparator);

        Assert.assertEquals(list.get(0).getTitle(), "pop");
        Assert.assertEquals(list.get(1).getTitle(), "rock");
    }
}
