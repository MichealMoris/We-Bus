package com.genius.webus.repository;

import com.genius.webus.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

    public Optional<Ride> findRideByRideNameIgnoreCase(String rideName);

}
