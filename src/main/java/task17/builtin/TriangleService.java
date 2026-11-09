package task17.builtin;

import java.util.function.Predicate;

public class TriangleService {

    private final Predicate<Triangle> checker = t -> t.getA() > 0 && t.getB() > 0 && t.getC() > 0
            && t.getA() + t.getB() > t.getC()
            && t.getA() + t.getC() > t.getB()
            && t.getB() + t.getC() > t.getA();

    public boolean canFormTriangle(Triangle t) {
        return checker.test(t);
    }

    public boolean canFormTriangle(double a, double b, double c) {
        return canFormTriangle(new Triangle(a, b, c));
    }
}
