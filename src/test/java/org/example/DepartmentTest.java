package org.example;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DepartmentTest {
    private Department department;

    @BeforeMethod
    public void setUp() {
        department = new Department("IT-отдел");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void constructorShouldRejectBlankName() {
        new Department(" ");
    }

    @Test
    public void addPositionShouldStorePosition() {
        Department.Position position = department.addPosition("Разработчик");
        assertEquals(department.getPositions().size(), 1);
        assertSame(department.findPosition("Разработчик"), position);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addPositionShouldRejectBlankTitle() {
        department.addPosition("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addPositionShouldRejectDuplicate() {
        department.addPosition("Менеджер");
        department.addPosition("Менеджер");
    }

    @Test
    public void findPositionShouldReturnNullIfMissing() {
        assertNull(department.findPosition("Нет такой"));
    }

    @Test
    public void assignShouldSetCurrentAndKeepHistory() {
        Department.Position position = department.addPosition("Разработчик");
        Employee first = new Employee("Иванов");
        Employee second = new Employee("Петров");
        position.assign(first);
        position.assign(second);
        assertEquals(position.getCurrent(), second);
        assertEquals(position.getHolders(), List.of(first, second));
    }

    @Test
    public void assignSameEmployeeTwiceShouldNotDuplicateHistory() {
        Department.Position position = department.addPosition("Разработчик");
        Employee employee = new Employee("Иванов");
        position.assign(employee);
        position.assign(employee);
        assertEquals(position.getHolders().size(), 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void assignShouldRejectNull() {
        department.addPosition("Разработчик").assign(null);
    }

    @Test
    public void vacateShouldClearCurrentButKeepHistory() {
        Department.Position position = department.addPosition("Менеджер");
        position.assign(new Employee("Козлов"));
        position.vacate();
        assertNull(position.getCurrent());
        assertEquals(position.getHolders().size(), 1);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void holdersListShouldBeUnmodifiable() {
        Department.Position position = department.addPosition("Менеджер");
        position.getHolders().add(new Employee("Хакер"));
    }

    @Test
    public void positionsShouldBeSortedByHoldersDescending() {
        Department.Position manager = department.addPosition("Менеджер");
        Department.Position developer = department.addPosition("Разработчик");
        Department.Position analyst = department.addPosition("Аналитик");
        manager.assign(new Employee("Козлов"));
        developer.assign(new Employee("Иванов"));
        developer.assign(new Employee("Петров"));
        developer.assign(new Employee("Сидоров"));
        analyst.assign(new Employee("Орлов"));
        analyst.assign(new Employee("Волков"));

        assertEquals(department.getPositionsSortedByHolders(), List.of(developer, analyst, manager));
        assertEquals(department.getPositions(), List.of(manager, developer, analyst));
    }

    @Test
    public void positionToStringShouldContainNamesWithoutRecordNoise() {
        Department.Position position = department.addPosition("Разработчик");
        position.assign(new Employee("Иванов"));
        String text = position.toString();
        assertTrue(text.contains("сейчас Иванов"));
        assertFalse(text.contains("Employee["));
    }

    @Test
    public void vacantPositionToStringShouldSayVacant() {
        assertTrue(department.addPosition("Менеджер").toString().contains("вакантна"));
    }
}