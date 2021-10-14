package com.example.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {

    private long id;
    private String country;
    private String city;
    private String street;
    private int build;

}
