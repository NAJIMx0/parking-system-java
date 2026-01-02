package com.najim.DAO;

import com.najim.connection.DatabaseConnection;
import com.najim.model.Floor;
import com.najim.model.Spot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpotDAO {
     public static boolean saveSpot(Spot spot) throws Exception {
         if (spot.getIdFloor() == null || spot.getIdFloor() <= 0) {
             System.out.println("Error: Floor ID cannot be null or invalid");
             return false;
         }

         FloorDAO floorDAO = new FloorDAO();
         Floor existingFloor = floorDAO.getFloorById(spot.getIdFloor());

         if (existingFloor == null) {
             System.out.println("Error: Floor with ID " + spot.getIdFloor() + " does not exist");
             return false;
         }
        String sql = "INSERT INTO spot (spotNumber, status, type, idFloor) VALUES (?, ?, ?, ?)";

         try (Connection conn = DatabaseConnection.getConnection();
              PreparedStatement ps = conn.prepareStatement(sql)){
             ps.setString(1, spot.getSpotNumber());
             ps.setString(2, spot.getStatus());
             ps.setString(3, spot.getType());
             ps.setInt(4, spot.getIdFloor());
             int row = ps.executeUpdate();
             if (row > 0){
                 System.out.println("spot saved successful");
                 return true;
             }else{
                 System.out.println("spot save failed");
                 return false;
             }
         }
     }

    public static Spot getSpotById(Integer id) throws SQLException {
        String sql="SELECT * FROM Spot WHERE idSpot = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Spot Ospot = new Spot();
                Ospot.setIdSpot(rs.getInt("idSpot"));
                Ospot.setSpotNumber(rs.getString("spotNumber"));
                Ospot.setStatus(rs.getString("status"));
                Ospot.setType(rs.getString("type"));
                Integer idfloor = rs.getInt("idFloor");
                Ospot.setFloor(FloorDAO.getFloorById(idfloor));
                rs.close();
                ps.close();
                conn.close();
                return Ospot;
            }
        }
        return null;
    }

     public static List<Spot> getAllSpots() throws Exception {
         String sql = "SELECT * FROM spot";
         List<Spot> spots = new ArrayList<>();

         try(Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                 Spot spot = new Spot();
                 spot.setSpotNumber(rs.getString("spotNumber"));
                 spot.setStatus(rs.getString("status"));
                 spot.setType(rs.getString("type"));

                 spots.add(spot);
             }
         }
         return spots;
     }

     public static List<Spot> getSpotsByFloorId(int floorId) throws Exception {
         String sql = "SELECT * FROM spot WHERE floorId = ?";
         List<Spot> spots = new ArrayList<>();

         try(Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
             ps.setInt(1, floorId);
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                 Spot spot = new Spot();
                 spot.setSpotNumber(rs.getString("spotNumber"));
                 spot.setStatus(rs.getString("status"));
                 spot.setType(rs.getString("type"));

                 spots.add(spot);
             }
         }
         return spots;
     }

    public static List<Spot> getFreeSpots() throws SQLException {
         String sql = "SELECT * FROM spot WHERE status = ?";
         List<Spot> spots = new ArrayList<>();

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, "FREE");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Spot spot = new Spot();
                spot.setIdSpot(rs.getInt("idSpot"));
                spot.setSpotNumber(rs.getString("spotNumber"));
                spot.setStatus(rs.getString("status"));
                spot.setType(rs.getString("type"));
                spot.setIdFloor(rs.getInt("idFloor"));

                spots.add(spot);
            }
            rs.close();
        }
        return spots;
    }

    public static List<Spot> getFreeSpotsByType(String type) throws Exception {
         String sql = "SELECT * FROM spot WHERE status = ? AND type = ?";
         List<Spot> spots = new ArrayList<>();

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, "FREE");
            ps.setString(2, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Spot spot = new Spot();
                spot.setIdSpot(rs.getInt("idSpot"));
                spot.setSpotNumber(rs.getString("spotNumber"));
                spot.setStatus(rs.getString("status"));
                spot.setType(rs.getString("type"));
                spot.setIdFloor(rs.getInt("idFloor"));

                spots.add(spot);
            }
        }
        return spots;
    }

    public static boolean updateSpot(Spot spot) throws Exception {
         String sql = "UPDATE spot SET spotNumber = ?, status = ?, type = ? WHERE idSpot = ?";

         try(Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
             ps.setString(1, spot.getSpotNumber());
             ps.setString(2, spot.getStatus());
             ps.setString(3, spot.getType());
             ps.setInt(4, spot.getIdSpot());
             int rs = ps.executeUpdate();
             if (rs > 0){
                 System.out.println("Spot updated successfuly");
                 return true;
             }else{
                 System.out.println("UPDATE spot failed");
                 return false;
             }
         }
    }

    public static boolean updateSpotStatus(int spotId, String status) throws Exception {
         String sql =  "UPDATE spot SET status = ? WHERE idSpot = ?";

         try(Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
             ps.setString(1, status);
             ps.setInt(2, spotId);
             int rs = ps.executeUpdate();
             if (rs > 0){
                 System.out.println("Spot status updated successfuly");
                 return true;
             }else{
                 System.out.println("UPDATE spot status failed");
                 return false;
             }
         }
    }

    public static boolean deleteSpot(int id) throws SQLException {
        String sql = "DELETE FROM spot WHERE idSpot = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("Spot id: " + id + " deleted successfully");
                return true;
            } else {
                System.out.println("Spot id: " + id + " not deleted successfully");
                return false;
            }
        }
    }

     public static int countFreeSpots() throws SQLException {
         String sql = "SELECT COUNT(*) FROM spot WHERE status = ?";

         try(Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
             ps.setString(1, "FREE");
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                int count = rs.getInt(1);
                return count;
             }
         }
        return 0;
     }


}
