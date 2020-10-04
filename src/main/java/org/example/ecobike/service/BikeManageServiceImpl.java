package org.example.ecobike.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import org.example.ecobike.model.Bike;
import org.example.ecobike.model.EBike;
import org.example.ecobike.model.FoldingBike;
import org.example.ecobike.model.SpeedElec;
import org.example.ecobike.util.BikeFactory;
import org.example.ecobike.util.BikeFileParser;
import org.example.ecobike.util.BikeFileWriter;

public class BikeManageServiceImpl implements BikeManageService {
    private final List<Bike> bikes;
    private HashMap<String, BiFunction<HashMap<String, String>,
        List<Bike>, List<Bike>>> bikeClasses = new HashMap<>();
    private BikeFactory bikeFactory;
    private BikeFileWriter bikeFileWriter;
    private List<Bike> bikeWriteToFile = new ArrayList<>();

    {
        bikeClasses.put("ebike", this::findEBikes);
        bikeClasses.put("speedelec", this::findSpeedElecs);
        bikeClasses.put("foldingbike", this::findFoldingBikes);
    }

    public BikeManageServiceImpl(BikeFileParser bikeFileParser,
                                 String filePath,
                                 BikeFactory bikeFactory,
                                 BikeFileWriter bikeFileWriter) {
        bikes = bikeFileParser.getBikesFromFile(filePath);
        this.bikeFactory = bikeFactory;
        this.bikeFileWriter = bikeFileWriter;
    }

    @Override
    public void showBikeCatalog() {
        for (Bike bike : bikes) {
            System.out.println(bike);
        }
    }

    @Override
    public Bike findBike(String params) {
        if (params == null || params.isBlank()) {
            return bikes.get(0);
        }
        HashMap<String, String> paramsMap = new HashMap<>();
        for (String param: params.toLowerCase().split(";")) {
            String[] keyAndValue = param.replaceAll("[^a-z=]", "").split("=");
            paramsMap.put(keyAndValue[0], keyAndValue[1]);
        }

        String typeOfBikeToSearch = paramsMap.get("type");

        if (typeOfBikeToSearch == null) {
            return truncByParams(paramsMap, bikes).get(0);
        }

        BiFunction<HashMap<String, String>, List<Bike>, List<Bike>> function = bikeClasses.get(typeOfBikeToSearch);
        List<Bike> tempBikes = bikes;
        if (function != null) {
            tempBikes = function.apply(paramsMap, tempBikes);
        }
        tempBikes = truncByParams(paramsMap, tempBikes);
        if (tempBikes.isEmpty()) {
            return null;
        }
        return tempBikes.get(0);
    }

    @Override
    public void addFoldingBike(String data) {
        bikeWriteToFile.add(bikeFactory.getBikeFromString("FOLDING BIKE " + data));
    }

    @Override
    public void addSpeedElecBike(String data) {
        bikeWriteToFile.add(bikeFactory.getBikeFromString("SPEEDELEC "  + data));
    }

    @Override
    public void addEBike(String data) {
        bikeWriteToFile.add(bikeFactory.getBikeFromString("EBIKE "  + data));
    }

    @Override
    public void writeBikesToFile(String path) {
        if (bikeWriteToFile.isEmpty()) {
            return;
        }
        bikeFileWriter.writeToFile(bikeWriteToFile, path);
        bikes.addAll(bikeWriteToFile);
    }

    private List<Bike> truncByParams(HashMap<String, String> paramsMap, List<Bike> bikes) {
        return bikes.stream()
            .filter(bike -> !paramsMap.containsKey("brand")
                || bike.getBrand().equalsIgnoreCase(paramsMap.get("brand")))
            .filter(bike -> !paramsMap.containsKey("price")
                || bike.getPrice() == Integer.parseInt(paramsMap.get("price")))
            .filter(bike -> !paramsMap.containsKey("color")
                || bike.getColor().equalsIgnoreCase(paramsMap.get("color")))
            .filter(bike -> !paramsMap.containsKey("weightingrams")
                || bike.getPrice() == Integer.parseInt(paramsMap.get("weightingrams")))
            .filter(bike -> !paramsMap.containsKey("availabilitylights")
                || bike.isAvailabilityLights() == Boolean.parseBoolean(paramsMap.get("availabilitylights")))
            .collect(Collectors.toList());
    }

    private List<Bike> findFoldingBikes(HashMap<String, String> paramsMap, List<Bike> bikes) {
        return bikes
            .stream()
            .filter(bike -> bike.getClass() == FoldingBike.class)
            .map(bike -> (FoldingBike) bike)
            .filter(bike -> !paramsMap.containsKey("sizeofwheels")
                || bike.getSizeOfWheels() == Integer.parseInt(paramsMap.get("sizeofwheels")))
            .filter(bike -> !paramsMap.containsKey("numbersofgears")
                || bike.getNumbersOfGears() == Integer.parseInt(paramsMap.get("numbersofgears")))
            .collect(Collectors.toList());
    }

    private List<Bike> findSpeedElecs(HashMap<String, String> paramsMap, List<Bike> bikes) {
        return bikes
            .stream()
            .filter(bike -> bike.getClass() == EBike.class)
            .map(bike -> (SpeedElec) bike)
            .filter(bike -> !paramsMap.containsKey("maxspeed")
                || bike.getMaxSpeed() == Integer.parseInt(paramsMap.get("maxspeed")))
            .filter(bike -> !paramsMap.containsKey("batterycapacity")
                || bike.getBatteryCapacity() == Integer.parseInt(paramsMap.get("batterycapacity")))
            .collect(Collectors.toList());
    }

    private List<Bike> findEBikes(HashMap<String, String> paramsMap, List<Bike> bikes) {
        return findSpeedElecs(paramsMap, bikes)
            .stream()
            .map(bike -> (EBike) bike)
            .collect(Collectors.toList());
    }
}
