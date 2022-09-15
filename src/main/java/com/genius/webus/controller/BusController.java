package com.genius.webus.controller;

import com.genius.webus.entity.Bus;
import com.genius.webus.entity.Ride;
import com.genius.webus.entity.RideLocation;
import com.genius.webus.entity.User;
import com.genius.webus.error.ConstraintViolationException;
import com.genius.webus.error.DuplicatedEntryException;
import com.genius.webus.service.BusService;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.service.RideService;
import com.genius.webus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private UserService userService;

    @Autowired
    private RideService rideService;

    @GetMapping
    public List<Bus> getBusByID(){

        return busService.getAllBuses();

    }

    @GetMapping("/{id}")
    public Bus getBusByID(@PathVariable("id") Long busID) throws NotFoundException{

        return busService.getBusByID(busID);

    }

    @PostMapping
    public Bus addBus(@Valid @RequestBody Bus bus) throws ConstraintViolationException {

        return busService.addBus(bus);

    }

    @DeleteMapping("/{id}")
    public String deleteBusByID(@PathVariable("id") Long busID) throws NotFoundException{

        busService.deleteBusByID(busID);
        return "Bus with ID "+busID+" deleted successfully!";


    }

    @PutMapping("/{id}")
    public Bus updateBusByID(@PathVariable("id") Long busID, @Valid @RequestBody Bus bus) throws NotFoundException{

        return busService.updateBusByID(busID, bus);

    }

    @PutMapping("/{busID}/users/{userID}")
    public Bus addUserToBus(
            @PathVariable Long busID,
            @PathVariable Long userID
    ) throws NotFoundException, DuplicatedEntryException, ConstraintViolationException {
        Bus bus = busService.getBusByID(busID);
        User user = userService.getUserByID(userID);
        for (User loopUsers : bus.getUsers()){

            if (loopUsers.getUserID() == user.getUserID()){

                throw new DuplicatedEntryException("User With ID " + user.getUserID() + " Already Exist!");

            }
            if (loopUsers.getUsername() == user.getUsername()){

                throw new DuplicatedEntryException("User With Username " + user.getUsername() + " Already Exist!");

            }

        }
        bus.getUsers().add(user);
        return busService.addBus(bus);
    }


}
