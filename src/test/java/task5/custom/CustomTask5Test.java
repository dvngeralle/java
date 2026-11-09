package task5.custom;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CustomTask5Test {

    @Test
    public void testFibonacciNumbers() {
        FibonacciService service = new FibonacciService();

        assertTrue(service.isFibonacci(0));
        assertTrue(service.isFibonacci(1));
        assertTrue(service.isFibonacci(2));
        assertTrue(service.isFibonacci(3));
        assertTrue(service.isFibonacci(5));
        assertTrue(service.isFibonacci(8));
        assertTrue(service.isFibonacci(13));
        assertTrue(service.isFibonacci(21));
        assertTrue(service.isFibonacci(34));
        assertTrue(service.isFibonacci(55));

        assertFalse(service.isFibonacci(-1));
        assertFalse(service.isFibonacci(4));
        assertFalse(service.isFibonacci(6));
        assertFalse(service.isFibonacci(7));
        assertFalse(service.isFibonacci(9));
        assertFalse(service.isFibonacci(14));
        assertFalse(service.isFibonacci(100));
    }
}
