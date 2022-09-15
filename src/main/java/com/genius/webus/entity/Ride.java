package com.genius.webus.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "RIDE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "rideID")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RIDE_ID")
    private Long rideID;
    @Column(unique = true)
    @NotBlank(message = "Ride Name Cannot Be Empty!")
    private String rideName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rideLocations")
    private List<RideLocation> rideLocations;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_id")*/
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_id")
    private Bus bus;

}
