package com.genius.webus.service.implemention;

import com.genius.webus.entity.Bus;
import com.genius.webus.error.ConstraintViolationException;
import com.genius.webus.repository.BusRepository;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Override
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    @Override
    public Bus addBus(Bus bus) throws ConstraintViolationException {
        try {
            return busRepository.save(bus);
        }catch (PersistenceException e){

            throw new ConstraintViolationException("Bus With Number " + bus.getBusNumber() + " is Already Added!");

        }catch (DataIntegrityViolationException exception){

            throw new ConstraintViolationException("Bus With Number " + bus.getBusNumber() + " is Already Added!");

        }
    }

    @Override
    public Bus getBusByID(Long busID) throws NotFoundException{

        Optional<Bus> bus = busRepository.findById(busID);
        if (!bus.isPresent()){

            throw new NotFoundException("Bus With ID "+ busID +" Not Found");

        }

        return bus.get();

    }

    @Override
    public void deleteBusByID(Long busID) throws NotFoundException{

        Optional<Bus> bus = busRepository.findById(busID);
        if (!bus.isPresent()){

            throw new NotFoundException("Bus With ID "+ busID +" Not Found");

        }

        busRepository.deleteById(busID);

    }

    @Override
    public Bus updateBusByID(Long busID, Bus bus) throws NotFoundException{

        Optional<Bus> dbBus = busRepository.findById(busID);
        if (!dbBus.isPresent()){

            throw new NotFoundException("Bus With ID "+ busID +" Not Found");

        }

        if (!bus.getCarNumberPlate().isEmpty() && bus.getCarNumberPlate() != null){

            dbBus.get().setCarNumberPlate(bus.getCarNumberPlate());

        }
        if (bus.getNumberOfSeats() != 0){

            dbBus.get().setNumberOfSeats(bus.getNumberOfSeats());

        }

        return busRepository.save(dbBus.get());

    }
}
