package com.genius.webus.service.implemention;

import com.genius.webus.error.DuplicatedEntryException;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.entity.Location;
import com.genius.webus.repository.LocationRepository;
import com.genius.webus.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationByID(Long locationID) throws NotFoundException{
        Optional<Location> location = locationRepository.findById(locationID);
        if (!location.isPresent()){

            throw new NotFoundException("Location With ID "+ locationID +" Not Found");

        }

        return location.get();
    }

    @Override
    public Location addLocation(Location location) throws DuplicatedEntryException {
        Optional<Location> dbLocation = locationRepository.findLocationByLocationNameIgnoreCase(location.getLocationName());
        if (dbLocation.isPresent()){

            throw new DuplicatedEntryException(location.getLocationName() + " is Already exist!");

        }
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocationByID(Long locationID) throws NotFoundException {

        Optional<Location> location = locationRepository.findById(locationID);
        if (!location.isPresent()){

            throw new NotFoundException("Location With ID "+ locationID +" Not Found");

        }

        locationRepository.deleteById(locationID);

    }

    @Override
    public Location updateLocationByID(Long locationID, Location location) throws NotFoundException, DuplicatedEntryException {
        Optional<Location> dbLocation = locationRepository.findById(locationID);
        if (!dbLocation.isPresent()){

            throw new NotFoundException("Location With ID "+ locationID +" Not Found");

        }
        if (!location.getLocationName().isEmpty() && location.getLocationName() != null){

            Optional<Location> dbLocationName = locationRepository.findLocationByLocationNameIgnoreCase(location.getLocationName());
            if (dbLocationName.isPresent()){

                throw new DuplicatedEntryException(location.getLocationName() + " is Already exist!");

            }else {

                dbLocation.get().setLocationName(location.getLocationName());

            }

        }
        if (location.getLatitude() != null){

            dbLocation.get().setLatitude(location.getLatitude());

        }
        if (location.getLongitude() != null){

            dbLocation.get().setLongitude(location.getLongitude());

        }
        return locationRepository.save(dbLocation.get());

    }


}
