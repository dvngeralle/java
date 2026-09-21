import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class MatrixUtils {

    private static final int RANDOM_BOUND = 21;
    private static final int RANDOM_SHIFT = 10;
    private static final Random random = new Random();

    public static int[][] createMatrix(Scanner scanner) {
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

    public static void printMatrix(int[][] a) {
                for (int[] row : a) {
            System.out.println(Arrays.toString(row));
        }
    }
}