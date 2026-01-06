package com.najim.threads;

import com.najim.Service.ParkingService;
import com.najim.model.Car;
import com.najim.model.Ticket;

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
}