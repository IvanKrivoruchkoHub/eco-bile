package org.example.ecobike.model;

public class EBike extends SpeedElec {
    @Override
    public String toString() {
        return "E-BIKE " + getBrand()
            + " with" + getBatteryCapacity() + " mAh battery and"
            + (isAvailabilityLights() ? "" : "no") + " head/tail light.\n"
            + "Price: " + getPrice() + " euros.";
    }

    @Override
    public String convertToString() {
        return "E-BIKE "
            + getBrand() + "; "
            + getMaxSpeed() + "; "
            + getWeightInGrams() + "; "
            + isAvailabilityLights() + "; "
            + getBatteryCapacity() + "; "
            + getColor() + "; "
            + getPrice();
    }
}
