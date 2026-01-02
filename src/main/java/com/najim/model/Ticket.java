package com.najim.model;

import java.time.LocalDateTime;  // ✅ Import this!

public class Ticket {
    private Integer idTicket;
    private LocalDateTime entryTime;  // ✅ Changed from Time to LocalDateTime
    private String spotType;
    private Car car;
    private Spot spot;

    public Ticket() {
    }

    public Ticket(LocalDateTime entryTime, String spotType, Integer idCar, Integer idSpot) {
        this.entryTime = entryTime;
        this.spotType = spotType;
        this.car = new Car();
        this.car.setIdCar(idCar);
        this.spot = new Spot();
        this.spot.setIdSpot(idSpot);
    }

    public Ticket(LocalDateTime entryTime, String spotType, Car car, Spot spot) {
        this.entryTime = entryTime;
        this.spotType = spotType;
        this.car = car;
        this.spot = spot;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public LocalDateTime getEntryTime() {  // ✅ Changed return type
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {  // ✅ Changed parameter type
        this.entryTime = entryTime;
    }

    public String getSpotType() {
        return spotType;
    }

    public void setSpotType(String spotType) {
        this.spotType = spotType;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Integer getIdCar() {
        return car != null ? car.getIdCar() : null;
    }

    public Integer getIdSpot() {
        return spot != null ? spot.getIdSpot() : null;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", entryTime=" + entryTime +
                ", spotType='" + spotType + '\'' +
                ", car=" + (car != null ? car.getPlateNumber() : "null") +
                ", spot=" + (spot != null ? spot.getSpotNumber() : "null") +
                '}';
    }
}