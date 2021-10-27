package com.example.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@Validated
public class AddressDto {

    private long id;
    private String country;
    private String city;
    private String street;
    private int build;

}
