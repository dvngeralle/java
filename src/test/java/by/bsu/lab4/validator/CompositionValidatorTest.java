package by.bsu.lab4.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CompositionValidatorTest {
    private CompositionValidator validator;

    @BeforeClass
    public void setUp() {
        validator = new CompositionValidator();
    }

    @DataProvider(name = "validLines")
    public Object[][] validLines() {
        return new Object[][]{
                {"SONG;Yesterday;The Beatles;125;POP;English"},
                {"INSTRUMENTAL;Autumn Leaves;Bill Evans;310;JAZZ;piano"},
                {"CLASSICAL;Moonlight Sonata;Daniel Barenboim;360;CLASSICAL;Beethoven;27"},
                {"SONG; Title ; Artist ; 125 ; POP ; English "}
        };
    }

    @DataProvider(name = "invalidLines")
    public Object[][] invalidLines() {
        return new Object[][]{
                {"JUNK;???;;;;"},
                {"SONG;A;B;200;POP"},
                {"CLASSICAL;A;B;300;CLASSICAL;Schubert"},
                {"SONG;;B;200;POP;English"},
                {"SONG;A;;200;POP;English"},
                {"SONG;A;B;200;POP;"},
                {"SONG;A;B;-5;ROCK;English"},
                {"SONG;A;B;0;ROCK;English"},
                {"SONG;A;B;abc;ROCK;English"},
                {"SONG;A;B;200;DISCO;English"},
                {"CLASSICAL;A;B;300;CLASSICAL;Schubert;abc"},
                {"CLASSICAL;A;B;300;CLASSICAL;Schubert;0"}
        };
    }

    @Test(dataProvider = "validLines")
    public void testValidLines(String line) {
        Assert.assertTrue(validator.isValid(line.split(";", -1)), line);
    }

    @Test(dataProvider = "invalidLines")
    public void testInvalidLines(String line) {
        Assert.assertFalse(validator.isValid(line.split(";", -1)), line);
    }
}
