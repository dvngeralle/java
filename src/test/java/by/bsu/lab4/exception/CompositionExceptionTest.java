package by.bsu.lab4.exception;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CompositionExceptionTest {

    @Test
    public void testMessageAndCause() {
        Throwable cause = new RuntimeException("root");

        CompositionException exception = new CompositionException("failed", cause);

        Assert.assertEquals(exception.getMessage(), "failed");
        Assert.assertSame(exception.getCause(), cause);
    }
}
