import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Task5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] a = MatrixUtils.createMatrix(scanner);

        System.out.println("Исходная матрица:");
        MatrixUtils.printMatrix(a);

        int[][] jagged = buildJaggedMatrix(a);
        sortRowsByNegativeCount(jagged);

        System.out.println("Зубчатая матрица (отсортирована по количеству отрицательных элементов):");
        MatrixUtils.printMatrix(jagged);
    }


    private static int[][] buildJaggedMatrix(int[][] a) {
        int[][] result = new int[a.length][];
        for (int i = 0; i < a.length; i++) {
            int[] temp = new int[a[i].length];
            int count = 0;
            for (int value : a[i]) {
                if (indexOf(temp, count, value) < 0) {
                    temp[count] = value;
                    count++;
                }
            }
            result[i] = Arrays.copyOf(temp, count);
        }
        return result;
    }

    private static int indexOf(int[] array, int length, int value) {
        for (int i = 0; i < length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private static void sortRowsByNegativeCount(int[][] matrix) {
        Arrays.sort(matrix, Comparator.comparingInt(Task5::countNegatives));
    }

    private static int countNegatives(int[] row) {
        int count = 0;
        for (int value : row) {
            if (value < 0) {
                count++;
            }
        }
        return count;
    }

}