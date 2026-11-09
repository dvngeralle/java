package task17.custom;

public class Main {
    public static void main(String[] args) {
        TriangleService service = new TriangleService();

        double[][] testCases = {
                {3, 4, 5},
                {5, 5, 5},
                {7, 10, 5},
                {1, 2, 3},
                {10, 2, 2},
                {-1, 4, 4},
                {0, 5, 5}
        };

        for (double[] sides : testCases) {
            double a = sides[0], b = sides[1], c = sides[2];
            System.out.println("a=" + a + ", b=" + b + ", c=" + c + " -> " + service.canFormTriangle(a, b, c));
        }
    }
}
