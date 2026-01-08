package com.najim;

import com.najim.Service.ParkingService;
import com.najim.Service.PaymentService;
import com.najim.threads.*;
import java.util.Scanner;
import java.util.concurrent.*;
import com.najim.model.Spot;
import java.util.List;

import com.najim.model.Ticket;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   SMART PARKING MANAGEMENT SYSTEM      ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        boolean running = true;

        while (running) {
            showMenu();
            int choice = getChoice();

            switch (choice) {
                case 1 -> parkCar();
                case 2 -> exitCar();
                case 3 -> viewAvailableSpots();
                case 4 -> viewParkedCars();
                case 5 -> checkCarStatus();
                case 6 -> viewStatistics();
                case 7 -> viewRevenue();
                case 8 -> demoConcurrentParking();
                case 9 -> demoStressTest();
                case 10 -> {
                    System.out.println("\n👋 Thank you for using Smart Parking System!");
                    running = false;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }

        scanner.close();
    }

    // Display menu
    private static void showMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║              MAIN MENU                 ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  [1] Park a Car                        ║");
        System.out.println("║  [2] Exit Parking                      ║");
        System.out.println("║  [3] View Available Spots              ║");
        System.out.println("║  [4] View Currently Parked Cars        ║");
        System.out.println("║  [5] Check if Car is Parked            ║");
        System.out.println("║  [6] View Parking Statistics           ║");
        System.out.println("║  [7] View Revenue Report               ║");
        System.out.println("║  [8] DEMO: Concurrent Parking (10 cars)║");
        System.out.println("║  [9] DEMO: Stress Test (50 cars)       ║");
        System.out.println("║  [10] Exit System                      ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Enter choice: ");
    }

    // Get user choice
    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Option 1: Park a car
    private static void parkCar() {
        System.out.println("\n🅿️  PARK A CAR");
        System.out.println("═══════════════");

        System.out.print("Enter license plate: ");
        String plate = scanner.nextLine();

        System.out.print("Enter car color: ");
        String color = scanner.nextLine();

        System.out.print("Enter spot type (REGULAR/VIP): ");
        String type = scanner.nextLine().toUpperCase();

        // Use thread to park
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new CarEntryTask(plate, color, type));
        executor.shutdown();

        try {
            executor.awaitTermination(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("❌ Parking timeout!");
        }
    }

    // Option 2: Exit parking
    private static void exitCar() {
        System.out.println("\n🚗 EXIT PARKING");
        System.out.println("═══════════════");

        System.out.print("Enter license plate: ");
        String plate = scanner.nextLine();

        System.out.print("Enter payment method (CASH/CARD): ");
        String method = scanner.nextLine().toUpperCase();

        // Use thread to exit
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new CarExitTask(plate, method));
        executor.shutdown();

        try {
            executor.awaitTermination(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("❌ Exit timeout!");
        }
    }

    // Option 3: View available spots
    private static void viewAvailableSpots() {
        System.out.println("\n📊 AVAILABLE SPOTS");
        System.out.println("══════════════════");

        try {
            List<Spot> spots = ParkingService.findAvailableSpots();

            if (spots.isEmpty()) {
                System.out.println("❌ No available spots!");
            } else {
                System.out.println("✅ Available spots: " + spots.size());

                // Group by type
                long regular = spots.stream()
                        .filter(s -> s.getType().equals("REGULAR"))
                        .count();
                long vip = spots.stream()
                        .filter(s -> s.getType().equals("VIP"))
                        .count();

                System.out.println("   REGULAR: " + regular);
                System.out.println("   VIP: " + vip);
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Option 4: View parked cars
    private static void viewParkedCars() {
        System.out.println("\n🚗 CURRENTLY PARKED CARS");
        System.out.println("════════════════════════");

        try {
            List<Ticket> tickets = ParkingService.getCurrentlyParkedCars();

            if (tickets.isEmpty()) {
                System.out.println("📭 No cars parked!");
            } else {
                System.out.println("🅿️  Total parked: " + tickets.size() + "\n");

                for (Ticket t : tickets) {
                    System.out.println("   🚗 " + t.getCar().getPlateNumber() +
                            " | Spot: " + t.getSpot().getSpotNumber() +
                            " | Entry: " + t.getEntryTime());
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Option 5: Check car status
    private static void checkCarStatus() {
        System.out.println("\n🔍 CHECK CAR STATUS");
        System.out.println("══════════════════");

        System.out.print("Enter license plate: ");
        String plate = scanner.nextLine();

        try {
            boolean parked = ParkingService.isCarCurrentlyParked(plate);

            if (parked) {
                System.out.println("✅ Car " + plate + " is currently PARKED");
            } else {
                System.out.println("❌ Car " + plate + " is NOT parked");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Option 6: View statistics
    private static void viewStatistics() {
        System.out.println("\n📊 PARKING STATISTICS");
        System.out.println("════════════════════");

        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            // Use Callable tasks to get data concurrently!
            Future<Integer> spotsFuture = executor.submit(new AvailabilityCheckerTask());
            Future<Double> revenueFuture = executor.submit(new RevenueCalculatorTask());

            // Get results
            Integer freeSpots = spotsFuture.get();
            Double revenue = revenueFuture.get();

            System.out.println("✅ Free Spots: " + freeSpots);
            System.out.println("💰 Total Revenue: $" + String.format("%.2f", revenue));

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }

    // Option 7: View revenue
    private static void viewRevenue() {
        System.out.println("\n💰 REVENUE REPORT");
        System.out.println("═════════════════");

        try {
            double total = PaymentService.getTotalRevenue();
            double cash = PaymentService.getRevenueByMethod("CASH");
            double card = PaymentService.getRevenueByMethod("CARD");

            System.out.println("✅ Total Revenue: $" + String.format("%.2f", total));
            System.out.println("   💵 CASH: $" + String.format("%.2f", cash));
            System.out.println("   💳 CARD: $" + String.format("%.2f", card));

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Option 8: Demo concurrent parking
    private static void demoConcurrentParking() {
        System.out.println("\n🎬 DEMO: 10 CARS PARKING SIMULTANEOUSLY");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Watch how threads handle concurrent requests!\n");

        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Submit 10 cars
        for (int i = 1; i <= 10; i++) {
            String plate = "DEMO-" + String.format("%03d", i);
            String color = "Color" + i;
            String type = (i % 3 == 0) ? "VIP" : "REGULAR";

            executor.submit(new CarEntryTask(plate, color, type));
        }

        executor.shutdown();

        try {
            executor.awaitTermination(30, TimeUnit.SECONDS);
            System.out.println("\n✅ Demo complete!");
        } catch (InterruptedException e) {
            System.out.println("❌ Demo interrupted!");
        }
    }

    // Option 9: Stress test
    private static void demoStressTest() {
        System.out.println("\n🔥 STRESS TEST: 50 CARS!");
        System.out.println("═════════════════════════");
        System.out.println("Testing system under heavy load...\n");

        ExecutorService executor = Executors.newFixedThreadPool(10);

        long startTime = System.currentTimeMillis();

        // Submit 50 cars
        for (int i = 1; i <= 50; i++) {
            String plate = "STRESS-" + String.format("%03d", i);
            String color = "Color" + i;
            String type = (i % 4 == 0) ? "VIP" : "REGULAR";

            executor.submit(new CarEntryTask(plate, color, type));
        }

        executor.shutdown();

        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);

            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime) / 1000;

            System.out.println("\n✅ Stress test complete!");
            System.out.println("⏱️  Duration: " + duration + " seconds");
            System.out.println("📊 Average: " + (50.0 / duration) + " cars/second");

        } catch (InterruptedException e) {
            System.out.println("❌ Stress test interrupted!");
        }
    }
}