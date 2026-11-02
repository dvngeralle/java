package org.example.flight;

public interface Flight {
    String getFlightNumber();
    void setFlightNumber(String flightNumber);
    String getDestination();
    void setDestination(String destination);
    String getAircraftModel();
    void setAircraftModel(String aircraftModel);
    FlightStatus getStatus();
    void setStatus(FlightStatus status);

    double getAverageFlightHours();
    double getEquippedWeight();
    double getFuel();

    void send();
    void repair();
    void refuel(double amount);
    void load(double mass);
    void unload(double mass);

    boolean isCargoFragile();
    double getCargoValue();
}