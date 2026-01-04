package com.najim.DAO;

import com.najim.connection.DatabaseConnection;
import com.najim.model.Payment;
import com.najim.model.Ticket;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public static boolean savePayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO Payment (amount, paymentTime, paymentMethod, idTicket) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, payment.getAmount());
            ps.setTimestamp(2, Timestamp.valueOf(payment.getPaymentTime()));
            ps.setString(3, payment.getPaymentMethod());
            ps.setInt(4, payment.getIdTicket());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    payment.setIdPayment(rs.getInt(1));
                }
                rs.close();
            }
            return rows > 0;
        }
    }

    public static Payment getPaymentById(int id) throws SQLException {
        String sql = "SELECT * FROM Payment WHERE idPayment = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Payment payment = new Payment();

                payment.setIdPayment(rs.getInt("idPayment"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentTime(rs.getTimestamp("paymentTime").toLocalDateTime());
                payment.setPaymentMethod(rs.getString("paymentMethod"));

                int ticketId = rs.getInt("idTicket");
                Ticket ticket = TicketDAO.getTicketById(ticketId);
                payment.setTicket(ticket);

                rs.close();
                return payment;
            }
        }
        return null;
    }

    public static List<Payment> getAllPayments() throws SQLException {
        String sql = "SELECT * FROM Payment";
        List<Payment> payments = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setIdPayment(rs.getInt("idPayment"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentTime(rs.getTimestamp("paymentTime").toLocalDateTime());
                payment.setPaymentMethod(rs.getString("paymentMethod"));

                int ticketId = rs.getInt("idTicket");
                Ticket ticket = TicketDAO.getTicketById(ticketId);
                payment.setTicket(ticket);

                payments.add(payment);
            }
            rs.close();
        }
        return payments;
    }

    public static Payment getPaymentByTicketId(int ticketId) throws SQLException {
        String sql = "SELECT * FROM Payment WHERE idTicket = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ticketId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Payment payment = new Payment();
                payment.setIdPayment(rs.getInt("idPayment"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentTime(rs.getTimestamp("paymentTime").toLocalDateTime());
                payment.setPaymentMethod(rs.getString("paymentMethod"));

                Ticket ticket = TicketDAO.getTicketById(ticketId);
                payment.setTicket(ticket);

                rs.close();
                return payment;
            }
        }
        return null;
    }

    public static List<Payment> getPaymentsByMethod(String paymentMethod) throws SQLException {
        String sql = "SELECT * FROM Payment WHERE paymentMethod = ?";
        List<Payment> payments = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, paymentMethod);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setIdPayment(rs.getInt("idPayment"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentTime(rs.getTimestamp("paymentTime").toLocalDateTime());
                payment.setPaymentMethod(rs.getString("paymentMethod"));

                int ticketId = rs.getInt("idTicket");
                Ticket ticket = TicketDAO.getTicketById(ticketId);
                payment.setTicket(ticket);

                payments.add(payment);
            }
            rs.close();
        }
        return payments;
    }

    public static double getTotalRevenue() throws SQLException {
        String sql = "SELECT SUM(amount) FROM Payment";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double total = rs.getDouble(1);
                rs.close();
                return total;
            }
        }
        return 0.0;
    }

    public static List<Payment> getPaymentsByDateRange(LocalDateTime start, LocalDateTime end) throws SQLException {
        String sql = "SELECT * FROM Payment WHERE paymentTime BETWEEN ? AND ?";
        List<Payment> payments = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(start));
            ps.setTimestamp(2, Timestamp.valueOf(end));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setIdPayment(rs.getInt("idPayment"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentTime(rs.getTimestamp("paymentTime").toLocalDateTime());
                payment.setPaymentMethod(rs.getString("paymentMethod"));

                int ticketId = rs.getInt("idTicket");
                Ticket ticket = TicketDAO.getTicketById(ticketId);
                payment.setTicket(ticket);

                payments.add(payment);
            }
            rs.close();
        }
        return payments;
    }

    public static boolean updatePayment(Payment payment) throws SQLException {
        String sql = "UPDATE Payment SET amount=?, paymentTime=?, paymentMethod=?, idTicket=? WHERE idPayment=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, payment.getAmount());
            ps.setTimestamp(2, Timestamp.valueOf(payment.getPaymentTime()));
            ps.setString(3, payment.getPaymentMethod());
            ps.setInt(4, payment.getIdTicket());
            ps.setInt(5, payment.getIdPayment());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    public static boolean deletePayment(int id) throws SQLException {
        String sql = "DELETE FROM Payment WHERE idPayment = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

}
