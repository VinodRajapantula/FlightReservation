package com.flightsearch.models;

/**
 * Created by Vinod_Rajapantula on 6/15/2017.
 */
public class FlightBuilder {

    public static FlightModel buildOneWayFlight(){
        FlightModel flightModel = new FlightModel();
        flightModel.setType(FlightTypes.ONE_WAY);
        return flightModel;
    }
    public static FlightModel buildTwoWayFlight(){
        FlightModel flightModel = new FlightModel();
        flightModel.setType(FlightTypes.TWO_WAY);
        return flightModel;
    }
}
