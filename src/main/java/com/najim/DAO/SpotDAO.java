package com.najim.DAO;

import com.najim.connection.DatabaseConnection;
import com.najim.model.Spot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpotDAO {
    // public boolean saveSpot(Spot spot) {}setidspot
    // public Spot getSpotById(int id) {}spot
    // public List<Spot> getAllSpots() {}list spot
    // public List<Spot> getSpotsByFloorId(int floorId) {} spot by id floor
    //public List<Spot> getFreeSpots() {} status
    //public List<Spot> getFreeSpotsByType(String type) {} free vip !?
    // public boolean updateSpot(Spot spot) {}
    //public boolean updateSpotStatus(int spotId, String status) {}
    // public boolean deleteSpot(int id) {}
    // public int countFreeSpots() {} count
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

}
