package org.example.ecobike.util.impl;

import org.example.ecobike.model.Bike;
import org.example.ecobike.model.EBike;
import org.example.ecobike.model.FoldingBike;
import org.example.ecobike.model.SpeedElec;
import org.example.ecobike.util.BikeFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BikeFactoryImpl implements BikeFactory {

    private Map<String, Function<String, Bike>> functionMap = new HashMap<>();

    public BikeFactoryImpl() {
        functionMap.put("ebike", this::createSpeedElec);
        functionMap.put("speedelec", this::createEBike);
        functionMap.put("folding", this::createFoldingBike);
    }

    @Override
    public Bike getBikeFromString(String bikeData) {
        String type =  bikeData
            .split("; ")[0]
            .split(" ")[0]
            .toLowerCase()
            .replaceAll("[^a-z]", "");
        return functionMap.get(type).apply(bikeData);
    }

    private SpeedElec initialize(String[] bikeData, SpeedElec bike) {
        bike.setBrand(bikeData[0].split(" ")[1]);
        bike.setMaxSpeed(Integer.parseInt(bikeData[1]));
        bike.setWeightInGrams(Integer.parseInt(bikeData[2]));
        bike.setAvailabilityLights(Boolean.parseBoolean(bikeData[3]));
        bike.setBatteryCapacity(Integer.parseInt(bikeData[4]));
        bike.setColor(bikeData[5]);
        bike.setPrice(Integer.parseInt(bikeData[6]));
        return bike;
    }

    private SpeedElec createSpeedElec(String data) {
        String[] bikeData = data.split("; ");
        SpeedElec bike = new SpeedElec();
        return initialize(bikeData, bike);
    }

    private FoldingBike createFoldingBike(String data) {
        String[] bikeData = data.split("; ");
        FoldingBike bike = new FoldingBike();
        bike.setBrand(bikeData[0].split(" ")[2]);
        bike.setSizeOfWheels(Integer.parseInt(bikeData[1]));
        bike.setNumbersOfGears(Integer.parseInt(bikeData[2]));
        bike.setWeightInGrams(Integer.parseInt(bikeData[3]));
        bike.setAvailabilityLights(Boolean.parseBoolean(bikeData[4]));
        bike.setColor(bikeData[5]);
        bike.setPrice(Integer.parseInt(bikeData[6]));
        return bike;
    }

    private SpeedElec createEBike(String data) {
        String[] bikeData = data.split("; ");
        EBike bike = new EBike();
        return initialize(bikeData, bike);
    }
}
