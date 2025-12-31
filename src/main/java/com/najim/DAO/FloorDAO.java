package com.najim.DAO;

import com.najim.connection.DatabaseConnection;
import com.najim.model.Car;
import com.najim.model.Floor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class FloorDAO {

//    public boolean saveFloor(Floor floor) { }
//
//
//    public Floor getFloorById(int id) { }
//    public List<Floor> getAllFloors() { }
//    public List<Floor> getFloorsByParkingId(int parkingId) {
//        String sql="SELECT * FROM floor WHERE plateNumber = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, plateNumber);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                Car Ocar = new Car();
//                Ocar.setIdCar(rs.getInt("idCar"));
//                Ocar.setPlateNumber(rs.getString("plateNumber"));
//                Ocar.setColor(rs.getString("color"));
//                rs.close();
//                ps.close();
//                conn.close();
//                return Ocar;
//            }
//        }
//        return null;
//
//    }
//public Floor getFloorById(int id) {
//    if (rs.next()) {
//        floor = new Floor();
//        floor.setIdFloor(rs.getInt("idFloor"));
//        floor.setFloorNumber(rs.getInt("floorNumber"));
//
//        // Load the full Parking object
//        int parkingId = rs.getInt("idParking");
//        Parking parking = parkingDAO.getParkingById(parkingId);
//        floor.setParking(parking);
//    }
//}
//
//    public boolean updateFloor(Floor floor) { }
//
//
//    public boolean deleteFloor(int id) { }

}
