    import java.util.Scanner;
    /*
    Для каждого числа из заданной последовательности натуральных чисел
определить, сколько в нем цифр. Если количество цифр чётное, то получить из него такое
число, чтобы цифры в нём следовали в обратном порядке. Если количество цифр
нечётное, то получить из исходного числа такое число, в котором все цифры 1 заменены
на 2, а цифры 0 — на 7.
    */
    public class Task3 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите n: ");
            int n = scanner.nextInt();
            int[] numbers = new int[n];
            System.out.print("Введите последовательность из "+n+" чисел через пробел: " );
            for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
            }
for(int i=0; i < n; i++){
    int digitCount = 0;

int number = numbers[i];
int original = number;
  while (number != 0) {
        number = number/10;
        digitCount = digitCount+1;
  }
number = original;

if (digitCount % 2 == 0) {
     int reversed = 0;
        while (number!=0) {
            int digit = number % 10;
            number = number/10;
            reversed = reversed * 10 + digit;
        }
        System.out.println(reversed);
       number = original;
} else {
    int result = 0;
    while (number != 0) {
        int digit = number % 10;
        if (digit == 1) {
            digit = 2;
        } else if (digit == 0) {
            digit = 7;
        }
        result = result * 10 + digit;
        number = number / 10;
    }
    

    int finalResult = 0;
    while (result != 0) {
        int digit = result % 10;
        result = result / 10;
        finalResult = finalResult * 10 + digit;
    }

    System.out.println(finalResult);
}



}
scanner.close();
        }
    }