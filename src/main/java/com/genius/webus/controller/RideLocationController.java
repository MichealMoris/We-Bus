package com.genius.webus.controller;

import com.genius.webus.entity.Location;
import com.genius.webus.entity.RideLocation;
import com.genius.webus.error.DuplicatedEntryException;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.service.LocationService;
import com.genius.webus.service.RideLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rideLocations")
public class RideLocationController {

    @Autowired
    private RideLocationService rideLocationService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<RideLocation> getAllRideLocations(){

        return rideLocationService.getAllRideLocations();

    }

    @GetMapping("/{id}")
    public RideLocation getRideLocationByID(@PathVariable("id") Long id) throws NotFoundException{

        return rideLocationService.getRideLocationByID(id);

    }

    @PostMapping
    public RideLocation addRideLocation(@RequestBody RideLocation rideLocation){

        return rideLocationService.addRideLocation(rideLocation);

    }

    @PutMapping("/{rideLocationID}/location/{locationID}")
    public RideLocation addLocationToRideLocation(
            @PathVariable Long rideLocationID,
            @PathVariable Long locationID
    ) throws NotFoundException, DuplicatedEntryException {
        RideLocation rideLocation = rideLocationService.getRideLocationByID(rideLocationID);
        Location location = locationService.getLocationByID(locationID);
        rideLocation.setLocation(location);
        return rideLocationService.addRideLocation(rideLocation);
    }

    @DeleteMapping("/{id}")
    public String deleteRideLocation(@PathVariable("id") Long rideLocationID) throws NotFoundException {

        rideLocationService.deleteRideLocationByID(rideLocationID);
        return "Ride Location With ID " + rideLocationID + " Deleted Successfully!";

    }

}
