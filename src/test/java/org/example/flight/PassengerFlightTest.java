package org.example.flight;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PassengerFlightTest {
    private PassengerFlight flight;

    @BeforeMethod
    public void setUp() {
        flight = new PassengerFlight("LH-1", "Мюнхен", "Airbus A320", 2.5, 42000.0, 5000.0, 180);
    }

    @Test
    public void constructorShouldRejectInvalidCapacity() {
        assertThrows(IllegalArgumentException.class,
                () -> new PassengerFlight("LH-2", "Мюнхен", "Airbus A320", 2.5, 42000.0, 5000.0, 0));
    }

    @Test
    public void boardShouldUpdatePassengersLuggageAndValue() {
        flight.boardPassengers(90);
        assertEquals(flight.getPassengers(), 90);
        assertEquals(flight.getOccupancyRate(), 50.0, 0.001);
        assertEquals(flight.getPayloadWeight(), 1800.0, 0.001);
        assertEquals(flight.getCargoValue(), 31500.0, 0.001);
        assertFalse(flight.isCargoFragile());
    }

    @Test
    public void boardShouldRejectInvalidRequests() {
        assertThrows(IllegalArgumentException.class, () -> flight.boardPassengers(0));
        assertThrows(IllegalArgumentException.class, () -> flight.boardPassengers(181));
        flight.send();
        assertThrows(IllegalStateException.class, () -> flight.boardPassengers(10));
        assertEquals(flight.getPassengers(), 0);
    }

    @Test
    public void disembarkShouldResetPassengersAndLuggage() {
        flight.boardPassengers(100);
        flight.disembarkPassengers();
        assertEquals(flight.getPassengers(), 0);
        assertEquals(flight.getPayloadWeight(), 0.0, 0.001);
    }

    @Test
    public void disembarkOnEmptyFlightShouldDoNothing() {
        flight.disembarkPassengers();
        assertEquals(flight.getPassengers(), 0);
    }

    @Test
    public void disembarkInFlightShouldFail() {
        flight.boardPassengers(10);
        flight.send();
        assertThrows(IllegalStateException.class, () -> flight.disembarkPassengers());
        assertEquals(flight.getPassengers(), 10);
    }
}