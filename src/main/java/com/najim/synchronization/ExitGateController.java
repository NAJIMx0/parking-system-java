package com.najim.synchronization;

import com.najim.Service.ParkingService;
import com.najim.model.Payment;

import java.util.concurrent.Semaphore;

public class ExitGateController {

    private Semaphore exitGates = new Semaphore(2);

    public void exitThroughGate(String plateNumber,String paymentMethod) throws Exception {

        System.out.println("Car " + plateNumber + " waiting for exit gate...");

        exitGates.acquire();

        try {
            System.out.println("Car " + plateNumber + " exiting through gate...");
            Payment py = ParkingService.exitCar(plateNumber, paymentMethod);
            if (py != null) {
                System.out.println("Car " + plateNumber + " EXITED! Fee: " +
                        String.format("%.2f", py.getAmount())+" DH");
            } else {
                System.out.println("Car " + plateNumber + " exit FAILED");
            }
            System.out.println("Car " + plateNumber + " passed through exit gate!");

        } finally {
            exitGates.release();
        }
    }
}