package com.bogdanbrl.carrental.models;

public enum ECarClass {

    ECONOMY ("economy"),
    MEDIUM ("medium"),
    LUXURY ("luxury");

    private String name;

    ECarClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ECarClass convertToCarClass(String name){
        switch (name){
            case "economy" : return ECarClass.ECONOMY;
            case "medium" : return ECarClass.MEDIUM;
            case "luxury" : return ECarClass.LUXURY;
            default: throw new IllegalArgumentException("Car class [" + name + "] not supported.");
        }
    }
}
