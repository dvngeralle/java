package by.bsu.lab4.entity;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InstrumentalPieceTest {
    private InstrumentalPiece piece;

    @BeforeMethod
    public void setUp() {
        piece = new InstrumentalPiece("Autumn Leaves", "Bill Evans", 310, MusicStyle.JAZZ, "piano");
    }

    @Test
    public void testGetters() {
        Assert.assertEquals(piece.getTitle(), "Autumn Leaves");
        Assert.assertEquals(piece.getArtist(), "Bill Evans");
        Assert.assertEquals(piece.getDurationSeconds(), 310);
        Assert.assertEquals(piece.getStyle(), MusicStyle.JAZZ);
        Assert.assertEquals(piece.getMainInstrument(), "piano");
    }

    @Test
    public void testToString() {
        Assert.assertEquals(piece.toString(),
                "InstrumentalPiece[title=Autumn Leaves, artist=Bill Evans, duration=310s, style=JAZZ, mainInstrument=piano]");
    }
}
