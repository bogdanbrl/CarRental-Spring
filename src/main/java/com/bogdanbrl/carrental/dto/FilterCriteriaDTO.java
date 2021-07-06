package com.bogdanbrl.carrental.dto;

public class FilterCriteriaDTO {

    private String brand;
    private String model;
    private String year;
    private String rentPrice;
    private String carBody;
    private String carClass;
    private String engine;
    private String heatedSeats;
    private String navigation;
    private String startRent;
    private String endRent;


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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(String rentPrice) {
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

    public String getHeatedSeats() {
        return heatedSeats;
    }

    public void setHeatedSeats(String heatedSeats) {
        this.heatedSeats = heatedSeats;
    }

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    public String getStartRent() {
        return startRent;
    }

    public void setStartRent(String startRent) {
        this.startRent = startRent;
    }

    public String getEndRent() {
        return endRent;
    }

    public void setEndRent(String endRent) {
        this.endRent = endRent;
    }

    @Override
    public String toString() {
        return "FilterCriteriaDTO{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", rentPrice='" + rentPrice + '\'' +
                ", carBody='" + carBody + '\'' +
                ", carClass='" + carClass + '\'' +
                ", engine='" + engine + '\'' +
                ", heatedSeats='" + heatedSeats + '\'' +
                ", navigation='" + navigation + '\'' +
                ", startRent='" + startRent + '\'' +
                ", endRent='" + endRent + '\'' +
                '}';
    }
}
