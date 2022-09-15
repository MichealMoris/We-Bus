package com.genius.webus.controller;

import com.genius.webus.entity.Location;
import com.genius.webus.error.DuplicatedEntryException;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<Location> getAllLocations(){

        return locationService.getAllLocations();

    }

    @GetMapping("/{id}")
    public Location getLocationByID(@PathVariable("id") Long locationID) throws NotFoundException {

        return locationService.getLocationByID(locationID);

    }

    @PostMapping
    public Location addLocation(@Valid @RequestBody Location location) throws DuplicatedEntryException {

        return locationService.addLocation(location);

    }

    @DeleteMapping("/{id}")
    public String deleteLocation(@PathVariable("id") Long locationID) throws NotFoundException{

        locationService.deleteLocationByID(locationID);
        return "Location with ID " + locationID + " deleted successfully!";

    }

    @PutMapping("/{id}")
    public Location updateLocationByID(@PathVariable("id") Long locationID, @Valid @RequestBody Location location) throws NotFoundException, DuplicatedEntryException{

        return locationService.updateLocationByID(locationID, location);

    }


}
