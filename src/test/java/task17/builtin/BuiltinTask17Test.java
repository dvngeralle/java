package task17.builtin;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BuiltinTask17Test {

    @Test
    public void testTrianglePossibility() {
        TriangleService service = new TriangleService();

        assertTrue(service.canFormTriangle(new Triangle(3, 4, 5)));
        assertTrue(service.canFormTriangle(new Triangle(5, 5, 5)));
        assertTrue(service.canFormTriangle(new Triangle(7, 10, 5)));

        assertFalse(service.canFormTriangle(new Triangle(1, 2, 3)));
        assertFalse(service.canFormTriangle(new Triangle(10, 2, 2)));
        assertFalse(service.canFormTriangle(new Triangle(-1, 4, 4)));
        assertFalse(service.canFormTriangle(new Triangle(0, 5, 5)));
    }
}
