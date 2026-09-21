import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Task5Stream {

    private static final int RANDOM_BOUND = 21;
    private static final int RANDOM_SHIFT = 10;
    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] a = createMatrix(scanner);

        System.out.println("Исходная матрица:");
        printMatrix(a);

        int[][] jagged = buildJaggedMatrix(a);
        jagged = sortRowsByNegativeCount(jagged);

        System.out.println("Зубчатая матрица (отсортирована по количеству отрицательных элементов):");
        printMatrix(jagged);
    }

    private static int[][] createMatrix(Scanner scanner) {
        System.out.print("Введите количество строк m: ");
        int m = scanner.nextInt();
        System.out.print("Введите количество столбцов n: ");
        int n = scanner.nextInt();
        int[][] a = new int[m][n];

        System.out.print("Способ заполнения (1 - случайно, 0 - вручную): ");
        int mode = scanner.nextInt();

        for (int[] row : a) {
            for (int j = 0; j < row.length; j++) {
                row[j] = mode == 1 ? random.nextInt(RANDOM_BOUND) - RANDOM_SHIFT : scanner.nextInt();
            }
        }

        return a;
    }

    private static int[][] buildJaggedMatrix(int[][] a) {
        return Arrays.stream(a)
                .map(row -> Arrays.stream(row).distinct().toArray())
                .toArray(int[][]::new);
    }

    private static int[][] sortRowsByNegativeCount(int[][] matrix) {
        return Arrays.stream(matrix)
                .sorted(Comparator.comparingLong(Task5Stream::countNegatives))
                .toArray(int[][]::new);
    }

    private static long countNegatives(int[] row) {
        return Arrays.stream(row).filter(value -> value < 0).count();
    }

    private static void printMatrix(int[][] a) {
        for (int[] row : a) {
            System.out.println(Arrays.toString(row));
        }
    }
}
