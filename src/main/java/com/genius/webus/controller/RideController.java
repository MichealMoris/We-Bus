package com.genius.webus.controller;

import com.genius.webus.entity.Bus;
import com.genius.webus.entity.Location;
import com.genius.webus.entity.Ride;
import com.genius.webus.entity.RideLocation;
import com.genius.webus.error.DuplicatedEntryException;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.service.BusService;
import com.genius.webus.service.RideLocationService;
import com.genius.webus.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @Autowired
    private RideLocationService rideLocationService;

    @Autowired
    private BusService busService;

    @GetMapping
    public List<Ride> getAllRides(){

        return rideService.getAllRides();

    }

    @GetMapping("/{id}")
    public Ride getRideByID(@PathVariable("id") Long rideID) throws NotFoundException {

        return rideService.getRideByID(rideID);

    }

    @PostMapping
    public Ride addRide(@Valid @RequestBody Ride ride) throws DuplicatedEntryException {

        return rideService.addRide(ride);

    }

    @DeleteMapping("/{id}")
    public String deleteRide(@PathVariable("id") Long rideId) throws NotFoundException{

        rideService.deleteRideByID(rideId);
        return "Ride with ID " + rideId + " deleted successfully!";

    }

    @PutMapping("/{id}")
    public Ride updateRideByID(@PathVariable("id") Long rideId, @Valid @RequestBody Ride ride) throws NotFoundException, DuplicatedEntryException{

        return rideService.updateRideByID(rideId, ride);

    }

    @PutMapping("/{rideID}/rideLocation/{rideLocationID}")
    public Ride addRideLocationToRide(
            @PathVariable Long rideID,
            @PathVariable Long rideLocationID
    ) throws NotFoundException, DuplicatedEntryException {
        RideLocation rideLocation = rideLocationService.getRideLocationByID(rideLocationID);
        Ride ride = rideService.getRideByID(rideID);
        for (RideLocation loopRideLocation : ride.getRideLocations()){

            if (loopRideLocation.getRideLocationID() == rideLocation.getRideLocationID()){

                throw new DuplicatedEntryException("Ride Location With ID " + rideLocation.getRideLocationID() + " Already Exist!");

            }
            if (loopRideLocation.getLocationOrder() == rideLocation.getLocationOrder()){

                throw new DuplicatedEntryException("Ride Location With Order " + rideLocation.getLocationOrder() + " Already Exist!");

            }
            if (loopRideLocation.getLocation().getLocationID() == rideLocation.getLocation().getLocationID()){

                throw new DuplicatedEntryException("Ride Location With Location ID " + rideLocation.getLocation().getLocationID() + " Already Exist!");

            }

        }
        ride.getRideLocations().add(rideLocation);
        return rideService.addRide(ride);
    }

    @PutMapping("/{rideID}/bus/{busID}")
    public Ride addBusToRide(
            @PathVariable Long busID,
            @PathVariable Long rideID
    ) throws NotFoundException, DuplicatedEntryException {
        Bus bus = busService.getBusByID(busID);
        Ride ride = rideService.getRideByID(rideID);
        if (ride.getBus() != null){

            if (bus.getBusID().equals(ride.getBus().getBusID())){

                throw new DuplicatedEntryException("Bus With ID " + bus.getBusID() + " Already Exist!");

            }else if (bus.getCarNumberPlate().equals(ride.getBus().getCarNumberPlate())){

                throw new DuplicatedEntryException("Bus With Number Plate " + bus.getCarNumberPlate() + " Already Exist!");

            }

        }
        ride.setBus(bus);
        return rideService.addRide(ride);
    }

}
