package org.example;

import org.testng.annotations.Test;

public class MainTest {
    @Test
    public void mainShouldRunWithoutExceptions() {
        Main.main(new String[0]);
    }
}