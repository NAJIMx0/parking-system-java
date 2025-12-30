package com.najim.DAO;

import com.najim.connection.DatabaseConnection;
import com.najim.model.Car;

import java.lang.invoke.LambdaConversionException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

//    saveCar() → INSERT a new car
//    getCarById() → SELECT car by ID
//    getAllCars() → SELECT all cars
//    updateCar() → UPDATE car info
//    deleteCar() → DELETE a car


    public static boolean saveCar(Car cr ) throws SQLException {

        String sql = "INSERT INTO car (plateNumber, color) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cr.getPlateNumber());
            ps.setString(2, cr.getColor());
            ps.executeUpdate();
            return true;
        }

    }
    public static Car getCarByplateNumber(String plateNumber) throws SQLException {
        String sql="SELECT * FROM Car WHERE plateNumber = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, plateNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Car Ocar = new Car();
                Ocar.setIdCar(rs.getInt("idCar"));
                Ocar.setPlateNumber(rs.getString("plateNumber"));
                Ocar.setColor(rs.getString("color"));
                rs.close();
                ps.close();
                conn.close();
                return Ocar;
            }
        }
        return null;
    }

    public static List<Car> getAllCars() throws SQLException {
        String sql = "SELECT * FROM car";
        List<Car> Lcars = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Car Ocar = new Car();
                Ocar.setIdCar(rs.getInt("idCar"));
                Ocar.setPlateNumber(rs.getString("plateNumber"));
                Ocar.setColor(rs.getString("color"));
                Lcars.add(Ocar);
            }
            rs.close();
            ps.close();
        }
        return Lcars;
    }

    public static boolean updateCar(Car cr) throws SQLException {
        String sql = "UPDATE car SET  color = ? WHERE plateNumber = ?";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(2, cr.getPlateNumber());
            ps.setString(1, cr.getColor());
            ps.executeUpdate();
            return true;
        }
    }
    public static boolean deleteCar( String plateNumber) throws SQLException {
        String sql = "DELETE FROM car WHERE plateNumber = ?";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, plateNumber);
            ps.executeUpdate();
            return true;
        }
    }

}
