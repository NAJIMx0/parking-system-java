package com.najim.test;

import com.najim.DAO.FloorDAO;
import com.najim.DAO.ParkingDAO;
import com.najim.model.Floor;
import com.najim.model.Parking;

import java.sql.SQLException;
import java.util.List;

public class TestFloorDAO {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    TESTING FloorDAO");
        System.out.println("========================================\n");

        try {
            // SETUP: Create a parking first
            System.out.println("📋 SETUP: Creating parking...");
            Parking parking = new Parking("Test Parking", "Test Location");
            ParkingDAO.saveParking(parking);
            System.out.println("✅ Parking created with ID: " + parking.getIdParking());
            System.out.println();

            // TEST 1: Save a floor
            System.out.println("TEST 1: Save Floor");
            System.out.println("------------------");
            Floor floor1 = new Floor(1, parking.getIdParking());
            boolean saved = FloorDAO.saveFloor(floor1);
            if (saved && floor1.getIdFloor() != null) {
                System.out.println("✅ Floor saved!");
                System.out.println("   ID: " + floor1.getIdFloor());
                System.out.println("   Floor Number: " + floor1.getFloorNumber());
                System.out.println("   Parking ID: " + floor1.getIdParking());
            } else {
                System.out.println("❌ Failed to save floor!");
            }
            System.out.println();

            // TEST 2: Get floor by ID (with Parking object loaded)
            System.out.println("TEST 2: Get Floor By ID");
            System.out.println("-----------------------");
            Floor foundById = FloorDAO.getFloorById(floor1.getIdFloor());
            if (foundById != null) {
                System.out.println("✅ Found floor: " + foundById);
                if (foundById.getParking() != null) {
                    System.out.println("   ✅ Parking loaded: " + foundById.getParking().getName());
                } else {
                    System.out.println("   ❌ Parking is NULL!");
                }
            } else {
                System.out.println("❌ Floor not found!");
            }
            System.out.println();

            // TEST 3: Save more floors
            System.out.println("TEST 3: Save Multiple Floors");
            System.out.println("----------------------------");
            Floor floor2 = new Floor(2, parking.getIdParking());
            Floor floor3 = new Floor(3, parking.getIdParking());
            FloorDAO.saveFloor(floor2);
            FloorDAO.saveFloor(floor3);
            System.out.println("✅ Created 2 more floors");
            System.out.println();

            // TEST 4: Get all floors
            System.out.println("TEST 4: Get All Floors");
            System.out.println("----------------------");
            List<Floor> allFloors = FloorDAO.getAllFloors();
            System.out.println("✅ Total floors: " + allFloors.size());
            for (Floor f : allFloors) {
                System.out.println("   - Floor " + f.getFloorNumber() + " (ID: " + f.getIdFloor() + ")");
            }
            System.out.println();

            // TEST 5: Get floors by parking ID
            System.out.println("TEST 5: Get Floors By Parking ID");
            System.out.println("--------------------------------");
            List<Floor> floorsByParking = FloorDAO.getFloorsByParkingId(parking.getIdParking());
            System.out.println("✅ Floors in this parking: " + floorsByParking.size());
            for (Floor f : floorsByParking) {
                System.out.println("   - Floor " + f.getFloorNumber());
            }
            System.out.println();

            // TEST 6: Update floor
            System.out.println("TEST 6: Update Floor");
            System.out.println("--------------------");
            floor1.setFloorNumber(10);
            boolean updated = FloorDAO.updateFloor(floor1);
            if (updated) {
                System.out.println("✅ Floor updated!");
                Floor verify = FloorDAO.getFloorById(floor1.getIdFloor());
                System.out.println("   New floor number: " + verify.getFloorNumber());
            } else {
                System.out.println("❌ Failed to update!");
            }
            System.out.println();

            // TEST 7: Delete floor
            System.out.println("TEST 7: Delete Floor");
            System.out.println("--------------------");
            boolean deleted = FloorDAO.deleteFloor(floor1.getIdFloor());
            if (deleted) {
                System.out.println("✅ Floor deleted!");
            } else {
                System.out.println("❌ Failed to delete!");
            }
            System.out.println();

            System.out.println("========================================");
            System.out.println("    ALL TESTS COMPLETED!");
            System.out.println("========================================");

        } catch (SQLException e) {
            System.err.println("❌ TEST FAILED!");
            e.printStackTrace();
        }
    }
}