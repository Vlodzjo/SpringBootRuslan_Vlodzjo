package com.example.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class PersonDto {

    private long id;
    private String fullName;
    private int age;

    @JsonProperty("password")
    @NotNull
    private String password;

    private String email;

    private List<String> hobbies;

    private List<AddressDto> addressDto;

    private LocalDate birthday;

}
