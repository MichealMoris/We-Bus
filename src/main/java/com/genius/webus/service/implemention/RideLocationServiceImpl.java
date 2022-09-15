package com.genius.webus.service.implemention;

import com.genius.webus.entity.Ride;
import com.genius.webus.entity.RideLocation;
import com.genius.webus.error.DuplicatedEntryException;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.repository.RideLocationRepository;
import com.genius.webus.service.RideLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RideLocationServiceImpl implements RideLocationService {

    @Autowired
    private RideLocationRepository rideLocationRepository;

    @Override
    public List<RideLocation> getAllRideLocations() {
        return rideLocationRepository.findAll();
    }

    @Override
    public RideLocation addRideLocation(RideLocation rideLocation){
        return rideLocationRepository.save(rideLocation);
    }

    @Override
    public RideLocation getRideLocationByID(Long id) throws NotFoundException {
        Optional<RideLocation> rideLocation = rideLocationRepository.findById(id);
        if (!rideLocation.isPresent()){

            throw new NotFoundException("Ride Location With ID "+ id +" Not Found");

        }
        return rideLocationRepository.findById(id).get();
    }

    @Override
    public void deleteRideLocationByID(Long rideLocationID) throws NotFoundException {

        Optional<RideLocation> rideLocation = rideLocationRepository.findById(rideLocationID);
        if (!rideLocation.isPresent()){

            throw new NotFoundException("Ride Location With ID "+ rideLocationID +" Not Found");

        }
        rideLocationRepository.deleteById(rideLocationID);

    }

}
