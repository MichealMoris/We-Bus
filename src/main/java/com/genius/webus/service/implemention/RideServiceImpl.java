package com.genius.webus.service.implemention;

import com.genius.webus.entity.Location;
import com.genius.webus.entity.Ride;
import com.genius.webus.error.DuplicatedEntryException;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.repository.RideRepository;
import com.genius.webus.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RideServiceImpl implements RideService {

    @Autowired
    private RideRepository rideRepository;

    @Override
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    @Override
    public Ride getRideByID(Long rideID) throws NotFoundException {
        Optional<Ride> ride = rideRepository.findById(rideID);
        if (!ride.isPresent()){

            throw new NotFoundException("Ride With ID "+ rideID +" Not Found");

        }

        return ride.get();
    }

    @Override
    public Ride addRide(Ride ride) throws DuplicatedEntryException {
        Optional<Ride> dbRide = rideRepository.findRideByRideNameIgnoreCase(ride.getRideName());
        if (dbRide.isPresent() && dbRide.get().getRideID() != ride.getRideID()){

            throw new DuplicatedEntryException(ride.getRideName() + " is Already exist!");

        }
        return rideRepository.save(ride);
    }

    @Override
    public void deleteRideByID(Long rideId) throws NotFoundException {

        Optional<Ride> ride = rideRepository.findById(rideId);
        if (!ride.isPresent()){

            throw new NotFoundException("Ride With ID "+ rideId +" Not Found");

        }

        rideRepository.deleteById(rideId);


    }

    @Override
    public Ride updateRideByID(Long rideId, Ride ride) throws NotFoundException, DuplicatedEntryException {
        Optional<Ride> dbRide = rideRepository.findById(rideId);
        if (!dbRide.isPresent()){

            throw new NotFoundException("Ride With ID "+ rideId +" Not Found");

        }
        if (!ride.getRideName().isEmpty() && ride.getRideName() != null){

            Optional<Ride> dbRideName = rideRepository.findRideByRideNameIgnoreCase(ride.getRideName());
            if (dbRideName.isPresent()){

                throw new DuplicatedEntryException(ride.getRideName() + " is Already exist!");

            }else {

                dbRide.get().setRideName(ride.getRideName());

            }

        }

        return rideRepository.save(dbRide.get());
    }
}
