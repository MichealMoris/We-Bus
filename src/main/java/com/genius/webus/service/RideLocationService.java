package com.genius.webus.service;

import com.genius.webus.entity.RideLocation;
import com.genius.webus.error.NotFoundException;

import java.util.List;

public interface RideLocationService {
    public List<RideLocation> getAllRideLocations();
    public RideLocation addRideLocation(RideLocation rideLocation);
    public RideLocation getRideLocationByID(Long id) throws NotFoundException;
    public void deleteRideLocationByID(Long rideLocationID) throws NotFoundException;
}
