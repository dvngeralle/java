package org.example.flight;

public class CargoFlight extends AbstractFlight {
    private double cargoValue;
    private boolean fragile;

    public CargoFlight(String flightNumber, String destination, String aircraftModel,
                       double averageFlightHours, double equippedWeight, double fuel) {
        super(flightNumber, destination, aircraftModel, averageFlightHours, equippedWeight, fuel);
    }

    public void loadCargo(double mass, boolean isFragile, double value) {
        require(value >= 0, "Ценность груза не может быть отрицательной");
        load(mass);
        cargoValue += value;
        fragile |= isFragile;
        log.info("Рейс {}: ценность груза {}, хрупкий: {}", getFlightNumber(), cargoValue, fragile);
    }

    @Override
    public void unload(double mass) {
        double before = getPayloadWeight();
        super.unload(mass);
        double after = getPayloadWeight();
        cargoValue *= after / before;
        if (after == 0) {
            fragile = false;
        }
    }

    public double calculateInsuranceCost(double rate) {
        require(rate >= 0 && rate <= 1, "Ставка страховки должна быть от 0 до 1");
        return cargoValue * rate * (fragile ? 1.25 : 1.0);
    }

    @Override public boolean isCargoFragile() { return fragile; }
    @Override public double getCargoValue() { return cargoValue; }
}