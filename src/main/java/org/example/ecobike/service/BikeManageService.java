package org.example.ecobike.service;

import org.example.ecobike.model.Bike;

public interface BikeManageService {
    void showBikeCatalog();
    Bike findBike(String params);
    void addFoldingBike(String data);
    void addSpeedElecBike(String data);
    void addEBike(String data);
    void writeBikesToFile(String path);
}
