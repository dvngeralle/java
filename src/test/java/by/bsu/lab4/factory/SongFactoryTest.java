package by.bsu.lab4.factory;

import by.bsu.lab4.entity.MusicComposition;
import by.bsu.lab4.entity.MusicStyle;
import by.bsu.lab4.entity.Song;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SongFactoryTest {

    @Test
    public void testCreate() {
        String[] fields = "SONG;Yesterday;The Beatles;125;POP;English".split(";", -1);

        MusicComposition result = new SongFactory().create(fields);

        Assert.assertTrue(result instanceof Song);
        Song song = (Song) result;
        Assert.assertEquals(song.getTitle(), "Yesterday");
        Assert.assertEquals(song.getArtist(), "The Beatles");
        Assert.assertEquals(song.getDurationSeconds(), 125);
        Assert.assertEquals(song.getStyle(), MusicStyle.POP);
        Assert.assertEquals(song.getLyricsLanguage(), "English");
    }
}
