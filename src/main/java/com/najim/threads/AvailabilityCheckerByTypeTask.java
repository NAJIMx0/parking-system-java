package com.najim.threads;

//LED screen at entrance:
//"PARKING FULL" or "47 SPOTS AVAILABLE"

import com.najim.Service.ParkingService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class AvailabilityCheckerByTypeTask implements Callable<List<Integer>> {

    @Override
    public List<Integer> call() throws Exception {
        List<Integer> res = new ArrayList<>();
        Integer countVIP = ParkingService.CountAvaibleSpotsByType("VIP");
        Integer countREGULAR = ParkingService.CountAvaibleSpotsByType("REGULAR");
        res.add(countVIP);// index 1
        res.add(countREGULAR);
        return res;
    }
//    Future<Integer> future = executor.submit(new AvailabilityCheckerTask());
//    Integer revenue = future.get();
}
