package org.example.flight;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractFlight implements Flight {
    protected final Logger log = LogManager.getLogger(getClass());

    private String flightNumber;
    private String destination;
    private String aircraftModel;
    private FlightStatus status = FlightStatus.READY_FOR_DEPARTURE;
    private final double averageFlightHours;
    private final double equippedWeight;
    private double fuel;
    private double payloadWeight;

    protected AbstractFlight(String flightNumber, String destination, String aircraftModel,
                             double averageFlightHours, double equippedWeight, double fuel) {
        this.flightNumber = notBlank(flightNumber, "Номер рейса");
        this.destination = notBlank(destination, "Пункт назначения");
        this.aircraftModel = notBlank(aircraftModel, "Марка судна");
        require(averageFlightHours > 0 && equippedWeight > 0 && fuel >= 0,
                "Время рейса и масса должны быть положительными, топливо не может быть отрицательным");
        this.averageFlightHours = averageFlightHours;
        this.equippedWeight = equippedWeight;
        this.fuel = fuel;
        log.info("Создан рейс {} в {}", flightNumber, destination);
    }

    protected void require(boolean condition, String message) {
        if (!condition) {
            log.error(message);
            throw new IllegalArgumentException(message);
        }
    }

    protected void requireState(boolean condition, String message) {
        if (!condition) {
            log.error(message);
            throw new IllegalStateException(message);
        }
    }

    private String notBlank(String value, String field) {
        require(value != null && !value.isBlank(), field + " не может быть пустым");
        return value;
    }

    private void requireOnGround() {
        requireState(status != FlightStatus.IN_FLIGHT, "Действие невозможно во время полета");
    }

    @Override public String getFlightNumber() { return flightNumber; }
    @Override public void setFlightNumber(String value) { flightNumber = notBlank(value, "Номер рейса"); }
    @Override public String getDestination() { return destination; }
    @Override public void setDestination(String value) { destination = notBlank(value, "Пункт назначения"); }
    @Override public String getAircraftModel() { return aircraftModel; }
    @Override public void setAircraftModel(String value) { aircraftModel = notBlank(value, "Марка судна"); }
    @Override public FlightStatus getStatus() { return status; }
    @Override public double getAverageFlightHours() { return averageFlightHours; }
    @Override public double getEquippedWeight() { return equippedWeight; }
    @Override public double getFuel() { return fuel; }
    public double getPayloadWeight() { return payloadWeight; }

    @Override
    public void setStatus(FlightStatus newStatus) {
        require(newStatus != null, "Статус не может быть null");
        log.info("Рейс {}: статус '{}' -> '{}'", flightNumber, status, newStatus);
        status = newStatus;
    }

    @Override
    public void send() {
        requireState(status == FlightStatus.READY_FOR_DEPARTURE, "Вылет возможен только из статуса 'готов к вылету'");
        requireState(fuel > 0, "Вылет невозможен: нет топлива");
        status = FlightStatus.IN_FLIGHT;
        log.info("Рейс {} вылетел в {}", flightNumber, destination);
    }

    @Override
    public void repair() {
        requireState(status == FlightStatus.NEEDS_REPAIR || status == FlightStatus.UNDER_REPAIR,
                "Ремонт не требуется в статусе: " + status);
        status = FlightStatus.READY_FOR_DEPARTURE;
        log.info("Рейс {} отремонтирован", flightNumber);
    }

    @Override
    public void refuel(double amount) {
        require(amount > 0, "Объем заправки должен быть больше 0");
        requireOnGround();
        fuel += amount;
        log.info("Рейс {} заправлен на {}, топливо: {}", flightNumber, amount, fuel);
    }

    @Override
    public void load(double mass) {
        require(mass > 0, "Масса загрузки должна быть больше 0");
        requireOnGround();
        payloadWeight += mass;
        log.info("Рейс {}: загружено {} кг, нагрузка: {}", flightNumber, mass, payloadWeight);
    }

    @Override
    public void unload(double mass) {
        require(mass > 0 && mass <= payloadWeight, "Нельзя выгрузить больше, чем на борту");
        requireOnGround();
        payloadWeight -= mass;
        log.info("Рейс {}: выгружено {} кг, нагрузка: {}", flightNumber, mass, payloadWeight);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + flightNumber + " -> " + destination + " [" + status + "]";
    }
}