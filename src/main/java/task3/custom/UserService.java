package task3.custom;

import java.util.*;

public class UserService {

    private final UserComparator countryAndAgeComparator = (u1, u2) -> {
        int cmp = u1.getCountry().compareToIgnoreCase(u2.getCountry());
        if (cmp != 0) return cmp;
        return Integer.compare(u1.getAge(), u2.getAge());
    };

    private final UserComparator nameComparator = (u1, u2) ->
            u1.getName().compareToIgnoreCase(u2.getName());

    public UserComparator getCountryAndAgeComparator() {
        return countryAndAgeComparator;
    }

    public UserComparator getNameComparator() {
        return nameComparator;
    }

    public UserPredicate createFilter(int minAge, char initialLetter) {
        return u -> u.getAge() > minAge
                && u.getName().toUpperCase().startsWith(String.valueOf(initialLetter).toUpperCase());
    }

    public void sort(User[] array, UserComparator comp) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (comp.compare(array[j], array[j + 1]) > 0) {
                    User temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public User[] filter(User[] array, UserPredicate predicate) {
        List<User> list = new ArrayList<>();
        for (User u : array) {
            if (predicate.test(u)) {
                list.add(u);
            }
        }
        return list.toArray(new User[0]);
    }

    public Map<Integer, List<User>> groupByAge(User[] array) {
        Map<Integer, List<User>> map = new LinkedHashMap<>();
        for (User u : array) {
            map.computeIfAbsent(u.getAge(), k -> new ArrayList<>()).add(u);
        }
        return map;
    }

    public User findMin(List<User> list, UserComparator comp) {
        if (list == null || list.isEmpty()) return null;
        User min = list.getFirst();
        for (User u : list) {
            if (comp.compare(u, min) < 0) {
                min = u;
            }
        }
        return min;
    }

    public User findMax(List<User> list, UserComparator comp) {
        if (list == null || list.isEmpty()) return null;
        User max = list.getFirst();
        for (User u : list) {
            if (comp.compare(u, max) > 0) {
                max = u;
            }
        }
        return max;
    }
}
