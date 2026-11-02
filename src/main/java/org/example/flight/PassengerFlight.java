package org.example.flight;

public class PassengerFlight extends AbstractFlight {
    private static final double LUGGAGE_WEIGHT = 20.0;
    private static final double LUGGAGE_VALUE = 350.0;

    private final int capacity;
    private int passengers;

    public PassengerFlight(String flightNumber, String destination, String aircraftModel,
                           double averageFlightHours, double equippedWeight, double fuel, int capacity) {
        super(flightNumber, destination, aircraftModel, averageFlightHours, equippedWeight, fuel);
        require(capacity > 0, "Вместимость должна быть больше 0");
        this.capacity = capacity;
    }

    public void boardPassengers(int count) {
        require(count > 0 && passengers + count <= capacity, "Некорректное число пассажиров или нет свободных мест");
        load(count * LUGGAGE_WEIGHT);
        passengers += count;
        log.info("Рейс {}: пассажиров {}/{}", getFlightNumber(), passengers, capacity);
    }

    public void disembarkPassengers() {
        if (getPayloadWeight() > 0) {
            unload(getPayloadWeight());
        }
        passengers = 0;
        log.info("Рейс {}: пассажиры высажены", getFlightNumber());
    }

    public int getPassengers() { return passengers; }
    public double getOccupancyRate() { return 100.0 * passengers / capacity; }

    @Override public boolean isCargoFragile() { return false; }
    @Override public double getCargoValue() { return passengers * LUGGAGE_VALUE; }
}