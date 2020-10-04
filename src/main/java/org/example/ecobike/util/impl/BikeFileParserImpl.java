package org.example.ecobike.util.impl;

import org.example.ecobike.model.Bike;
import org.example.ecobike.util.BikeFactory;
import org.example.ecobike.util.BikeFileParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BikeFileParserImpl implements BikeFileParser {
    private BikeFactory bikeFactory;

    public BikeFileParserImpl(BikeFactory bikeFactory) {
        this.bikeFactory = bikeFactory;
    }

    public BikeFactory getBikeFactory() {
        return bikeFactory;
    }

    public void setBikeFactory(BikeFactory bikeFactory) {
        this.bikeFactory = bikeFactory;
    }

    @Override
    public List<Bike> getBikesFromFile(String filePath) {
        List<Bike> bikes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                bikes.add(bikeFactory.getBikeFromString(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bikes;
    }
}
