package com.najim.DAO;

import com.najim.connection.DatabaseConnection;
import com.najim.model.Car;
import com.najim.model.Ticket;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public static boolean  saveTicket(Ticket tk) throws SQLException {
        String sql = "INSERT INTO Ticket (entryTime, spotType, idCar, idSpot) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, Timestamp.valueOf(tk.getEntryTime()));
            ps.setString(2, tk.getSpotType());
            ps.setInt(3, tk.getIdCar());
            ps.setInt(4, tk.getIdSpot());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    tk.setIdTicket(rs.getInt(1));
                }
                rs.close();
            }
            return true;
        }
    }
    public static Ticket getTicketById(int idtk) throws SQLException {
        String sql = "SELECT * FROM Ticket WHERE idTicket = ?";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idtk);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ticket tk = new Ticket();
                tk.setIdTicket(rs.getInt(1));
                tk.setEntryTime(rs.getTimestamp("entryTime").toLocalDateTime());
                tk.setSpotType(rs.getString(3));
                Integer idCar = rs.getInt(4);
                Integer idSpot = rs.getInt(5);

                tk.setCar(CarDAO.getCarById(idCar));
                tk.setSpot(SpotDAO.getSpotById(idSpot));
                rs.close();
                ps.close();
                conn.close();
                return tk;
            }
        }
        return null;
    }

    public static List<Ticket> getAllTickets() throws SQLException {
        String sql = "SELECT * FROM Ticket";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (rs.next()) {
                Ticket tk = new Ticket();
                tk.setIdTicket(rs.getInt(1));
                tk.setEntryTime(rs.getTimestamp("entryTime").toLocalDateTime());
                tk.setSpotType(rs.getString(3));
                Integer idCar = rs.getInt(4);
                Integer idSpot = rs.getInt(5);
                tk.setCar(CarDAO.getCarById(idCar));
                tk.setSpot(SpotDAO.getSpotById(idSpot));
                tickets.add(tk);
            }
            rs.close();
            ps.close();
            return tickets;
        }
    }
    public static boolean updateTicket(Ticket tk) throws SQLException {
        String sql = "UPDATE Ticket SET entryTime=?, spotType=?, idCar=?, idSpot=? WHERE idTicket=?";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(tk.getEntryTime()));
            ps.setString(2, tk.getSpotType());
            ps.setInt(3, tk.getIdCar());
            ps.setInt(4, tk.getIdSpot());
            ps.setInt(5, tk.getIdTicket());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return true;
            }
        }
        return false;
    }
    public static boolean deleteTicket(int idTicket) throws SQLException {
        String sql = "DELETE FROM Ticket WHERE idTicket = ?";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idTicket);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return true;
            }
        }
        return false;
    }
    public static Ticket getTicketByCarId(int carId) throws SQLException {
        String sql = "SELECT * FROM Ticket WHERE idCar = ? ORDER BY entryTime DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, carId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ticket tk = new Ticket();
                tk.setIdTicket(rs.getInt(1));
                tk.setEntryTime(rs.getTimestamp("entryTime").toLocalDateTime());
                tk.setSpotType(rs.getString(3));
                Integer idCar = rs.getInt(4);
                Integer idSpot = rs.getInt(5);
                tk.setCar(CarDAO.getCarById(idCar));
                tk.setSpot(SpotDAO.getSpotById(idSpot));

                return tk;
            }
        }
        return null;
    }

    public static Ticket getTicketBySpotId(int spotId) throws SQLException{
        String sql = "SELECT * FROM Ticket WHERE idSpot = ? ORDER BY entryTime DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, spotId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ticket tk = new Ticket();
                tk.setIdTicket(rs.getInt(1));
                tk.setEntryTime(rs.getTimestamp("entryTime").toLocalDateTime());
                tk.setSpotType(rs.getString(3));
                Integer idCar = rs.getInt(4);
                Integer idSpot = rs.getInt(5);
                tk.setCar(CarDAO.getCarById(idCar));
                tk.setSpot(SpotDAO.getSpotById(idSpot));

                return tk;
            }
        }
        return null;
    }
    public static List<Ticket> getTicketsByDateRange(LocalDateTime start, LocalDateTime end) throws SQLException{
        String sql = "SELECT * FROM Ticket WHERE entryTime BETWEEN ? AND ?";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(start));
            ps.setTimestamp(2, Timestamp.valueOf(end));
            ResultSet rs = ps.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (rs.next()) {
                Ticket tk = new Ticket();
                tk.setIdTicket(rs.getInt(1));
                tk.setEntryTime(rs.getTimestamp("entryTime").toLocalDateTime());
                tk.setSpotType(rs.getString(3));
                Integer idCar = rs.getInt(4);
                Integer idSpot = rs.getInt(5);
                tk.setCar(CarDAO.getCarById(idCar));
                tk.setSpot(SpotDAO.getSpotById(idSpot));
                tickets.add(tk);
            }
            rs.close();
            ps.close();
            return tickets;



        }
    }
    public static List<Ticket> getActiveTickets()throws SQLException{
        String sql = "SELECT t.* FROM Ticket t INNER JOIN Spot s ON t.idSpot = s.idSpot WHERE s.status = 'OCCUPIED'";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (rs.next()) {
                Ticket tk = new Ticket();
                tk.setIdTicket(rs.getInt(1));
                tk.setEntryTime(rs.getTimestamp("entryTime").toLocalDateTime());
                tk.setSpotType(rs.getString(3));
                Integer idCar = rs.getInt(4);
                Integer idSpot = rs.getInt(5);
                tk.setCar(CarDAO.getCarById(idCar));
                tk.setSpot(SpotDAO.getSpotById(idSpot));
                tickets.add(tk);
            }
            rs.close();
            ps.close();
            return tickets;


        }
    }
    public static Integer countTotalTickets() throws SQLException{
        String sql = "SELECT COUNT(*) FROM Ticket";
        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer count = rs.getInt(1);
                rs.close();
                ps.close();
                return count;
            }
        }
        return 0;
    }
}
