/*
19. Найти количество всех седловых точек матрицы. (Матрица А имеет
седловую точку Аi,j, если Аi,j является минимальным элементом в i-й строке и
максимальным в j-м столбце). Отсортировать строки матрицы по количеству
седловых точек в строке.
*/
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class Task19 {

 public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] a = MatrixUtils.createMatrix(scanner);
       int[] columnMax = computeColumnMax(a);
        System.out.println("Исходная матрица:");
        MatrixUtils.printMatrix(a);
        System.out.println("Отсортрованная матрица:");
        sortBySadles(a, columnMax);
        MatrixUtils.printMatrix(a);
        int totalSaddles = 0;
       for (int[] row : a) {
    totalSaddles += countSaddlesInRow(row, columnMax);
}
System.out.println("Количество седловых точек: " + totalSaddles);
    }

private static int[] computeColumnMax(int[][] a){
    int[] columnMax = new int[a[0].length];
    for (int j = 0; j < a[0].length; j++){
        int max = a[0][j];
        for (int i =1; i < a.length; i++){
                if (a[i][j] > max) {
        max = a[i][j];
    }
            
        
        }
        columnMax[j] = max;
    }
    return columnMax;
}
private static int countSaddlesInRow(int[] row, int[] columnMax) {
    int min = row[0];
    for (int j = 1; j < row.length; j++) {
        if (row[j] < min) {
            min = row[j];
        }
    }

    int count = 0;
    for (int j = 0; j < row.length; j++) {
        if (row[j] == min && row[j] == columnMax[j]) {
            count++;
        }
    }
    return count;
}
public static void sortBySadles(int[][] matrix, int[] columnMax) {
    Arrays.sort(matrix, Comparator.comparingInt(row -> countSaddlesInRow(row, columnMax)));
}
}
