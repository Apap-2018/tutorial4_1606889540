package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.repository.FlightDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
    @Autowired
    private FlightDb flightDb;

    @Override
    public void addFlight(FlightModel flight){
        flightDb.save(flight);
    }

    @Override
    public void deleteFlight(FlightModel flight){
        flightDb.delete(flight);
    }

    @Override
    public FlightModel getFlightDetailById(long id){
        return flightDb.findFlightById(id);
    }
}
