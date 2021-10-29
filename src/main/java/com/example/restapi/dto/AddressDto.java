package com.example.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Data
@AllArgsConstructor
@Validated
public class AddressDto {

    private UUID id;
    private String country;
    private String city;
    private String street;
    private int build;

}
