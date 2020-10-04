package org.example.ecobike.util;

import org.example.ecobike.model.Bike;

public interface BikeFactory {
    Bike getBikeFromString(String bikeData);
}
