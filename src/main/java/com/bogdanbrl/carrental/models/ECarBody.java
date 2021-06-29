package com.bogdanbrl.carrental.models;

public enum ECarBody {

    SEDAN ("sedan"),
    COUPE ("coupe"),
    SPORTS_CAR ("sport car"),
    STATION_WAGON ("wagon"),
    HATCHBACK ("hatchback"),
    CONVERTIBLE ("convertible"),
    SPORT_UTILITY_VEHICLE ("SUV"),
    MINIVAN ("minivan"),
    PICKUP ("pickup");

    private String name;

    ECarBody(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ECarBody convertToCarBody(String name){
        switch (name){
            case "sedan" : return ECarBody.SEDAN;
            case "coupe" : return ECarBody.COUPE;
            case "sport car" : return ECarBody.SPORTS_CAR;
            case "wagon" : return ECarBody.STATION_WAGON;
            case "hatchback" : return ECarBody.HATCHBACK;
            case "convertible" : return ECarBody.CONVERTIBLE;
            case "SUV" : return ECarBody.SPORT_UTILITY_VEHICLE;
            case "minivan" : return ECarBody.MINIVAN;
            case "pickup" : return ECarBody.PICKUP;
            default: throw new IllegalArgumentException("Car body [" + name + "] not supported.");
        }
    }
}
