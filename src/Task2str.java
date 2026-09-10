    import java.util.Scanner;

    /*
    * Найти и вывести все четырёхзначные числа из заданной последовательности
    * целых чисел. Подсчитать количество четырёхзначных чисел, сумма цифр которых равна
    * заданному числу k.
    */
    public class Task2str {
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
                String s = String.valueOf(number);
                if(s.length() == 4){
                    System.out.println(number);
                int sum = 0;

                for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                int digit = Character.getNumericValue(c);
                sum = sum+digit;
                
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