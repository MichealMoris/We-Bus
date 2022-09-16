package com.genius.webus.controller;

import com.genius.webus.entity.Bus;
import com.genius.webus.entity.User;
import com.genius.webus.error.DuplicatedEntryException;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.service.BusService;
import com.genius.webus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BusService busService;

   /* @PostConstruct
    public void pojoData(){

        userService.pojoData();

    }*/

    @GetMapping
    public List<User> getAllUsers(){

        return userService.getAllUsers();

    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable("id") Long userID) throws NotFoundException{

        return userService.getUserByID(userID);

    }


    @GetMapping("/phoneNumber/{phoneNumber}")
    public User getUserByPhoneNumber(@PathVariable("phoneNumber") String userPhoneNumber) throws NotFoundException{

        return userService.getUserByPhoneNumber(userPhoneNumber);

    }

    @PostMapping
    public User addUsers(@Valid @RequestBody User user) throws DuplicatedEntryException {

        return userService.addUser(user);

    }

    @DeleteMapping("/{id}")
    public String deleteUserByID(@PathVariable("id") Long userID) throws NotFoundException{

        userService.deleteUserByID(userID);
        return "User with ID " + userID + " deleted successfully!";

    }

    @PutMapping("/{id}")
    public User updateUserByID(@PathVariable("id") Long ID, @Valid @RequestBody User user) throws NotFoundException {

        return userService.updateUserByID(ID, user);

    }

    @PutMapping("/{userID}/bus/{busID}")
    public User addBusToUser(
            @PathVariable Long userID,
            @PathVariable Long busID
    ) throws NotFoundException, DuplicatedEntryException {
        User user = userService.getUserByID(userID);
        Bus bus = busService.getBusByID(busID);
        user.setBus(bus);
        return userService.addUserToBus(user);
    }

}
