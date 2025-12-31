package com.najim.model;

public class Parking {
    private int idParking;
    private String name;
    private String location;

    public Parking() {
    }

    public Parking(String name, String location) {
        this.name = name;
        this.location = location;
    }
//test
    public int getIdParking() {
        return idParking;
    }

    public void setIdParking(int idParking) {
        this.idParking = idParking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "idParking=" + idParking +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}