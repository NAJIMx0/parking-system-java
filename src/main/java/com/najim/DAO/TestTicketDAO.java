package com.najim.DAO;

import com.najim.DAO.*;
import com.najim.model.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TestTicketDAO {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    TESTING TicketDAO");
        System.out.println("========================================\n");

        try {
            // SETUP: Create all required data
            System.out.println("📋 SETUP: Creating test data...");

            // Create parking
            Parking parking = new Parking("Test Parking", "Test Location");
            ParkingDAO.saveParking(parking);
            System.out.println("✅ Parking ID: " + parking.getIdParking());

            // Create floor
            Floor floor = new Floor(1, parking.getIdParking());
            FloorDAO.saveFloor(floor);
            System.out.println("✅ Floor ID: " + floor.getIdFloor());

            // Create spots
            Spot spot1 = new Spot("A1", "FREE", "REGULAR", floor);
            Spot spot2 = new Spot("A2", "FREE", "REGULAR", floor);
            Spot spot3 = new Spot("A3", "OCCUPIED", "REGULAR", floor);
            SpotDAO.saveSpot(spot1);
            SpotDAO.saveSpot(spot2);
            SpotDAO.saveSpot(spot3);
            System.out.println("✅ Created 3 spots");

            // Create cars
            Car car1 = new Car("TEST-001112", "Red");
            Car car2 = new Car("TEST-00224", "Blue");
            CarDAO.saveCar(car1);
            CarDAO.saveCar(car2);
            System.out.println("✅ Created 2 cars");
            System.out.println();

            // TEST 1: Save a ticket
            System.out.println("TEST 1: Save Ticket");
            System.out.println("-------------------");
            LocalDateTime now = LocalDateTime.now();
            Ticket ticket1 = new Ticket(now, "REGULAR", car1.getIdCar(), spot1.getIdSpot());
            boolean saved = TicketDAO.saveTicket(ticket1);
            if (saved ) {
                System.out.println("✅ Ticket saved!");
                System.out.println("   ID: " + ticket1.getIdTicket());
                System.out.println("   Entry Time: " + ticket1.getEntryTime());
                System.out.println("   Spot Type: " + ticket1.getSpotType());
                System.out.println("   Car ID: " + ticket1.getIdCar());
                System.out.println("   Spot ID: " + ticket1.getIdSpot());
            } else {
                System.out.println("❌ Failed to save ticket!");
            }
            System.out.println();

            // TEST 2: Get ticket by ID (with Car and Spot loaded)
            System.out.println("TEST 2: Get Ticket By ID");
            System.out.println("------------------------");
            Ticket foundById = TicketDAO.getTicketById(ticket1.getIdTicket());
            if (foundById != null) {
                System.out.println("✅ Found ticket: ID " + foundById.getIdTicket());
                System.out.println("   Entry Time: " + foundById.getEntryTime());

                if (foundById.getCar() != null) {
                    System.out.println("   ✅ Car loaded: " + foundById.getCar().getPlateNumber());
                } else {
                    System.out.println("   ❌ Car is NULL!");
                }

                if (foundById.getSpot() != null) {
                    System.out.println("   ✅ Spot loaded: " + foundById.getSpot().getSpotNumber());
                } else {
                    System.out.println("   ❌ Spot is NULL!");
                }
            } else {
                System.out.println("❌ Ticket not found!");
            }
            System.out.println();

            // TEST 3: Save another ticket for the same car
            System.out.println("TEST 3: Save Multiple Tickets for Same Car");
            System.out.println("------------------------------------------");
            Ticket ticket2 = new Ticket(now.plusHours(1), "REGULAR", car1.getIdCar(), spot2.getIdSpot());
            TicketDAO.saveTicket(ticket2);
            System.out.println("✅ Created second ticket for car " + car1.getPlateNumber());
            System.out.println();

            // TEST 4: Get ticket by car ID (should return MOST RECENT)
            System.out.println("TEST 4: Get Ticket By Car ID");
            System.out.println("----------------------------");
            Ticket foundByCar = TicketDAO.getTicketByCarId(car1.getIdCar());
            if (foundByCar != null) {
                System.out.println("✅ Found most recent ticket:");
                System.out.println("   Ticket ID: " + foundByCar.getIdTicket());
                System.out.println("   Entry Time: " + foundByCar.getEntryTime());
                System.out.println("   Spot: " + foundByCar.getSpot().getSpotNumber());
            } else {
                System.out.println("❌ Ticket not found!");
            }
            System.out.println();

            // TEST 5: Get ticket by spot ID
            System.out.println("TEST 5: Get Ticket By Spot ID");
            System.out.println("-----------------------------");
            Ticket foundBySpot = TicketDAO.getTicketBySpotId(spot1.getIdSpot());
            if (foundBySpot != null) {
                System.out.println("✅ Found ticket for spot " + spot1.getSpotNumber());
                System.out.println("   Car: " + foundBySpot.getCar().getPlateNumber());
            } else {
                System.out.println("❌ Ticket not found!");
            }
            System.out.println();

            // TEST 6: Get all tickets
            System.out.println("TEST 6: Get All Tickets");
            System.out.println("-----------------------");
            List<Ticket> allTickets = TicketDAO.getAllTickets();
            System.out.println("✅ Total tickets: " + allTickets.size());
            for (Ticket t : allTickets) {
                System.out.println("   - Ticket " + t.getIdTicket() +
                        ": " + t.getCar().getPlateNumber() +
                        " at " + t.getSpot().getSpotNumber());
            }
            System.out.println();

            // TEST 7: Get active tickets (spots with OCCUPIED status)
            System.out.println("TEST 7: Get Active Tickets");
            System.out.println("--------------------------");

            // First, mark spot3 as OCCUPIED and create a ticket for it
            Ticket ticket3 = new Ticket(now, "REGULAR", car2.getIdCar(), spot3.getIdSpot());
            TicketDAO.saveTicket(ticket3);

            List<Ticket> activeTickets = TicketDAO.getActiveTickets();
            System.out.println("✅ Active tickets: " + activeTickets.size());
            for (Ticket t : activeTickets) {
                System.out.println("   - Car " + t.getCar().getPlateNumber() +
                        " at spot " + t.getSpot().getSpotNumber());
            }
            System.out.println();

            // TEST 8: Get tickets by date range
            System.out.println("TEST 8: Get Tickets By Date Range");
            System.out.println("---------------------------------");
            LocalDateTime start = now.minusHours(1);
            LocalDateTime end = now.plusHours(2);
            List<Ticket> ticketsByDate = TicketDAO.getTicketsByDateRange(start, end);
            System.out.println("✅ Tickets in date range: " + ticketsByDate.size());
            System.out.println();

            // TEST 9: Count total tickets
            System.out.println("TEST 9: Count Total Tickets");
            System.out.println("---------------------------");
            Integer count = TicketDAO.countTotalTickets();
            System.out.println("✅ Total tickets in database: " + count);
            System.out.println();

            // TEST 10: Update ticket
            System.out.println("TEST 10: Update Ticket");
            System.out.println("----------------------");
            ticket1.setSpotType("VIP");
            boolean updated = TicketDAO.updateTicket(ticket1);
            if (updated) {
                System.out.println("✅ Ticket updated!");
                Ticket verify = TicketDAO.getTicketById(ticket1.getIdTicket());
                System.out.println("   New spot type: " + verify.getSpotType());
            } else {
                System.out.println("❌ Failed to update!");
            }
            System.out.println();

            // TEST 11: Delete ticket
            System.out.println("TEST 11: Delete Ticket");
            System.out.println("----------------------");
            boolean deleted = TicketDAO.deleteTicket(ticket1.getIdTicket());
            if (deleted) {
                System.out.println("✅ Ticket deleted!");
                Ticket verify = TicketDAO.getTicketById(ticket1.getIdTicket());
                System.out.println("   Verify: " + (verify == null ? "NULL (correct)" : "Still exists (error)"));
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
