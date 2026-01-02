package com.najim.DAO;

import com.najim.connection.DatabaseConnection;
import com.najim.model.Car;
import com.najim.model.Floor;
import com.najim.model.Parking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FloorDAO {

    public static boolean saveFloor(Floor floor) throws SQLException {
        String sql = "INSERT INTO floor (floorNumber, idParking)  VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, floor.getFloorNumber());
                stmt.setInt(2, floor.getIdParking());
                int row =  stmt.executeUpdate();
                if (row > 0) {
                    System.out.println("Floor saved successfully");
                    return true;
                }else{
                    System.out.println("Floor save failed");
                    return false;
                }
        }
    }


    public static Floor getFloorById(Integer id) throws SQLException {
        String sql="SELECT * FROM Floor WHERE idFloor = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Floor Ospot = new Floor();
                Ospot.setIdFloor(rs.getInt("idFloor"));
                Ospot.setFloorNumber(rs.getInt("floorNumber"));
                Integer idp = rs.getInt("idParking");
                Ospot.setParking(ParkingDAO.getParkingById(idp));


                rs.close();
                ps.close();
                conn.close();
                return Ospot;
            }
        }
        return null;
    }


    public static List<Floor> getAllFloors() throws SQLException {
        String sql = "SELECT * FROM floor";
        List<Floor> floors = new ArrayList<>();

        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    Floor floor = new Floor();
                    floor.setIdFloor(rs.getInt("idFloor"));
                    floor.setFloorNumber(rs.getInt("floorNumber"));
                    floor.setIdParking(rs.getInt("idParking"));

                    floors.add(floor);
            }
                rs.close();
        }
        return floors;
    }

    public static List<Floor> getFloorsByParkingId(int parkingId) throws SQLException {
        String sql = "SELECT * FROM floor WHERE idParking = ?";
        List<Floor> floors = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, parkingId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    Floor floor = new Floor();
                    floor.setIdFloor(rs.getInt("idFloor"));
                    floor.setFloorNumber(rs.getInt("floorNumber"));
                    floor.setIdParking(rs.getInt("idParking"));

                    floors.add(floor);
                }
                rs.close();
        }
        return floors;
    }

//    public static Floor getFloorById(int id) throws SQLException {
//        String sql = "SELECT * FROM floor WHERE idFloor = ?";
//
//        try(Connection conn = DatabaseConnection.getConnection();
//            PreparedStatement stmt = conn.prepareStatement(sql)){
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()){
//                Floor floor = new Floor();
//                floor.setIdFloor(rs.getInt("idFloor"));
//                floor.setFloorNumber(rs.getInt("floorNumber"));
//                floor.setIdParking(rs.getInt("idParking"));
//                return floor;
//            }
//             Load the full Parking object
//            int parkingId = rs.getInt("idParking");
//            Parking parking = parkingDAO.getParkingById(parkingId);
//            floor.setParking(parking);
//        }
//        return null;
//    }
//

    public static boolean updateFloor(Floor floor)  throws SQLException {
        String sql = "UPDATE floor SET floorNumber = ? WHERE idFloor = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, floor.getFloorNumber());
            stmt.setInt(2, floor.getIdFloor());
            int row =  stmt.executeUpdate();
            if (row > 0){
                System.out.println("Floor updated successfully");
                return true;
            }else{
                System.out.println("Floor update failed");
                return false;
            }
        }
    }


   public static boolean deleteFloor(int id) throws SQLException {
        String sql = "DELETE FROM floor WHERE idFloor = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            int row =  stmt.executeUpdate();
            if (row > 0){
                System.out.println("Floor deleted successfully");
                return true;
            }else{
                System.out.println("Floor delete failed");
                return false;
            }
        }
   }

}
