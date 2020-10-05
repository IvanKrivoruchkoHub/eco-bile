package org.example.ecobike.model;

public class FoldingBike extends Bike {
    private int sizeOfWheels;
    private int numbersOfGears;

    public int getSizeOfWheels() {
        return sizeOfWheels;
    }

    public void setSizeOfWheels(int sizeOfWheels) {
        this.sizeOfWheels = sizeOfWheels;
    }

    public int getNumbersOfGears() {
        return numbersOfGears;
    }

    public void setNumbersOfGears(int numbersOfGears) {
        this.numbersOfGears = numbersOfGears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoldingBike that = (FoldingBike) o;
        return sizeOfWheels == that.sizeOfWheels &&
            numbersOfGears == that.numbersOfGears;
    }

    @Override
    public String convertToString() {
        return "FOLDING BIKE "
            + getBrand() + "; "
            + getSizeOfWheels() + "; "
            + getNumbersOfGears() + "; "
            + getWeightInGrams() + "; "
            + isAvailabilityLights() + "; "
            + getColor() + "; "
            + getPrice();
    }

    @Override
    public String toString() {
        return "FOLDING BIKE " + getBrand()
            + " with " + getNumbersOfGears() + " gear(s) and "
            + (isAvailabilityLights() ? "" : "no") + " head/tail light.\n"
            + "Price: " + getPrice() + " euros.";
    }
}
