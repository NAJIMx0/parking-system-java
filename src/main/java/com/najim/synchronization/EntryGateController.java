package com.najim.synchronization;

import java.util.concurrent.Semaphore;

public class EntryGateController {

    private static Semaphore gates = new Semaphore(2);

    public static void EnterinGate(String platenumber) throws Exception{
        gates.acquire();
        try{
            System.out.println(" entering...");
            Thread.sleep(10000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            gates.release();
        }
    }
}
