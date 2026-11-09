package task5.builtin;

import java.util.function.LongPredicate;

public class FibonacciService {

    private final LongPredicate checker = n -> {
        if (n < 0) return false;
        long a = 0, b = 1;
        while (a < n) {
            long next = a + b;
            a = b;
            b = next;
        }
        return a == n;
    };

    public boolean isFibonacci(long n) {
        return checker.test(n);
    }
}
