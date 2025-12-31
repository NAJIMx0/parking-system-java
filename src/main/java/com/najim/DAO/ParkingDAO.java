package com.najim.DAO;

import com.najim.connection.DatabaseConnection;
import com.najim.model.Car;
import com.najim.model.Parking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//saveParking(Parking parking)
//getParkingByLocalization(String lc)

public class ParkingDAO {

    public boolean saveParking(Parking parking) throws SQLException {
        String sql = "INSERT INTO parking (name, location) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, parking.getName());
            ps.setString(2, parking.getLocation());
            ps.executeUpdate();
            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    parking.setIdParking(rs.getInt(1));
                }
                rs.close();
            }
            return true;
        }
    }


    public Parking getParkingByLocalization(String lc) throws SQLException {
        String sql="SELECT * FROM parking WHERE location = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Parking parking = new Parking();
                parking.setIdParking(rs.getInt("idParking"));
                parking.setName(rs.getString("name"));
                parking.setLocation(rs.getString("location"));
                rs.close();
                ps.close();
                conn.close();
                return parking;
            }
        }
        return null;
    }

    public Parking getParkingById(int id) throws SQLException {
        String sql="SELECT * FROM parking WHERE idParking = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Parking parking = new Parking();
                parking.setIdParking(rs.getInt("idParking"));
                parking.setName(rs.getString("name"));
                parking.setLocation(rs.getString("location"));
                rs.close();
                ps.close();
                conn.close();
                return parking;
            }
        }
        return null;
    }

    public List<Parking> getAllParkings() throws SQLException{
        String sql = "SELECT * FROM parking ";
        List<Parking> Lparking = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Parking parking = new Parking();
                parking.setIdParking(rs.getInt("idParking"));
                parking.setName(rs.getString("name"));
                parking.setLocation(rs.getString("location"));

                Lparking.add(parking);
            }
            rs.close();
            ps.close();
        }
        return Lparking;
    }
//    public Parking getParkingByName(String name) { }
//
//
//    public boolean updateParking(Parking parking) { }
//
//
//    public boolean deleteParking(int id) { }
}
