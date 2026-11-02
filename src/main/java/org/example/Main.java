package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.flight.CargoFlight;
import org.example.flight.FlightStatus;
import org.example.flight.PassengerFlight;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        CargoFlight cargo = new CargoFlight("CV-748", "Франкфурт", "Boeing 747-8F", 4.5, 197000.0, 15000.0);
        cargo.refuel(45000.0);
        cargo.loadCargo(42000.0, true, 850000.0);
        cargo.loadCargo(18000.0, false, 250000.0);
        LOGGER.info("Ценность груза: {}, хрупкий: {}, страховка: {}",
                cargo.getCargoValue(), cargo.isCargoFragile(), cargo.calculateInsuranceCost(0.02));
        cargo.send();
        cargo.setStatus(FlightStatus.READY_FOR_DEPARTURE);
        cargo.unload(60000.0);

        PassengerFlight passenger = new PassengerFlight("LH-1487", "Мюнхен", "Airbus A320neo",
                2.5, 42000.0, 5000.0, 180);
        passenger.boardPassengers(160);
        LOGGER.info("Заполненность: {}%", String.format("%.1f", passenger.getOccupancyRate()));
        passenger.send();
        try {
            passenger.refuel(1000.0);
        } catch (IllegalStateException e) {
            LOGGER.warn("Ожидаемая ошибка: {}", e.getMessage());
        }
    }
}