package com.najim.DAO;

import com.najim.DAO.FloorDAO;
import com.najim.DAO.ParkingDAO;
import com.najim.DAO.SpotDAO;
import com.najim.model.Floor;
import com.najim.model.Parking;
import com.najim.model.Spot;

import java.sql.SQLException;
import java.util.List;

public class TestSpotDAO {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    TESTING SpotDAO");
        System.out.println("========================================\n");

        try {
            // SETUP: Create parking and floor
            System.out.println("📋 SETUP: Creating parking and floor...");
            Parking parking = new Parking("Test Parking", "Test Location");
            ParkingDAO.saveParking(parking);
            Floor floor = new Floor(1, parking.getIdParking());
            FloorDAO.saveFloor(floor);
            System.out.println("✅ Parking ID: " + parking.getIdParking());
            System.out.println("✅ Floor ID: " + floor.getIdFloor());
            System.out.println();

            // TEST 1: Save a spot
            System.out.println("TEST 1: Save Spot");
            System.out.println("-----------------");
            Spot spot1 = new Spot("A1", "FREE", "REGULAR", floor.getIdFloor());
            boolean saved = SpotDAO.saveSpot(spot1);
            if (saved && spot1.getIdSpot() != null) {
                System.out.println("✅ Spot saved!");
                System.out.println("   ID: " + spot1.getIdSpot());
                System.out.println("   Number: " + spot1.getSpotNumber());
                System.out.println("   Status: " + spot1.getStatus());
                System.out.println("   Type: " + spot1.getType());
            } else {
                System.out.println("❌ Failed to save spot!");
            }
            System.out.println();

            // TEST 2: Get spot by ID (with Floor object loaded)
            System.out.println("TEST 2: Get Spot By ID");
            System.out.println("----------------------");
            Spot foundById = SpotDAO.getSpotById(spot1.getIdSpot());
            if (foundById != null) {
                System.out.println("✅ Found spot: " + foundById);
                if (foundById.getFloor() != null) {
                    System.out.println("   ✅ Floor loaded: Floor " + foundById.getFloor().getFloorNumber());
                } else {
                    System.out.println("   ❌ Floor is NULL!");
                }
            } else {
                System.out.println("❌ Spot not found!");
            }
            System.out.println();

            // TEST 3: Save multiple spots
            System.out.println("TEST 3: Save Multiple Spots");
            System.out.println("---------------------------");
            Spot spot2 = new Spot("A2", "FREE", "REGULAR", floor.getIdFloor());
            Spot spot3 = new Spot("A3", "OCCUPIED", "REGULAR", floor.getIdFloor());
            Spot spot4 = new Spot("V1", "FREE", "VIP", floor.getIdFloor());
            SpotDAO.saveSpot(spot2);
            SpotDAO.saveSpot(spot3);
            SpotDAO.saveSpot(spot4);
            System.out.println("✅ Created 3 more spots");
            System.out.println();

            // TEST 4: Get all spots
            System.out.println("TEST 4: Get All Spots");
            System.out.println("---------------------");
            List<Spot> allSpots = SpotDAO.getAllSpots();
            System.out.println("✅ Total spots: " + allSpots.size());
            for (Spot s : allSpots) {
                System.out.println("   - " + s.getSpotNumber() + " (" + s.getStatus() + ", " + s.getType() + ")");
            }
            System.out.println();

            // TEST 5: Get free spots
            System.out.println("TEST 5: Get Free Spots");
            System.out.println("----------------------");
            List<Spot> freeSpots = SpotDAO.getFreeSpots();
            System.out.println("✅ Free spots: " + freeSpots.size());
            for (Spot s : freeSpots) {
                System.out.println("   - " + s.getSpotNumber() + " (ID: " + s.getIdSpot() + ")");
            }
            System.out.println();

            // TEST 6: Get free spots by type
            System.out.println("TEST 6: Get Free Spots By Type (VIP)");
            System.out.println("-------------------------------------");
            List<Spot> vipSpots = SpotDAO.getFreeSpotsByType("VIP");
            System.out.println("✅ Free VIP spots: " + vipSpots.size());
            for (Spot s : vipSpots) {
                System.out.println("   - " + s.getSpotNumber());
            }
            System.out.println();

            // TEST 7: Count free spots
            System.out.println("TEST 7: Count Free Spots");
            System.out.println("------------------------");
            int count = SpotDAO.countFreeSpots();
            System.out.println("✅ Total free spots: " + count);
            System.out.println();

            // TEST 8: Update spot status
            System.out.println("TEST 8: Update Spot Status");
            System.out.println("--------------------------");
            boolean statusUpdated = SpotDAO.updateSpotStatus(spot1.getIdSpot(), "OCCUPIED");
            if (statusUpdated) {
                System.out.println("✅ Spot status updated!");
                Spot verify = SpotDAO.getSpotById(spot1.getIdSpot());
                System.out.println("   New status: " + verify.getStatus());
            } else {
                System.out.println("❌ Failed to update status!");
            }
            System.out.println();

            // TEST 9: Update spot
            System.out.println("TEST 9: Update Spot");
            System.out.println("-------------------");
            spot1.setSpotNumber("A1-UPDATED");
            spot1.setStatus("FREE");
            boolean updated = SpotDAO.updateSpot(spot1);
            if (updated) {
                System.out.println("✅ Spot updated!");
                Spot verify = SpotDAO.getSpotById(spot1.getIdSpot());
                System.out.println("   New number: " + verify.getSpotNumber());
            } else {
                System.out.println("❌ Failed to update!");
            }
            System.out.println();

            // TEST 10: Delete spot
            System.out.println("TEST 10: Delete Spot");
            System.out.println("--------------------");
            boolean deleted = SpotDAO.deleteSpot(spot1.getIdSpot());
            if (deleted) {
                System.out.println("✅ Spot deleted!");
            } else {
                System.out.println("❌ Failed to delete!");
            }
            System.out.println();

            System.out.println("========================================");
            System.out.println("    ALL TESTS COMPLETED!");
            System.out.println("========================================");

        } catch (Exception e) {
            System.err.println("❌ TEST FAILED!");
            e.printStackTrace();
        }
    }
}