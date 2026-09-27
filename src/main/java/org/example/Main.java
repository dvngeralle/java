package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество окружностей: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Количество окружностей должно быть больше нуля!");
            scanner.close();
            return;
        }

        Circle[] circles = new Circle[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Ввод данных для окружности № " + (i + 1) + " ---");

            System.out.print("Введите числитель и знаменатель для X (через пробел): ");
            int numX = scanner.nextInt();
            int denX = scanner.nextInt();
            RationalFraction x = new RationalFraction(numX, denX);

            System.out.print("Введите числитель и знаменатель для Y (через пробел): ");
            int numY = scanner.nextInt();
            int denY = scanner.nextInt();
            RationalFraction y = new RationalFraction(numY, denY);

            Point center = new Point(x, y);

            System.out.print("Введите радиус: ");
            double radius = scanner.nextDouble();

            circles[i] = new Circle(radius, center);
        }

        // 1. Поиск min и max с использованием GeometryUtils
        Circle minCircle = GeometryUtils.findMinAreaCircle(circles);
        Circle maxCircle = GeometryUtils.findMaxAreaCircle(circles);

        System.out.println("\nОкружность с минимальной площадью: " + minCircle);
        System.out.println("Окружность с максимальной площадью: " + maxCircle);

        // 2. Поиск троек коллинеарных окружностей
        System.out.println("\nГруппы окружностей, центры которых лежат на одной прямой:");
        boolean foundGroup = false;

        for (int i = 0; i < circles.length; i++) {
            for (int j = i + 1; j < circles.length; j++) {
                for (int k = j + 1; k < circles.length; k++) {

                    if (GeometryUtils.isCollinear(circles[i].getCenter(), circles[j].getCenter(), circles[k].getCenter())) {
                        System.out.println("Найдены 3 окружности на одной прямой:");
                        System.out.println("  1) " + circles[i]);
                        System.out.println("  2) " + circles[j]);
                        System.out.println("  3) " + circles[k]);
                        foundGroup = true;
                    }

                }
            }
        }

        if (!foundGroup) {
            System.out.println("Групп из 3 и более окружностей на одной прямой не найдено.");
        }

        scanner.close();
    }
}