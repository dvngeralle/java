package task17.custom;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CustomTask17Test {

    @Test
    public void testTrianglePossibility() {
        TriangleService service = new TriangleService();

        assertTrue(service.canFormTriangle(3, 4, 5));
        assertTrue(service.canFormTriangle(5, 5, 5));
        assertTrue(service.canFormTriangle(7, 10, 5));

        assertFalse(service.canFormTriangle(1, 2, 3));
        assertFalse(service.canFormTriangle(10, 2, 2));
        assertFalse(service.canFormTriangle(-1, 4, 4));
        assertFalse(service.canFormTriangle(0, 5, 5));
    }
}
