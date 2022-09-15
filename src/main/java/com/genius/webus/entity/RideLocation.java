package com.genius.webus.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "RIDE_LOCATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rideLocationID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location")
    private Location location;

    @Column(name = "location_order")
    private int locationOrder;

}
