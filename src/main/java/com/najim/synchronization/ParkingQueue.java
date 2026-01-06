package com.najim.synchronization;

public class ParkingQueue {

    private int totalSpots;
    private int occupiedSpots;

    public ParkingQueue(int totalSpots) {
        this.totalSpots = totalSpots;
        this.occupiedSpots = 0;
    }

    public synchronized void waitForSpot() throws InterruptedException {
        while (occupiedSpots >= totalSpots) {
            System.out.println("Parking is FULL! Waiting for a spot...");

            wait();
        }
        occupiedSpots++;
        System.out.println("Got a spot! Occupied: " + occupiedSpots + "/" + totalSpots);
    }

    public synchronized void SpoteFree() {
        occupiedSpots--;
        System.out.println("Spot free for parking: " + occupiedSpots + "/" + totalSpots);
        notifyAll();
    }
    public synchronized boolean isFull() {
        return occupiedSpots >= totalSpots;
    }
}
