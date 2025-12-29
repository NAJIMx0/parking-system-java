package com.najim.model;

public class Floor {
    private Integer idFloor;
    private Integer floorNumber;
    private Parking parking;

    public Floor() {
    }

    public Floor(Integer floorNumber, Integer idParking) {
        this.floorNumber = floorNumber;
        this.parking = new Parking();
        this.parking.setIdParking(idParking);
    }

    public Floor(Integer floorNumber, Parking parking) {
        this.floorNumber = floorNumber;
        this.parking = parking;
    }

    public Integer getIdFloor() {
        return idFloor;
    }

    public void setIdFloor(Integer idFloor) {
        this.idFloor = idFloor;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Integer getIdParking() {
        return parking != null ? parking.getIdParking() : null;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "idFloor=" + idFloor +
                ", floorNumber=" + floorNumber +
                ", parking=" + (parking != null ? parking.getName() : "null") +
                '}';
    }
}