package com.najim.model;

public class Car {
    private int idCar;
    private String plateNumber;
    private String color;

    public Car() {
    }

    public Car(String plateNumber, String color) {
        this.plateNumber = plateNumber;
        this.color = color;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "idCar=" + idCar +
                ", plateNumber='" + plateNumber + '\'' +
                ", color='" + color + '\'' +
                '}';
    }


}