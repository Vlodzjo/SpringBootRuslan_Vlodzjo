package com.example.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import validation.ValidPassword;

import javax.management.ConstructorParameters;
import javax.validation.constraints.*;
import javax.xml.transform.Source;
import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PersonDto {

    private UUID id;
    @NotBlank(message = "FullName must not been blanked")
    private String fullName;
    @Min(value = 1, message = "Minimal value = 1")
    private int age;

    @JsonProperty("password")
    @NotNull
    @NotBlank(message = "Password is mandatory")
    @ValidPassword
    private String password;

    @NotBlank
    @Email
    private String email;

    private List<String> hobbiesDto;

    @NotNull
    @NotEmpty
    private List<AddressDto> addressDto;

    private LocalDate birthday;

}
