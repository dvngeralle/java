package task17.builtin;

public class Main {
    public static void main(String[] args) {
        TriangleService service = new TriangleService();

        Triangle[] testCases = {
                new Triangle(3, 4, 5),
                new Triangle(5, 5, 5),
                new Triangle(7, 10, 5),
                new Triangle(1, 2, 3),
                new Triangle(10, 2, 2),
                new Triangle(-1, 4, 4),
                new Triangle(0, 5, 5)
        };

        for (Triangle t : testCases) {
            System.out.println(t + " -> " + service.canFormTriangle(t));
        }
    }
}
