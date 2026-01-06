package com.najim.threads;

import com.najim.Service.ParkingService;
import com.najim.model.Car;
import com.najim.model.Ticket;
import com.najim.synchronization.EntryGateController;

public class CarEntryTask implements Runnable {

    private String plateNumber;
    private String color;
    private String spotType;

    public CarEntryTask(String plateNumber, String color, String spotType) {
            this.plateNumber = plateNumber;
            this.color = color;
            this.spotType = spotType;
    }

    @Override
    public void run() {
        try {
            EntryGateController.EnterinGate(plateNumber);
            System.out.println("Car "+plateNumber+" is trying to ENTER...");
            Car car = new Car(plateNumber,color);
            Ticket tk = ParkingService.parkCar(car , spotType);
            if (tk != null) {
                System.out.println("Car "+plateNumber+" is PARKING... ");
            }else  {
                System.out.println("Car "+plateNumber+" is not parking...");
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




        public static void main(String[] args) {

            Runnable car1 = new CarEntryTask("123-ABC", "Red", "NORMAL");
            Runnable car2 = new CarEntryTask("456-DEF", "Blue", "VIP");
            Runnable car3 = new CarEntryTask("789-GHI", "Black", "NORMAL");

            // Run directly (no thread)
            car1.run();
            car2.run();
            car3.run();
        }


}