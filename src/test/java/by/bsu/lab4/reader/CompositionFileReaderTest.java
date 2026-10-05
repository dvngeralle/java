package by.bsu.lab4.reader;

import by.bsu.lab4.exception.CompositionException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CompositionFileReaderTest {
    private final CompositionFileReader reader = new CompositionFileReader();
    private Path file;

    @BeforeMethod
    public void setUp() throws IOException {
        file = Files.createTempFile("reader-test", ".txt");
    }

    @AfterMethod
    public void tearDown() throws IOException {
        Files.deleteIfExists(file);
    }

    @Test
    public void testReadLines() throws Exception {
        Files.write(file, List.of("first line", "second line"));

        List<String> lines = reader.readLines(file.toString());

        Assert.assertEquals(lines, List.of("first line", "second line"));
    }

    @Test
    public void testReadEmptyFile() throws Exception {
        Assert.assertTrue(reader.readLines(file.toString()).isEmpty());
    }

    @Test(expectedExceptions = CompositionException.class)
    public void testMissingFileThrowsException() throws Exception {
        reader.readLines("no-such-folder/no-such-file.txt");
    }
}
