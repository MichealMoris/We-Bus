package com.genius.webus.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "busID")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long busID;
    @Column(unique = true)
    private int busNumber;
    @NotBlank(message = "Car Number Plate Cannot Be Empty!")
    private String carNumberPlate;
    @Min(1)
    private int numberOfSeats;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "users")*/
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bus")
    private List<User> users;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ride_id")*/
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bus")
    private Ride ride;

}
