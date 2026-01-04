package com.najim.DAO;

import com.najim.DAO.ParkingDAO;
import com.najim.model.Parking;

import java.sql.SQLException;
import java.util.List;

public class TestParkingDAO {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    TESTING ParkingDAO");
        System.out.println("========================================\n");

//        try {
//            // TEST 1: Save a parking
//            System.out.println("TEST 1: Save Parking");
//            System.out.println("--------------------");
            Parking parking1 = new Parking("Downtown Parking", "123 Main Street");
//            boolean saved = ParkingDAO.saveParking(parking1);
//            if (saved ) {
//                System.out.println("✅ Parking saved!");
//                System.out.println("   ID: " + parking1.getIdParking());
//                System.out.println("   Name: " + parking1.getName());
//                System.out.println("   Location: " + parking1.getLocation());
//            } else {
//                System.out.println("❌ Failed to save parking!");
//            }
//            System.out.println();

//            // TEST 2: Get parking by ID
//            System.out.println("TEST 2: Get Parking By ID");
//            System.out.println("-------------------------");
//            Parking foundById = ParkingDAO.getParkingById(1);
//            if (foundById != null) {
//                System.out.println("✅ Found: " + foundById);
//            } else {
//                System.out.println("❌ Parking not found!");
//            }
//            System.out.println();

//            // TEST 3: Get parking by name
//            System.out.println("TEST 3: Get Parking By Name");
//            System.out.println("---------------------------");
//            Parking foundByName = ParkingDAO.getParkingByName("Downtown Parking");
//            if (foundByName != null) {
//                System.out.println("✅ Found: " + foundByName);
//            } else {
//                System.out.println("❌ Parking not found!");
//            }
//            System.out.println();

//            // TEST 4: Get parking by location
//            System.out.println("TEST 4: Get Parking By Location");
//            System.out.println("-------------------------------");
//            Parking foundByLocation = ParkingDAO.getParkingByLocalization("123 Main Street");
//            if (foundByLocation != null) {
//                System.out.println("✅ Found: " + foundByLocation);
//            } else {
//                System.out.println("❌ Parking not found!");
//            }
//            System.out.println();

//            // TEST 5: Get all parkings
//            System.out.println("TEST 5: Get All Parkings");
//            System.out.println("-----------------------");
//            List<Parking> allParkings = ParkingDAO.getAllParkings();
//            System.out.println("✅ Total parkings: " + allParkings.size());
//            for (Parking p : allParkings) {
//                System.out.println("   - " + p);
//            }
//            System.out.println();

//            // TEST 6: Update parking name
//            System.out.println("TEST 6: Update Parking Name");
//            System.out.println("---------------------------");
//            parking1.setName("Updated Downtown Parking");
//            boolean updated = ParkingDAO.updateParkingName(parking1);
//            if (updated) {
//                System.out.println("✅ Parking name updated!");
//                Parking verify = ParkingDAO.getParkingById(parking1.getIdParking());
//                System.out.println("   New name: " + verify.getName());
//            } else {
//                System.out.println("❌ Failed to update!");
//            }
//            System.out.println();

//            // TEST 7: Delete parking
//            System.out.println("TEST 7: Delete Parking");
//            System.out.println("----------------------");
//            boolean deleted = ParkingDAO.deleteParking(8);
//            if (deleted) {
//                System.out.println("✅ Parking deleted!");
//                Parking verify = ParkingDAO.getParkingById(8);
//                System.out.println("   Verify: " + (verify == null ? "NULL (correct)" : "Still exists (error)"));
//            } else {
//                System.out.println("❌ Failed to delete!");
//            }
//            System.out.println();

//            System.out.println("========================================");
//            System.out.println("    ALL TESTS COMPLETED!");
//            System.out.println("========================================");

//        } catch (SQLException e) {
//            System.err.println("❌ TEST FAILED!");
//            e.printStackTrace();
//        }
    }
}