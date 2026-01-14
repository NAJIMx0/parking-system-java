package com.najim.synchronization;

import java.util.concurrent.Semaphore;

public class EntryGateController {

    private Semaphore gates = new Semaphore(2);

    public void EnterinGate(String platenumber) throws Exception{
        gates.acquire();
        try{
            System.out.println("Car " + platenumber + " entering through gate...");
            Thread.sleep(2000);//normal 5 to 6 s
            System.out.println("Car " + platenumber + " passed through entry gate!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            gates.release();
        }
    }
}