package org.example;

public class Main {
    public static void main(String[] args) {
        Department department = new Department("IT-отдел");

        Department.Position manager = department.addPosition("Менеджер");
        Department.Position developer = department.addPosition("Разработчик");
        Department.Position analyst = department.addPosition("Аналитик");

        developer.assign(new Employee("Иванов"));
        developer.assign(new Employee("Петров"));
        developer.assign(new Employee("Сидоров"));
        manager.assign(new Employee("Козлов"));
        manager.vacate();
        manager.assign(new Employee("Смирнов"));
        analyst.assign(new Employee("Орлов"));

        department.getPositionsSortedByHolders().forEach(System.out::println);
    }
}