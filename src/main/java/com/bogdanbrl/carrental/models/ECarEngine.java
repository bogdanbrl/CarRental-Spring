package com.bogdanbrl.carrental.models;

public enum ECarEngine {

    GASOLINE ("gasoline"),
    DIESEL ("diesel"),
    HYBRID ("hybrid"),
    ELECTRIC ("electric");

    private String name;

    ECarEngine(String name) {this.name = name;}

    public String getName() {
        return name;
    }

    public static ECarEngine convertToEngine(String name){
        switch (name){
            case "gasoline" : return ECarEngine.GASOLINE;
            case "diesel" : return ECarEngine.DIESEL;
            case "hybrid" : return ECarEngine.HYBRID;
            case "electric" : return ECarEngine.ELECTRIC;
            default: throw new IllegalArgumentException("Engine [" + name + "] not supported");
        }
    }
}
