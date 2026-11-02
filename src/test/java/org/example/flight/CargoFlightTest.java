package org.example.flight;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CargoFlightTest {
    private CargoFlight flight;

    @BeforeMethod
    public void setUp() {
        flight = new CargoFlight("CV-1", "Франкфурт", "Boeing 747F", 4.5, 197000.0, 5000.0);
    }

    @Test
    public void loadCargoShouldAccumulateValueAndFragility() {
        flight.loadCargo(100.0, false, 1000.0);
        assertFalse(flight.isCargoFragile());
        flight.loadCargo(200.0, true, 500.0);
        assertTrue(flight.isCargoFragile());
        assertEquals(flight.getCargoValue(), 1500.0, 0.001);
        assertEquals(flight.getPayloadWeight(), 300.0, 0.001);
    }

    @Test
    public void loadCargoShouldRejectNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> flight.loadCargo(100.0, false, -1.0));
    }

    @Test
    public void failedLoadShouldNotChangeCargoState() {
        flight.send();
        assertThrows(IllegalStateException.class, () -> flight.loadCargo(100.0, true, 999.0));
        assertEquals(flight.getCargoValue(), 0.0, 0.001);
        assertFalse(flight.isCargoFragile());
    }

    @Test
    public void partialUnloadShouldReduceValueProportionally() {
        flight.loadCargo(100.0, true, 1000.0);
        flight.unload(25.0);
        assertEquals(flight.getCargoValue(), 750.0, 0.001);
        assertTrue(flight.isCargoFragile());
    }

    @Test
    public void fullUnloadShouldResetCargoState() {
        flight.loadCargo(100.0, true, 1000.0);
        flight.unload(100.0);
        assertEquals(flight.getPayloadWeight(), 0.0, 0.001);
        assertEquals(flight.getCargoValue(), 0.0, 0.001);
        assertFalse(flight.isCargoFragile());
    }

    @Test
    public void insuranceShouldDependOnFragility() {
        flight.loadCargo(100.0, false, 1000.0);
        assertEquals(flight.calculateInsuranceCost(0.1), 100.0, 0.001);
        flight.loadCargo(100.0, true, 0.0);
        assertEquals(flight.calculateInsuranceCost(0.1), 125.0, 0.001);
    }

    @Test
    public void insuranceShouldRejectInvalidRate() {
        assertThrows(IllegalArgumentException.class, () -> flight.calculateInsuranceCost(-0.1));
        assertThrows(IllegalArgumentException.class, () -> flight.calculateInsuranceCost(1.1));
    }
}