package task17.custom;

public class TriangleService {

    private final TriangleChecker checker = (a, b, c) -> a > 0 && b > 0 && c > 0
            && a + b > c
            && a + c > b
            && b + c > a;

    public boolean canFormTriangle(double a, double b, double c) {
        return checker.canFormTriangle(a, b, c);
    }
}
