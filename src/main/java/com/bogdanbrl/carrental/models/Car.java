package com.bogdanbrl.carrental.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private Integer year;
    private Double rentPrice;

    @Enumerated(EnumType.STRING)
    private ECarBody carBody;

    @Enumerated(EnumType.STRING)
    private ECarClass carClass;

    @Enumerated(EnumType.STRING)
    private ECarEngine engine;

    @Lob
    private Byte[] image;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private CarOptions carOptions;

    @OneToMany(mappedBy = "car", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private final Set<Rent> rents = new HashSet<>();

    public Car() {
    }

    public Car(String brand, String model, Integer year, Double rentPrice,
               ECarBody carBody, ECarClass carClass, ECarEngine engine,
               Byte[] image, CarOptions carOptions) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rentPrice = rentPrice;
        this.carBody = carBody;
        this.carClass = carClass;
        this.engine = engine;
        this.image = image;
        this.carOptions = carOptions;
    }

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

    public ECarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(ECarBody carBody) {
        this.carBody = carBody;
    }

    public ECarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(ECarClass carClass) {
        this.carClass = carClass;
    }

    public ECarEngine getEngine() {
        return engine;
    }

    public void setEngine(ECarEngine engine) {
        this.engine = engine;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
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
}
