package com.bogdanbrl.carrental.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name ="rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Car car;

    private Double rentPricePerDay;
    private LocalDate startRent;
    private LocalDate endRent;
    private Integer rentedDays;
    private Double earnings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Double getRentPricePerDay() {
        return rentPricePerDay;
    }

    public void setRentPricePerDay(Double rentPricePerDay) {
        this.rentPricePerDay = rentPricePerDay;
    }

    public LocalDate getStartRent() {
        return startRent;
    }

    public void setStartRent(LocalDate startRent) {
        this.startRent = startRent;
    }

    public LocalDate getEndRent() {
        return endRent;
    }

    public void setEndRent(LocalDate endRent) {
        this.endRent = endRent;
    }

    public Integer getRentedDays() {
        return rentedDays;
    }

    public void setRentedDays(Integer rentedDays) {
        this.rentedDays = rentedDays;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }
}
