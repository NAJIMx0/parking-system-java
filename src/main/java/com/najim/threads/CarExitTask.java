package com.najim.threads;

import com.najim.Service.ParkingService;
import com.najim.model.Payment;
import com.najim.synchronization.ExitGateController;

public class CarExitTask implements Runnable {

    private static final ExitGateController exitGateController = new ExitGateController();

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

            if (py != null) {
                System.out.println("Car " + platnumber + " EXITED! Fee: " +
                        String.format("%.2f", py.getAmount())+" DH");

                exitGateController.exitThroughGate(platnumber);
            } else {
                System.out.println("Car " + platnumber + " exit FAILED");
            }
//            System.out.println("Car " + platnumber + " EXITED! Fee: " + py.getAmount()+" DH");
        }catch (Exception e){
            System.out.println("Car Exit Task Error"+e.getMessage());
        }
    }
}
