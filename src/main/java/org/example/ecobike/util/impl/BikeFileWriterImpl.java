package org.example.ecobike.util.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.example.ecobike.model.Bike;
import org.example.ecobike.util.BikeFileWriter;

public class BikeFileWriterImpl implements BikeFileWriter {
    @Override
    public void writeToFile(List<Bike> bikes, String pathToFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFile, true))) {
            for (Bike bike: bikes) {
                bw.write(bike.convertToString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
