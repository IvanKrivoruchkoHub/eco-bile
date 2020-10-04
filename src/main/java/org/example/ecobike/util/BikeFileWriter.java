package org.example.ecobike.util;

import java.util.List;
import org.example.ecobike.model.Bike;

public interface BikeFileWriter {
    void writeToFile(List<Bike> bikes, String pathToFile);
}
