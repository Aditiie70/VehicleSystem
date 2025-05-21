package com.java.examly.service;

import com.java.examly.entity.Vehicle;
import com.java.examly.util.DBConnectionUtil;
import java.sql.*;
import java.util.*;

public class VehicleServiceImpl implements VehicleService {

    @Override
    public void addVehicle(Vehicle vehicle) {
        try (Connection conn = DBConnectionUtil.getConnection()) {
            String sql = "INSERT INTO vehicle(brand, model, type, price, available) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vehicle.getBrand());
            stmt.setString(2, vehicle.getModel());
            stmt.setString(3, vehicle.getType());
            stmt.setDouble(4, vehicle.getPrice());
            stmt.setBoolean(5, vehicle.isAvailable());
            stmt.executeUpdate();
            System.out.println("Vehicle added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVehicle(int id, Vehicle vehicle) {
        try (Connection conn = DBConnectionUtil.getConnection()) {
            StringBuilder query = new StringBuilder("UPDATE vehicle SET ");
            List<String> updates = new ArrayList<>();
            if (vehicle.getBrand() != null && !vehicle.getBrand().isEmpty()) updates.add("brand='" + vehicle.getBrand() + "'");
            if (vehicle.getModel() != null && !vehicle.getModel().isEmpty()) updates.add("model='" + vehicle.getModel() + "'");
            if (vehicle.getType() != null && !vehicle.getType().isEmpty()) updates.add("type='" + vehicle.getType() + "'");
            if (vehicle.getPrice() > 0) updates.add("price=" + vehicle.getPrice());
            updates.add("available=" + vehicle.isAvailable());
            query.append(String.join(", ", updates));
            query.append(" WHERE id=").append(id);

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query.toString());
            System.out.println("Vehicle updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVehicle(int id) {
        try (Connection conn = DBConnectionUtil.getConnection()) {
            String sql = "DELETE FROM vehicle WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Vehicle deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> list = new ArrayList<>();
        try (Connection conn = DBConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM vehicle";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setType(rs.getString("type"));
                vehicle.setPrice(rs.getDouble("price"));
                vehicle.setAvailable(rs.getBoolean("available"));
                list.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Vehicle> searchByBrand(String brand) {
        List<Vehicle> list = new ArrayList<>();
        try (Connection conn = DBConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM vehicle WHERE brand = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, brand);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setType(rs.getString("type"));
                vehicle.setPrice(rs.getDouble("price"));
                vehicle.setAvailable(rs.getBoolean("available"));
                list.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Vehicle> filterByType(String type) {
        List<Vehicle> list = new ArrayList<>();
        try (Connection conn = DBConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM vehicle WHERE type = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setType(rs.getString("type"));
                vehicle.setPrice(rs.getDouble("price"));
                vehicle.setAvailable(rs.getBoolean("available"));
                list.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

   
    
}
