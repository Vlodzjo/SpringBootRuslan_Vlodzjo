package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private long id;
    private String country;
    private String city;
    private String street;
    private int build;

}
