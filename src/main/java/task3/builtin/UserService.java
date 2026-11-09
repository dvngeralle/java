package task3.builtin;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserService {

    private final Comparator<User> countryAndAgeComparator = Comparator
            .comparing(User::getCountry, String.CASE_INSENSITIVE_ORDER)
            .thenComparingInt(User::getAge);

    private final Comparator<User> nameComparator =
            Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER);

    public Comparator<User> getCountryAndAgeComparator() {
        return countryAndAgeComparator;
    }

    public Comparator<User> getNameComparator() {
        return nameComparator;
    }

    public Predicate<User> createFilter(int minAge, char initialLetter) {
        return u -> u.getAge() > minAge
                && u.getName().toUpperCase().startsWith(String.valueOf(initialLetter).toUpperCase());
    }

    public void sort(User[] array, Comparator<User> comp) {
        Arrays.sort(array, comp);
    }

    public User[] filter(User[] array, Predicate<User> predicate) {
        return Arrays.stream(array)
                .filter(predicate)
                .toArray(User[]::new);
    }

    public Map<Integer, List<User>> groupByAge(User[] array) {
        return Arrays.stream(array)
                .collect(Collectors.groupingBy(User::getAge, LinkedHashMap::new, Collectors.toList()));
    }

    public User findMin(List<User> list, Comparator<User> comp) {
        if (list == null || list.isEmpty()) return null;
        return Collections.min(list, comp);
    }

    public User findMax(List<User> list, Comparator<User> comp) {
        if (list == null || list.isEmpty()) return null;
        return Collections.max(list, comp);
    }
}
