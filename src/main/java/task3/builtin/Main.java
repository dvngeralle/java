package task3.builtin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();

        User[] users = new User[]{
                new User(1, "Алексей", 25, "Германия"),
                new User(2, "Андрей", 30, "Беларусь"),
                new User(3, "Борис", 20, "Германия"),
                new User(4, "Анна", 25, "Беларусь"),
                new User(5, "Алина", 30, "Беларусь"),
                new User(6, "Виктор", 40, "Польша"),
                new User(7, "Артем", 18, "Германия"),
                new User(8, "Анастасия", 25, "Германия")
        };

        service.sort(users, service.getCountryAndAgeComparator());
        System.out.println("Отсортировано по стране и возрасту:");
        Arrays.stream(users).forEach(System.out::println);

        int targetAge = 20;
        char targetLetter = 'А';
        User[] filtered = service.filter(users, service.createFilter(targetAge, targetLetter));
        System.out.println("\nПользователи старше " + targetAge + " с первой буквой '" + targetLetter + "':");
        Arrays.stream(filtered).forEach(System.out::println);

        Map<Integer, List<User>> grouped = service.groupByAge(filtered);
        System.out.println("\nСгруппировано по возрасту:");
        grouped.forEach((age, list) -> System.out.println("Возраст " + age + ": " + list));

        System.out.println("\nМинимальный и максимальный элемент в каждой группе:");
        grouped.forEach((age, list) -> {
            User min = service.findMin(list, service.getNameComparator());
            User max = service.findMax(list, service.getNameComparator());
            System.out.println("Возраст " + age + " -> Min: " + (min != null ? min.getName() : null)
                    + ", Max: " + (max != null ? max.getName() : null));
        });
    }
}
