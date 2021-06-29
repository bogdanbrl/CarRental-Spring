package com.bogdanbrl.carrental.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "caroptions")
public class CarOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean hasHeatedSeats;
    private Boolean hasNavigation;

    @OneToMany(mappedBy = "carOptions")
    @JsonBackReference
    private final Set<Car> car = new HashSet<>();

    public CarOptions() {
    }

    public CarOptions(Boolean hasHeatedSeats, Boolean hasNavigation) {
        this.hasHeatedSeats = hasHeatedSeats;
        this.hasNavigation = hasNavigation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getHasHeatedSeats() {
        return hasHeatedSeats;
    }

    public void setHasHeatedSeats(Boolean hasHeatedSeats) {
        this.hasHeatedSeats = hasHeatedSeats;
    }

    public Boolean getHasNavigation() {
        return hasNavigation;
    }

    public void setHasNavigation(Boolean hasNavigation) {
        this.hasNavigation = hasNavigation;
    }

    public Set<Car> getCar() {
        return car;
    }

    @Override
    public String toString() {
        return "CarOptions{" +
                "id=" + id +
                ", hasHeatedSeats=" + hasHeatedSeats +
                ", hasNavigation=" + hasNavigation +
                ", car=" + car +
                '}';
    }
}
