    import java.util.Scanner;

    /*
    * Найти и вывести все четырёхзначные числа из заданной последовательности
    * целых чисел. Подсчитать количество четырёхзначных чисел, сумма цифр которых равна
    * заданному числу k.
    */
    public class Task2 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите n: ");
            int n = scanner.nextInt();

            int[] numbers = new int[n];
            System.out.print("Введите последовательность из " + n + " чисел через пробел: ");
            for (int i = 0; i < n; i++) {
                numbers[i] = scanner.nextInt();
            }

            System.out.print("Введите k: ");
            int k = scanner.nextInt();

            int count = 0;

            for (int i = 0; i < n; i++) {
                int number = numbers[i];
                if( number >= 1000 && number <= 9999){
                    System.out.println(number);
                
                int sum = 0;
            while(number != 0){
                int digit = number % 10;
                sum = sum+digit;
                number = number/10;
            }
                if(sum == k){
                    count = count + 1;
                }
            }
            }

            scanner.close();
            System.out.println("Количество четырёхзначных чисел с суммой цифр k: " + count);
        }
    }