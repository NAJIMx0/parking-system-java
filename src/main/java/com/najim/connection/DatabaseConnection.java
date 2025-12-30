package com.najim.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    private static final String URL = "jdbc:mysql://localhost:3306/parking_system";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed!");
            }catch (SQLException e){
                System.out.println("Failed to close database connection!");
                e.printStackTrace();
            }
        }
    }

//    public static void testConnection() {
//        Connection conn = null;
//        try {
//            conn = getConnection();
//            if (conn != null && !conn.isClosed()) {
//                System.out.println("Database connected successfully!");
//            } else {
//                System.out.println("failed to connect to database!");
//            }
//        } catch (SQLException e) {
//            System.err.println("Connection (test) failed!");
//            e.printStackTrace();
//        } finally {
//            closeConnection(conn);
//        }
//    }


//    public static void main(String[] args) {
//        testConnection();
//    }
}