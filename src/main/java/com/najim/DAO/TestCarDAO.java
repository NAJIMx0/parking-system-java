//package com.najim.DAO;
//
//import com.najim.model.Car;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class TestCarDAO {
//    public static void main(String[] args) throws SQLException {
////        CarDAO carDAO = new CarDAO();
////
////        System.out.println("=== TESTING CarDAO ===\n");
////
////////        // TEST 1: Save a car
//////        System.out.println("1. Testing saveCar()...");
////        Car car1 = new Car("BBBP-199", "Red");
//////
////        boolean saved = carDAO.saveCar(car1);
////        System.out.println(saved ? "✅ Car saved! " : "❌ Failed to save car");
////        System.out.println(car1.getIdCar());
//
//        // TEST 2: Get car by ID
////        System.out.println("\n2. Testing getCarById()...");
////        System.out.println(car1.getIdCar());
////        Car foundCar = carDAO.getCarByplateNumber(car1.getPlateNumber());
////        System.out.println(foundCar != null ? "✅ Found: " + foundCar : "❌ Car not found");
//
////        // TEST 3: Get all cars
////        System.out.println("\n3. Testing getAllCars()...");
////        List<Car> allCars = carDAO.getAllCars();
////        System.out.println("✅ Total cars: " + allCars.size());
////        for (Car c : allCars) {
////            System.out.println("   - " + c);
////        }
//
////        // TEST 4: Update car
////        System.out.println("\n4. Testing updateCar()...");
////        car1.setColor("Blue");
////        System.out.println(car1.getPlateNumber());
////        boolean updated = carDAO.updateCar(car1);
////        System.out.println(updated ? "✅ Car updated!" : "❌ Failed to update");
//
//        // TEST 5: Get by plate number
////        System.out.println("\n5. Testing getCarByPlateNumber()...");
////        Car carByPlate = carDAO.getCarByplateNumber("ABC-123");
////        System.out.println(carByPlate != null ? "✅ Found: " + carByPlate : "❌ Car not found");
//
////        // TEST 6: Delete car
////        System.out.println("\n6. Testing deleteCar()...");
////        boolean deleted = carDAO.deleteCar(car1.getPlateNumber());
////        System.out.println(deleted ? "✅ Car deleted!" : "❌ Failed to delete");
//
////        System.out.println("\n=== ALL TESTS COMPLETE ===");
//    }
//}