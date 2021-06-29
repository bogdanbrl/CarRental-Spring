package com.bogdanbrl.carrental.dto;

import com.bogdanbrl.carrental.models.CarOptions;
import com.bogdanbrl.carrental.models.Rent;

import java.util.HashSet;
import java.util.Set;

public class CarDTO {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private Double rentPrice;
    private String carBody;
    private String carClass;
    private String engine;
    private String image;
    private CarOptions carOptions;
    private Set<Rent> rents = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getCarBody() {
        return carBody;
    }

    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CarOptions getCarOptions() {
        return carOptions;
    }

    public void setCarOptions(CarOptions carOptions) {
        this.carOptions = carOptions;
    }

    public Set<Rent> getRents() {
        return rents;
    }

    public void setRents(Set<Rent> rents) {
        this.rents = rents;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", rentPrice=" + rentPrice +
                ", carBody='" + carBody + '\'' +
                ", carClass='" + carClass + '\'' +
                ", engine='" + engine + '\'' +
                ", image='" + image + '\'' +
                ", carOptions=" + carOptions +
                ", rents=" + rents +
                '}';
    }
}
