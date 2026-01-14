package com.najim.synchronization;

import com.najim.DAO.TicketDAO;
import com.najim.Service.ParkingService;

public class ParkingQueue {

    private int totalSpots;
    private int occupiedSpots;

    public ParkingQueue(int tl)  {
        this.totalSpots = tl;
        try {
            this.occupiedSpots = TicketDAO.getActiveTickets().size();
        } catch (Exception e) {
            this.occupiedSpots = 0;
        }
    }

    public  synchronized void waitForSpot() throws InterruptedException {
        while (occupiedSpots >= totalSpots) {
            System.out.println("Parking is FULL! Waiting for a spot...");

            wait();
        }
        occupiedSpots++;
        System.out.println("Got a spot! Occupied: " + occupiedSpots + "/" + totalSpots);
    }

    public synchronized void releasePendingSpot() {
        if (occupiedSpots > 0) {
            occupiedSpots--;
            System.out.println("Spot released! Occupied: " + occupiedSpots + "/" + totalSpots);
            notifyAll();
        } else {
            System.out.println("WARNING: Trying to release spot when none occupied!");
        }
    }

    public synchronized void spotFreed() {
        if (occupiedSpots > 0) {
            occupiedSpots--;
            System.out.println("Spot freed! Occupied: " + occupiedSpots + "/" + totalSpots);
            notifyAll();
        } else {
            System.out.println("WARNING: Trying to free spot when none occupied!");
        }
    }

    public synchronized boolean isFull() {
        return occupiedSpots >= totalSpots;
    }
}