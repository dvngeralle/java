package by.bsu.lab4.entity;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SongTest {
    private Song song;

    @BeforeMethod
    public void setUp() {
        song = new Song("Yesterday", "The Beatles", 125, MusicStyle.POP, "English");
    }

    @Test
    public void testGetters() {
        Assert.assertEquals(song.getTitle(), "Yesterday");
        Assert.assertEquals(song.getArtist(), "The Beatles");
        Assert.assertEquals(song.getDurationSeconds(), 125);
        Assert.assertEquals(song.getStyle(), MusicStyle.POP);
        Assert.assertEquals(song.getLyricsLanguage(), "English");
    }

    @Test
    public void testToString() {
        Assert.assertEquals(song.toString(),
                "Song[title=Yesterday, artist=The Beatles, duration=125s, style=POP, lyricsLanguage=English]");
    }
}
