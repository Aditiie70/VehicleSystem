package com.java.examly.service;

import com.java.examly.entity.Vehicle;
import java.util.List;

public interface VehicleService {
    void addVehicle(Vehicle vehicle);
    void updateVehicle(int id, Vehicle vehicle);
    void deleteVehicle(int id);
    List<Vehicle> getAllVehicles();
    List<Vehicle> searchByBrand(String brand);
    List<Vehicle> filterByType(String type);
}
