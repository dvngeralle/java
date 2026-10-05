package by.bsu.lab4;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest {

    @Test
    public void testMainRunsWithoutErrors() {
        Main.main(new String[0]);
    }

    @Test
    public void testDefaultConstructor() {
        Assert.assertNotNull(new Main());
    }
}
