package task5.builtin;

public class Main {
    public static void main(String[] args) {
        FibonacciService service = new FibonacciService();

        long[] testNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 14, 21, 34, 55, 100};
        for (long n : testNumbers) {
            System.out.println(n + " -> " + service.isFibonacci(n));
        }
    }
}
