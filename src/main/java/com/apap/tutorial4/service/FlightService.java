package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;


public interface FlightService {
    FlightModel getFlightDetailById(long id);
    void addFlight(FlightModel flight);
    void deleteFlight(FlightModel flight);
}
