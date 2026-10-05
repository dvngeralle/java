package by.bsu.lab4.factory;

import by.bsu.lab4.entity.InstrumentalPiece;
import by.bsu.lab4.entity.MusicComposition;
import by.bsu.lab4.entity.MusicStyle;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InstrumentalPieceFactoryTest {

    @Test
    public void testCreate() {
        String[] fields = "INSTRUMENTAL;Autumn Leaves;Bill Evans;310;JAZZ;piano".split(";", -1);

        MusicComposition result = new InstrumentalPieceFactory().create(fields);

        Assert.assertTrue(result instanceof InstrumentalPiece);
        InstrumentalPiece piece = (InstrumentalPiece) result;
        Assert.assertEquals(piece.getTitle(), "Autumn Leaves");
        Assert.assertEquals(piece.getArtist(), "Bill Evans");
        Assert.assertEquals(piece.getDurationSeconds(), 310);
        Assert.assertEquals(piece.getStyle(), MusicStyle.JAZZ);
        Assert.assertEquals(piece.getMainInstrument(), "piano");
    }
}
