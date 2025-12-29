package com.najim.model;

public class Spot {
    private Integer idSpot;
    private String spotNumber;
    private String status;
    private String type;
    private Floor floor;

    public Spot() {
    }

    public Spot(String spotNumber, String status, String type, Integer idFloor) {
        this.spotNumber = spotNumber;
        this.status = status;
        this.type = type;
        this.floor = new Floor();
        this.floor.setIdFloor(idFloor);
    }

    public Spot(String spotNumber, String status, String type, Floor floor) {
        this.spotNumber = spotNumber;
        this.status = status;
        this.type = type;
        this.floor = floor;
    }

    public Integer getIdSpot() {
        return idSpot;
    }

    public void setIdSpot(Integer idSpot) {
        this.idSpot = idSpot;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Integer getIdFloor() {
        return floor != null ? floor.getIdFloor() : null;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "idSpot=" + idSpot +
                ", spotNumber='" + spotNumber + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", floor=" + (floor != null ? floor.getFloorNumber() : "null") +
                '}';
    }
}