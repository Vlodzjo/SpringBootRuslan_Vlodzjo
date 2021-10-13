package com.example.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Data
public class PersonDto {

    private long id;
    private String fullName;
    private int age;

    @JsonProperty("password")
    private String password;

    private String email;

    private List<String> hobbies;

    private List<AddressDto> addressDto;

    private LocalDate birthday;
}
