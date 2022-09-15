package com.genius.webus.service;

import com.genius.webus.error.DuplicatedEntryException;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.entity.Location;

import java.util.List;

public interface LocationService {

    public List<Location> getAllLocations();
    public Location getLocationByID(Long locationID) throws NotFoundException;
    public Location addLocation(Location location) throws DuplicatedEntryException;
    public void deleteLocationByID(Long locationID) throws NotFoundException;
    public Location updateLocationByID(Long locationID, Location location) throws NotFoundException, DuplicatedEntryException;

}
