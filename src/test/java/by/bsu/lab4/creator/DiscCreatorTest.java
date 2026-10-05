package by.bsu.lab4.creator;

import by.bsu.lab4.entity.ClassicalWork;
import by.bsu.lab4.entity.Disc;
import by.bsu.lab4.entity.InstrumentalPiece;
import by.bsu.lab4.entity.Song;
import by.bsu.lab4.exception.CompositionException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DiscCreatorTest {
    private final DiscCreator creator = new DiscCreator();
    private Path file;

    @BeforeMethod
    public void setUp() throws IOException {
        file = Files.createTempFile("creator-test", ".txt");
    }

    @AfterMethod
    public void tearDown() throws IOException {
        Files.deleteIfExists(file);
    }

    @Test
    public void testCreateSkipsInvalidLines() throws Exception {
        Files.write(file, List.of(
                "SONG;Yesterday;The Beatles;125;POP;English",
                "SONG;Broken;Nobody;-5;ROCK;English",
                "JUNK;???;;;;",
                "INSTRUMENTAL;Autumn Leaves;Bill Evans;310;JAZZ;piano",
                "CLASSICAL;Unfinished;Someone;300;CLASSICAL;Schubert",
                "CLASSICAL;Moonlight Sonata;Daniel Barenboim;360;CLASSICAL;Beethoven;27"));

        Disc disc = creator.create("Test disc", file.toString());

        Assert.assertEquals(disc.getName(), "Test disc");
        Assert.assertEquals(disc.getCompositions().size(), 3);
        Assert.assertTrue(disc.getCompositions().get(0) instanceof Song);
        Assert.assertTrue(disc.getCompositions().get(1) instanceof InstrumentalPiece);
        Assert.assertTrue(disc.getCompositions().get(2) instanceof ClassicalWork);
    }

    @Test
    public void testCreateFromEmptyFile() throws Exception {
        Disc disc = creator.create("Empty", file.toString());

        Assert.assertTrue(disc.getCompositions().isEmpty());
    }

    @Test(expectedExceptions = CompositionException.class)
    public void testMissingFileThrowsException() throws Exception {
        creator.create("Broken", "no-such-folder/no-such-file.txt");
    }
}
