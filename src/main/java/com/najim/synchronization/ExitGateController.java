package com.najim.synchronization;

import java.util.concurrent.Semaphore;

public class ExitGateController {

    private Semaphore exitGates = new Semaphore(2);

    public void exitThroughGate(String plateNumber) throws InterruptedException {

        System.out.println("Car " + plateNumber + " waiting for exit gate...");

        exitGates.acquire();

        try {
            System.out.println("Car " + plateNumber + " exiting through gate...");
            Thread.sleep(2000);
            System.out.println("Car " + plateNumber + " passed through exit gate!");

        } finally {
            exitGates.release();
        }
    }
}