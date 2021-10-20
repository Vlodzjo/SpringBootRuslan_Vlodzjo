package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@Validated
public class Address {
    @Min(value = 1, message = "Minimal id = 1")
    private long id;
    private String country;
    private String city;
    private String street;
    private int build;

}
