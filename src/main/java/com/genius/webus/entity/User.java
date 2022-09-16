package com.genius.webus.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userID")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    @NotBlank(message = "Full Name Cannot Be Empty!")
    private String userFullName;

    @Column(unique = true)
    @NotBlank(message = "Username Cannot Be Empty!")
    private String username;

    @Column(unique = true)
    @Email
    private String email;

    @Column(unique = true)
    @NotBlank(message = "Phone Number Cannot Be Empty!")
    private String phoneNumber;

    @NotBlank(message = "Type Cannot Be Empty!")
    private String type;

    private boolean isAdmin;

    private String password;

    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus")*/
    @ManyToOne()
    @JoinColumn(name = "bus_id")
    private Bus bus;

}
