package com.genius.webus.service;


import com.genius.webus.entity.Bus;
import com.genius.webus.error.ConstraintViolationException;
import com.genius.webus.error.NotFoundException;

import java.util.List;

public interface BusService {

    public List<Bus> getAllBuses();
    public Bus addBus(Bus bus) throws ConstraintViolationException;
    public Bus getBusByID(Long busID) throws NotFoundException;
    public void deleteBusByID(Long busID) throws NotFoundException;
    public Bus updateBusByID(Long busID, Bus bus) throws NotFoundException;
}
