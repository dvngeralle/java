package org.example.flight;

public enum FlightStatus {
    READY_FOR_DEPARTURE("Готов к вылету"),
    LOADING("Загрузка"),
    UNLOADING("Разгрузка"),
    REFUELING("Заправка"),
    IN_FLIGHT("В пути"),
    NEEDS_REPAIR("Требуется ремонт"),
    UNDER_REPAIR("В ремонте");

    private final String description;

    FlightStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
