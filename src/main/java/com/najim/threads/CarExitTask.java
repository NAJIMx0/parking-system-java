package com.najim.threads;

import com.najim.Service.ParkingService;
import com.najim.model.Payment;

public class CarExitTask implements Runnable {
    private String platnumber;
    private String paymentMethod;
    public CarExitTask(String platnumber, String paymentMethod) {
        this.platnumber = platnumber;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public void run() {
        try {
            System.out.println("Car "+platnumber+" is trying to EXIT...");
            Payment py = ParkingService.exitCar(platnumber, paymentMethod);
            System.out.println("Car " + platnumber + " EXITED! Fee: " + py.getAmount()+" DH");
        }catch (Exception e){
            System.out.println("Car Exit Task Error"+e.getMessage());
        }
    }
}
