package org.example.ecobike.model;

public class SpeedElec extends Bike {
    private int maxSpeed;
    private int batteryCapacity;

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpeedElec speedElec = (SpeedElec) o;
        return maxSpeed == speedElec.maxSpeed &&
            batteryCapacity == speedElec.batteryCapacity;
    }

    @Override
    public String convertToString() {
        return "SPEEDELEC "
            + getBrand() + "; "
            + getMaxSpeed() + "; "
            + getWeightInGrams() + "; "
            + isAvailabilityLights() + "; "
            + getBatteryCapacity() + "; "
            + getColor() + "; "
            + getPrice();
    }

    @Override
    public String toString() {
        return "SPEEDELEC " + getBrand()
            + "with " + getBatteryCapacity() + " mAh battery and "
            + (isAvailabilityLights() ? "" : "no") + " head/tail light.\n"
            + "Price: " + getPrice() + " euros.";
    }
}
