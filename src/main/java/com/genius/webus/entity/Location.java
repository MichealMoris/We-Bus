package com.genius.webus.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "LOCATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOCATION_ID")
    private Long locationID;
    @Column(unique = true)
    @NotBlank(message = "Location Name Cannot Be Blank!")
    private String locationName;
    @NotNull(message = "Latitude Cannot Be Null!")
    private Double latitude;
    @NotNull(message = "Longitude Cannot Be Null!")
    private Double longitude;


}
