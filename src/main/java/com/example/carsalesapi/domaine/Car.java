package com.example.carsalesapi.domaine;

import com.example.carsalesapi.domaine.enumuration.FuelType;
import com.example.carsalesapi.domaine.enumuration.Transmission;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;

    private String model;

    private LocalDate registrationDate;

    private Double price;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    private String mileage;

    private byte[] picture;

    public Car(Long id, String make, String model, LocalDate registrationDate, Double price, FuelType fuelType, Transmission transmission, String mileage, byte[] picture) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.registrationDate = registrationDate;
        this.price = price;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.mileage = mileage;
        this.picture = picture;
    }

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
