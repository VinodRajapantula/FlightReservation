package com.flightsearch.models;

/**
 * Created by Vinod_Rajapantula on 6/15/2017.
 */


public enum FlightTypes {
    ONE_WAY("One-Way"), TWO_WAY("Return");

    String name;

    FlightTypes(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}