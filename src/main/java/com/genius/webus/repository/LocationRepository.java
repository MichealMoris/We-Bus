package com.genius.webus.repository;

import com.genius.webus.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

    public Optional<Location> findLocationByLocationNameIgnoreCase(String locationName);

}
