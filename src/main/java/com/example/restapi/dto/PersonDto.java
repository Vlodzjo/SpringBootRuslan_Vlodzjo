package com.example.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PersonDto {

    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "passport_auto")
    private UUID id;

    @NotBlank(message = "FullName must not been blanked")
    private String fullName;

    @Min(value = 1, message = "Minimal value = 1")
    private int age;

    @JsonProperty("password")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private AddressDto addressDto;

    private LocalDate birthday;

    private List<PersonVaccineDto> vaccines;

    private DocumentDto documentDto;

}
