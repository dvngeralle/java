import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Task19Stream {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] a = MatrixUtils.createMatrix(scanner);
        int[] columnMax = computeColumnMax(a);

        System.out.println("Исходная матрица:");
        MatrixUtils.printMatrix(a);

        int totalSaddles = Arrays.stream(a)
                .mapToInt(row -> countSaddlesInRow(row, columnMax))
                .sum();
        System.out.println("Количество седловых точек: " + totalSaddles);

        int[][] sorted = sortBySaddles(a, columnMax);
        System.out.println("Отсортированная матрица:");
        MatrixUtils.printMatrix(sorted);
    }

    private static int[] computeColumnMax(int[][] a) {
        int n = a[0].length;
        return IntStream.range(0, n)
                .map(j -> Arrays.stream(a).mapToInt(row -> row[j]).max().orElseThrow())
                .toArray();
    }

    private static int countSaddlesInRow(int[] row, int[] columnMax) {
        int min = Arrays.stream(row).min().orElseThrow();
        return (int) IntStream.range(0, row.length)
                .filter(j -> row[j] == min && row[j] == columnMax[j])
                .count();
    }

    private static int[][] sortBySaddles(int[][] matrix, int[] columnMax) {
        return Arrays.stream(matrix)
                .sorted(Comparator.comparingInt(row -> countSaddlesInRow(row, columnMax)))
                .toArray(int[][]::new);
    }
}
