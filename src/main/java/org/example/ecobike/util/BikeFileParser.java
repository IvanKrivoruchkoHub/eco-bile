package org.example.ecobike.util;

import org.example.ecobike.model.Bike;
import java.util.List;

public interface BikeFileParser {
    List<Bike> getBikesFromFile(String filePath);
}
