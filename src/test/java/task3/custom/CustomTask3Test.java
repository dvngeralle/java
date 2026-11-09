package task3.custom;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class CustomTask3Test {

    @Test
    public void testSortFilterAndGroup() {
        UserService service = new UserService();
        User[] users = new User[]{
                new User(1, "Алексей", 25, "Германия"),
                new User(2, "Андрей", 30, "Беларусь"),
                new User(3, "Борис", 20, "Германия"),
                new User(4, "Анна", 25, "Беларусь")
        };

        service.sort(users, service.getCountryAndAgeComparator());
        assertEquals(users[0].getCountry(), "Беларусь");
        assertEquals(users[0].getAge(), 25);
        assertEquals(users[1].getCountry(), "Беларусь");
        assertEquals(users[1].getAge(), 30);

        User[] filtered = service.filter(users, service.createFilter(20, 'А'));
        assertEquals(filtered.length, 3);

        Map<Integer, List<User>> grouped = service.groupByAge(filtered);
        assertEquals(grouped.size(), 2);
        assertTrue(grouped.containsKey(25));
        assertTrue(grouped.containsKey(30));

        User min = service.findMin(grouped.get(25), service.getNameComparator());
        User max = service.findMax(grouped.get(25), service.getNameComparator());
        assertEquals(min.getName(), "Алексей");
        assertEquals(max.getName(), "Анна");
    }
}
