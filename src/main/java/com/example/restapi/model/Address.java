package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "address_auto"
    )
    private UUID id;

    private String country;

    private String city;

    private String street;

    private int build;

    public Address(String country, String city, String street, int build) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.build = build;
    }
}
