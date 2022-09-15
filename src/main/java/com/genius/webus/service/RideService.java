package com.genius.webus.service;

import com.genius.webus.entity.Ride;
import com.genius.webus.error.DuplicatedEntryException;
import com.genius.webus.error.NotFoundException;

import java.util.List;

public interface RideService {

    public List<Ride> getAllRides();
    public Ride getRideByID(Long rideID) throws NotFoundException;
    public Ride addRide(Ride ride) throws DuplicatedEntryException;
    public void deleteRideByID(Long rideId) throws NotFoundException;
    public Ride updateRideByID(Long rideId, Ride ride) throws NotFoundException, DuplicatedEntryException;

}
