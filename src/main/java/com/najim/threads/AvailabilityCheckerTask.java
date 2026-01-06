package com.najim.threads;

//LED screen at entrance:
//"PARKING FULL" or "47 SPOTS AVAILABLE"

import com.najim.Service.ParkingService;

import java.util.concurrent.Callable;

public class AvailabilityCheckerTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return ParkingService.CountAvaibleSpots();
    }
//    Future<Integer> future = executor.submit(new AvailabilityCheckerTask());
//    Integer revenue = future.get();
}
