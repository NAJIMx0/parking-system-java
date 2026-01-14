package com.najim;

import com.najim.Service.ParkingService;
import com.najim.Service.PaymentService;
import com.najim.model.Spot;
import com.najim.model.Ticket;
import com.najim.threads.*;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String SEPARATOR = "================================================================================";

    public static void main(String[] args) {
        System.out.println(SEPARATOR);
        System.out.println("                     SMART PARKING MANAGEMENT SYSTEM");
        System.out.println(SEPARATOR);
        System.out.println("System Capacity: 60 spots (50 REGULAR + 10 VIP) across 2 floors");
        System.out.println(SEPARATOR);

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            System.out.println();

            try {
                switch (choice) {
                    case 1:
                        handleParkCar();
                        break;
                    case 2:
                        handleExitCar();
                        break;
                    case 3:
                        handleViewAvailableSpots();
                        break;
                    case 4:
                        handleViewParkedCars();
                        break;
                    case 5:
                        handleCheckCarStatus();
                        break;
                    case 6:
                        handleViewStatistics();
                        break;
                    case 7:
                        handleViewRevenue();
                        break;
                    case 8:
                        handleConcurrentParkingDemo();
                        break;
                    case 9:
                        handleConcurrentExitDemo();
                        break;
                    case 10:
                        handleParkingStressTest();
                        break;
                    case 11:
                        handleExitStressTest();
                        break;
                    case 0:
                        System.out.println("Shutting down system...");
                        System.out.println("Thank you for using Smart Parking Management System.");
                        running = false;
                        break;
                    default:
                        System.out.println("ERROR: Invalid choice. Please select 0-11.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                e.printStackTrace();
            }

            if (running) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("                              MAIN MENU");
        System.out.println(SEPARATOR);
        System.out.println("  1. Park a Car");
        System.out.println("  2. Exit Parking");
        System.out.println("  3. View Available Spots");
        System.out.println("  4. View Currently Parked Cars");
        System.out.println("  5. Check Car Status");
        System.out.println("  6. View Parking Statistics");
        System.out.println("  7. View Revenue Report");
        System.out.println("  8. DEMO: Concurrent Parking (10 cars entering)");
        System.out.println("  9. DEMO: Concurrent Exit (5 cars exiting)");
        System.out.println(" 10. DEMO: Parking Stress Test (100 cars)");
        System.out.println(" 11. DEMO: Exit Stress Test (50 cars)");
        System.out.println("  0. Exit System");
        System.out.println(SEPARATOR);
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Option 1: Park a car
    private static void handleParkCar() throws InterruptedException {
        System.out.println("PARK A CAR");
        System.out.println(SEPARATOR);

        String plate = getStringInput("Enter license plate: ");
        if (plate.isEmpty()) {
            System.out.println("ERROR: License plate cannot be empty.");
            return;
        }

        String color = getStringInput("Enter car color: ");
        if (color.isEmpty()) {
            color = "Unknown";
        }

        String type = getStringInput("Enter spot type (REGULAR/VIP): ").toUpperCase();
        if (!type.equals("REGULAR") && !type.equals("VIP")) {
            System.out.println("ERROR: Invalid spot type. Must be REGULAR or VIP.");
            return;
        }

        System.out.println("\nProcessing...");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new CarEntryTask(plate, color, type));
        executor.shutdown();

        boolean finished = executor.awaitTermination(20, TimeUnit.SECONDS);

        if (!finished) {
            System.out.println("ERROR: Operation timeout.");
            executor.shutdownNow();
        }
    }

    // Option 2: Exit parking
    private static void handleExitCar() throws InterruptedException {
        System.out.println("EXIT PARKING");
        System.out.println(SEPARATOR);

        String plate = getStringInput("Enter license plate: ");
        if (plate.isEmpty()) {
            System.out.println("ERROR: License plate cannot be empty.");
            return;
        }

        String method = getStringInput("Enter payment method (CASH/CARD): ").toUpperCase();
        if (!method.equals("CASH") && !method.equals("CARD")) {
            System.out.println("ERROR: Invalid payment method. Must be CASH or CARD.");
            return;
        }

        System.out.println("\nProcessing...");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new CarExitTask(plate, method));
        executor.shutdown();

        boolean finished = executor.awaitTermination(20, TimeUnit.SECONDS);

        if (!finished) {
            System.out.println("ERROR: Operation timeout.");
            executor.shutdownNow();
        }
    }

    // Option 3: View available spots
    private static void handleViewAvailableSpots() throws Exception {
        System.out.println("AVAILABLE SPOTS");
        System.out.println(SEPARATOR);

        List<Spot> spots = ParkingService.findAvailableSpots();

        if (spots.isEmpty()) {
            System.out.println("PARKING FULL - No available spots.");
        } else {
            long regularCount = spots.stream()
                    .filter(s -> "REGULAR".equals(s.getType()))
                    .count();

            long vipCount = spots.stream()
                    .filter(s -> "VIP".equals(s.getType()))
                    .count();

            System.out.println("Total Available: " + spots.size() + " spots");
            System.out.println("  - REGULAR: " + regularCount + " spots");
            System.out.println("  - VIP: " + vipCount + " spots");
        }
    }

    // Option 4: View currently parked cars
    private static void handleViewParkedCars() throws Exception {
        System.out.println("CURRENTLY PARKED CARS");
        System.out.println(SEPARATOR);

        List<Ticket> tickets = ParkingService.getCurrentlyParkedCars();

        if (tickets.isEmpty()) {
            System.out.println("No cars currently parked.");
        } else {
            System.out.println("Total Parked: " + tickets.size() + " cars\n");

            System.out.printf("%-20s %-15s %-12s %-25s%n",
                    "LICENSE PLATE", "SPOT", "TYPE", "ENTRY TIME");
            System.out.println(SEPARATOR);

            for (Ticket ticket : tickets) {
                String entryTimeStr = ticket.getEntryTime().toString();
                if (entryTimeStr.length() > 19) {
                    entryTimeStr = entryTimeStr.substring(0, 19);
                }

                System.out.printf("%-20s %-15s %-12s %-25s%n",
                        ticket.getCar().getPlateNumber(),
                        ticket.getSpot().getSpotNumber(),
                        ticket.getSpotType(),
                        entryTimeStr
                );
            }
        }
    }

    // Option 5: Check car status
    private static void handleCheckCarStatus() throws Exception {
        System.out.println("CHECK CAR STATUS");
        System.out.println(SEPARATOR);

        String plate = getStringInput("Enter license plate: ");
        if (plate.isEmpty()) {
            System.out.println("ERROR: License plate cannot be empty.");
            return;
        }

        boolean parked = ParkingService.isCarCurrentlyParked(plate);

        System.out.println("\nResult:");
        if (parked) {
            System.out.println("  Car " + plate + " is CURRENTLY PARKED");
        } else {
            System.out.println("  Car " + plate + " is NOT parked");
        }
    }

    // Option 6: View statistics
    private static void handleViewStatistics() throws Exception {
        System.out.println("PARKING STATISTICS");
        System.out.println(SEPARATOR);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> spotsFuture = executor.submit(new AvailabilityCheckerTask());
        Future<Double> revenueFuture = executor.submit(new RevenueCalculatorTask());

        Integer freeSpots = spotsFuture.get();
        Double revenue = revenueFuture.get();

        int totalCapacity = 60;
        int occupiedSpots = totalCapacity - freeSpots;
        double occupancyRate = (occupiedSpots * 100.0) / totalCapacity;

        System.out.println("Capacity Information:");
        System.out.println("  Total Capacity: " + totalCapacity + " spots");
        System.out.println("  Currently Occupied: " + occupiedSpots + " spots");
        System.out.println("  Currently Available: " + freeSpots + " spots");
        System.out.println("  Occupancy Rate: " + String.format("%.1f", occupancyRate) + "%");
        System.out.println("\nRevenue Information:");
        System.out.println("  Total Revenue: $" + String.format("%.2f", revenue));

        executor.shutdown();
    }

    // Option 7: View revenue
    private static void handleViewRevenue() throws Exception {
        System.out.println("REVENUE REPORT");
        System.out.println(SEPARATOR);

        double total = PaymentService.getTotalRevenue();
        double cash = PaymentService.getRevenueByMethod("CASH");
        double card = PaymentService.getRevenueByMethod("CARD");

        System.out.println("Revenue Breakdown:");
        System.out.println("  Total Revenue: $" + String.format("%.2f", total));
        System.out.println("  CASH Payments: $" + String.format("%.2f", cash));
        System.out.println("  CARD Payments: $" + String.format("%.2f", card));

        if (total > 0) {
            double cashPercent = (cash / total) * 100;
            double cardPercent = (card / total) * 100;
            System.out.println("\nPayment Distribution:");
            System.out.println("  CASH: " + String.format("%.1f", cashPercent) + "%");
            System.out.println("  CARD: " + String.format("%.1f", cardPercent) + "%");
        }
    }

    // Option 8: Concurrent parking demo
    private static void handleConcurrentParkingDemo() throws InterruptedException {
        System.out.println("DEMO: CONCURRENT PARKING (10 CARS ENTERING)");
        System.out.println(SEPARATOR);
        System.out.println("Demonstrating concurrent entry with 10 cars...");
        System.out.println("Entry gates: 2 (only 2 cars can enter at once)");
        System.out.println("Thread pool size: 5 threads\n");

        ExecutorService executor = Executors.newFixedThreadPool(5);

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= 10; i++) {
            String plate = "ENTRY-" + String.format("%03d", i);
            String color = "Color" + i;
            String type = (i % 3 == 0) ? "VIP" : "REGULAR";

            executor.submit(new CarEntryTask(plate, color, type));
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(60, TimeUnit.SECONDS);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("\n" + SEPARATOR);
        if (finished) {
            System.out.println("Demo completed successfully");
        } else {
            System.out.println("Demo timed out");
        }
        System.out.println("Execution time: " + duration + " milliseconds");
        System.out.println("Average time per car: " + (duration / 10) + " milliseconds");
        System.out.println(SEPARATOR);
    }

    // Option 9: Concurrent exit demo
    private static void handleConcurrentExitDemo() throws InterruptedException {
        System.out.println("DEMO: CONCURRENT EXIT (5 CARS EXITING)");
        System.out.println(SEPARATOR);
        System.out.println("This demo will exit currently parked cars concurrently");
        System.out.println("Exit gates: 2 (only 2 cars can exit at once)");
        System.out.println("Thread pool size: 3 threads\n");

        try {
            List<Ticket> parkedCars = ParkingService.getCurrentlyParkedCars();

            if (parkedCars.isEmpty()) {
                System.out.println("No cars currently parked. Please run parking demo first.");
                return;
            }

            int carsToExit = Math.min(5, parkedCars.size());
            System.out.println("Exiting " + carsToExit + " cars...\n");

            ExecutorService executor = Executors.newFixedThreadPool(3);
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < carsToExit; i++) {
                String plate = parkedCars.get(i).getCar().getPlateNumber();
                String method = (i % 2 == 0) ? "CASH" : "CARD";

                executor.submit(new CarExitTask(plate, method));
            }

            executor.shutdown();
            boolean finished = executor.awaitTermination(60, TimeUnit.SECONDS);

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("\n" + SEPARATOR);
            if (finished) {
                System.out.println("Exit demo completed successfully");
            } else {
                System.out.println("Exit demo timed out");
            }
            System.out.println("Execution time: " + duration + " milliseconds");
            System.out.println("Average time per car: " + (duration / carsToExit) + " milliseconds");
            System.out.println(SEPARATOR);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Option 10: Parking stress test - 100 CARS
    private static void handleParkingStressTest() throws InterruptedException {
        System.out.println("STRESS TEST: 100 CARS PARKING");
        System.out.println(SEPARATOR);
        System.out.println("WARNING: This will attempt to park 100 cars simultaneously.");
        System.out.println("Parking capacity: 60 spots");
        System.out.println("Expected: ~60 will park, ~40 will fail (no spots)");
        System.out.println("Thread pool size: 20 threads\n");

        String confirm = getStringInput("Continue? (yes/no): ").toLowerCase();
        if (!confirm.equals("yes") && !confirm.equals("y")) {
            System.out.println("Stress test cancelled.");
            return;
        }

        System.out.println("\nStarting parking stress test...\n");

        ExecutorService executor = Executors.newFixedThreadPool(20);

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= 100; i++) {
            String plate = "PARK-STRESS-" + String.format("%03d", i);
            String color = "Color" + i;
            String type = (i % 5 == 0) ? "VIP" : "REGULAR";

            executor.submit(new CarEntryTask(plate, color, type));
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(300, TimeUnit.SECONDS);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("\n" + SEPARATOR);
        if (finished) {
            System.out.println("Parking stress test completed");
        } else {
            System.out.println("Parking stress test timed out");
        }
        System.out.println("Total execution time: " + duration + " milliseconds");
        System.out.println("Average time per operation: " + (duration / 100) + " milliseconds");
        System.out.println("Throughput: " + String.format("%.2f", (100000.0 / duration)) + " operations/second");
        System.out.println(SEPARATOR);

        System.out.println("\nRecommendation: Check statistics (option 6) to see actual results.");
    }

    // Option 11: Exit stress test - 50 CARS (NEW!)
    private static void handleExitStressTest() throws InterruptedException {
        System.out.println("STRESS TEST: 50 CARS EXITING");
        System.out.println(SEPARATOR);
        System.out.println("WARNING: This will attempt to exit 50 parked cars simultaneously.");
        System.out.println("Exit gates: 2 (only 2 cars can exit at once)");
        System.out.println("Thread pool size: 15 threads\n");

        try {
            List<Ticket> parkedCars = ParkingService.getCurrentlyParkedCars();

            if (parkedCars.isEmpty()) {
                System.out.println("ERROR: No cars currently parked.");
                System.out.println("Please run parking stress test (option 10) first.");
                return;
            }

            int availableCars = parkedCars.size();
            int carsToExit = Math.min(50, availableCars);

            System.out.println("Currently parked cars: " + availableCars);
            System.out.println("Cars to exit: " + carsToExit);

            if (carsToExit < 50) {
                System.out.println("WARNING: Only " + carsToExit + " cars available (need 50 for full test)");
            }

            String confirm = getStringInput("\nContinue? (yes/no): ").toLowerCase();
            if (!confirm.equals("yes") && !confirm.equals("y")) {
                System.out.println("Exit stress test cancelled.");
                return;
            }

            System.out.println("\nStarting exit stress test...\n");

            ExecutorService executor = Executors.newFixedThreadPool(15);
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < carsToExit; i++) {
                String plate = parkedCars.get(i).getCar().getPlateNumber();
                String method = (i % 2 == 0) ? "CASH" : "CARD";

                executor.submit(new CarExitTask(plate, method));
            }

            executor.shutdown();
            boolean finished = executor.awaitTermination(120, TimeUnit.SECONDS);

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("\n" + SEPARATOR);
            if (finished) {
                System.out.println("Exit stress test completed");
            } else {
                System.out.println("Exit stress test timed out");
            }
            System.out.println("Cars processed: " + carsToExit);
            System.out.println("Total execution time: " + duration + " milliseconds");
            System.out.println("Average time per exit: " + (duration / carsToExit) + " milliseconds");
            System.out.println("Throughput: " + String.format("%.2f", (carsToExit * 1000.0 / duration)) + " exits/second");
            System.out.println(SEPARATOR);

            System.out.println("\nRecommendation:");
            System.out.println("- Check statistics (option 6) to verify spot availability");
            System.out.println("- Check revenue report (option 7) to see payment totals");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}