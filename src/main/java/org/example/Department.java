package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Department {
    private static final Logger LOGGER = LogManager.getLogger(Department.class);

    private final String name;
    private final List<Position> positions = new ArrayList<>();

    public Department(String name) {
        if (name == null || name.isBlank()) {
            LOGGER.error("Попытка создать отдел с пустым названием");
            throw new IllegalArgumentException("Название отдела не может быть пустым");
        }
        this.name = name;
        LOGGER.info("Создан отдел '{}'", name);
    }

    public Position addPosition(String title) {
        if (title == null || title.isBlank()) {
            LOGGER.error("Попытка добавить должность с пустым названием");
            throw new IllegalArgumentException("Название должности не может быть пустым");
        }
        if (findPosition(title) != null) {
            LOGGER.warn("Должность '{}' уже существует в отделе '{}'", title, name);
            throw new IllegalArgumentException("Должность уже существует: " + title);
        }
        Position position = new Position(title);
        positions.add(position);
        LOGGER.info("В отдел '{}' добавлена должность '{}'", name, title);
        return position;
    }

    public Position findPosition(String title) {
        for (Position position : positions) {
            if (position.getTitle().equals(title)) {
                return position;
            }
        }
        return null;
    }

    public List<Position> getPositionsSortedByHolders() {
        List<Position> sorted = new ArrayList<>(positions);
        sorted.sort(new Comparator<Position>() {
            @Override
            public int compare(Position a, Position b) {
                return Integer.compare(b.getHolders().size(), a.getHolders().size());
            }
        });
        LOGGER.debug("Должности отдела '{}' отсортированы по числу сотрудников(по убыванию)", name);
        return sorted;
    }

    public List<Position> getPositions() {
        return Collections.unmodifiableList(positions);
    }


    public class Position {
        private final String title;
        private final List<Employee> holders = new ArrayList<>();
        private Employee current;

        private Position(String title) {
            this.title = title;
        }

        public void assign(Employee employee) {
            if (employee == null) {
                LOGGER.error("Попытка назначить null на должность '{}'", title);
                throw new IllegalArgumentException("Сотрудник не может быть null");
            }
            if (!holders.contains(employee)) {
                holders.add(employee);
            }
            current = employee;
            LOGGER.info("На должность '{}' назначен {}", title, employee);
        }

        public void vacate() {
            LOGGER.info("Должность '{}' освобождена", title);
            current = null;
        }

        public String getTitle() {
            return title;
        }

        public Employee getCurrent() {
            return current;
        }

        public List<Employee> getHolders() {
            return Collections.unmodifiableList(holders);
        }

        @Override
        public String toString() {
            return Department.this.name + " / " + title
                    + ": сейчас " + (current == null ? "вакантна" : current)
                    + ", все занимавшие " + holders;
        }
    }
}