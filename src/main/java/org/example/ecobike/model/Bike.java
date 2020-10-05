package org.example.ecobike.model;

import java.util.Objects;

public abstract class Bike {
    private String brand;
    private int price;
    private String color;
    private int weightInGrams;
    private boolean availabilityLights;

    public Bike() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(int weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    public boolean isAvailabilityLights() {
        return availabilityLights;
    }

    public void setAvailabilityLights(boolean availabilityLights) {
        this.availabilityLights = availabilityLights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return price == bike.price &&
            weightInGrams == bike.weightInGrams &&
            availabilityLights == bike.availabilityLights &&
            Objects.equals(brand, bike.brand) &&
            Objects.equals(color, bike.color);
    }

    public abstract String convertToString();
}
