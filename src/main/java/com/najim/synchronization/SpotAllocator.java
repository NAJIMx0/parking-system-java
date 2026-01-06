package com.najim.synchronization;

import com.najim.DAO.SpotDAO;
import com.najim.model.Spot;

import java.util.List;

public class SpotAllocator {
   // find free spot for car and mark it occupied
    public static synchronized Spot allocateSpot(String type) throws Exception {
        List<Spot> freeSpots = SpotDAO.getFreeSpotsByType(type);

        if (freeSpots.isEmpty()) {
            return null;
        }
        Spot spot = freeSpots.get(0);

        SpotDAO.updateSpotStatus(spot.getIdSpot(), "OCCUPIED");
        return spot;
    }
}
