package org.example.flight;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractFlightTest {
    private static class TestFlight extends AbstractFlight {
        TestFlight(String number, String destination, String model, double hours, double weight, double fuel) {
            super(number, destination, model, hours, weight, fuel);
        }

        @Override public boolean isCargoFragile() { return false; }
        @Override public double getCargoValue() { return 0; }
    }

    private TestFlight flight;

    @BeforeMethod
    public void setUp() {
        flight = new TestFlight("SU-1", "Минск", "Boeing 737", 2.0, 40000.0, 1000.0);
    }

    @DataProvider
    public Object[][] invalidArguments() {
        return new Object[][]{
                {" ", "Минск", "Boeing", 2.0, 40000.0, 1000.0},
                {"SU-1", "", "Boeing", 2.0, 40000.0, 1000.0},
                {"SU-1", "Минск", null, 2.0, 40000.0, 1000.0},
                {"SU-1", "Минск", "Boeing", 0.0, 40000.0, 1000.0},
                {"SU-1", "Минск", "Boeing", 2.0, 0.0, 1000.0},
                {"SU-1", "Минск", "Boeing", 2.0, 40000.0, -1.0}
        };
    }

    @Test(dataProvider = "invalidArguments", expectedExceptions = IllegalArgumentException.class)
    public void constructorShouldRejectInvalidArguments(String number, String destination, String model,
                                                        double hours, double weight, double fuel) {
        new TestFlight(number, destination, model, hours, weight, fuel);
    }

    @Test
    public void newFlightShouldHaveGivenParameters() {
        assertEquals(flight.getFlightNumber(), "SU-1");
        assertEquals(flight.getDestination(), "Минск");
        assertEquals(flight.getAircraftModel(), "Boeing 737");
        assertEquals(flight.getAverageFlightHours(), 2.0, 0.001);
        assertEquals(flight.getEquippedWeight(), 40000.0, 0.001);
        assertEquals(flight.getFuel(), 1000.0, 0.001);
        assertEquals(flight.getPayloadWeight(), 0.0, 0.001);
        assertEquals(flight.getStatus(), FlightStatus.READY_FOR_DEPARTURE);
    }

    @Test
    public void settersShouldUpdateValues() {
        flight.setFlightNumber("SU-2");
        flight.setDestination("Вильнюс");
        flight.setAircraftModel("Airbus A320");
        flight.setStatus(FlightStatus.LOADING);
        assertEquals(flight.getFlightNumber(), "SU-2");
        assertEquals(flight.getDestination(), "Вильнюс");
        assertEquals(flight.getAircraftModel(), "Airbus A320");
        assertEquals(flight.getStatus(), FlightStatus.LOADING);
    }

    @Test
    public void settersShouldRejectInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> flight.setFlightNumber(" "));
        assertThrows(IllegalArgumentException.class, () -> flight.setDestination(""));
        assertThrows(IllegalArgumentException.class, () -> flight.setAircraftModel(null));
        assertThrows(IllegalArgumentException.class, () -> flight.setStatus(null));
    }

    @Test
    public void sendShouldMoveToInFlight() {
        flight.send();
        assertEquals(flight.getStatus(), FlightStatus.IN_FLIGHT);
    }

    @Test
    public void sendShouldFailWithoutFuelOrWhenNotReady() {
        assertThrows(IllegalStateException.class,
                () -> new TestFlight("SU-3", "Минск", "Boeing", 2.0, 40000.0, 0.0).send());
        flight.setStatus(FlightStatus.NEEDS_REPAIR);
        assertThrows(IllegalStateException.class, () -> flight.send());
    }

    @Test
    public void repairShouldWorkOnlyWhenBroken() {
        assertThrows(IllegalStateException.class, () -> flight.repair());
        flight.setStatus(FlightStatus.NEEDS_REPAIR);
        flight.repair();
        assertEquals(flight.getStatus(), FlightStatus.READY_FOR_DEPARTURE);
        flight.setStatus(FlightStatus.UNDER_REPAIR);
        flight.repair();
        assertEquals(flight.getStatus(), FlightStatus.READY_FOR_DEPARTURE);
    }

    @Test
    public void refuelShouldIncreaseFuelAndRejectInvalidRequests() {
        flight.refuel(500.0);
        assertEquals(flight.getFuel(), 1500.0, 0.001);
        assertThrows(IllegalArgumentException.class, () -> flight.refuel(0));
        flight.send();
        assertThrows(IllegalStateException.class, () -> flight.refuel(100.0));
        assertEquals(flight.getFuel(), 1500.0, 0.001);
    }

    @Test
    public void loadShouldIncreasePayloadAndRejectInvalidRequests() {
        flight.load(300.0);
        flight.load(200.0);
        assertEquals(flight.getPayloadWeight(), 500.0, 0.001);
        assertThrows(IllegalArgumentException.class, () -> flight.load(-1));
        flight.send();
        assertThrows(IllegalStateException.class, () -> flight.load(100.0));
        assertEquals(flight.getPayloadWeight(), 500.0, 0.001);
    }

    @Test
    public void unloadShouldDecreasePayloadAndRejectInvalidRequests() {
        flight.load(500.0);
        flight.unload(200.0);
        assertEquals(flight.getPayloadWeight(), 300.0, 0.001);
        assertThrows(IllegalArgumentException.class, () -> flight.unload(0));
        assertThrows(IllegalArgumentException.class, () -> flight.unload(301.0));
        flight.send();
        assertThrows(IllegalStateException.class, () -> flight.unload(100.0));
        assertEquals(flight.getPayloadWeight(), 300.0, 0.001);
    }

    @Test
    public void toStringShouldContainFlightNumberAndDestination() {
        assertTrue(flight.toString().contains("SU-1"));
        assertTrue(flight.toString().contains("Минск"));
    }
}