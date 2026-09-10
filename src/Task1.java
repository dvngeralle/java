import java.util.Scanner;
/*
 * Найти все p-значные числа из заданной последовательности натуральных чисел,
 * в записи которых не повторяются цифры, и подсчитать их количество.
 */
public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        int[] numbers = new int[n]; 
        System.out.print("Введите последовательность из " + n + " чисел через пробел: ");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        } 

        System.out.print("Введите p: ");
        int p = scanner.nextInt();

        int count = 0;

        for (int i = 0; i < n; i++) {
            int number = numbers[i];
            int original = number;

            int digitCount = 0;
            boolean[] seen = new boolean[10];
            boolean hasRepeats = false;

            if (number == 0) {
                digitCount = 1;
            } else {
                while (number != 0) {
                    int digit = number % 10;

                    if (seen[digit]) {
                        hasRepeats = true;
                    } else {
                        seen[digit] = true;
                    }

                    digitCount++;
                    number = number / 10;
                } 
            }

            if (digitCount == p && !hasRepeats) {
                System.out.println(original);
                count++;
            }
        }

        scanner.close();
        System.out.println("Количество p-значных чисел без повторяющихся цифр: " + count);
    }
}