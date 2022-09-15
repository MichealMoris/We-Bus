package com.genius.webus.repository;

import com.genius.webus.entity.Location;
import com.genius.webus.entity.Ride;
import com.genius.webus.entity.RideLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RideLocationRepository extends JpaRepository<RideLocation, Long> {


}
