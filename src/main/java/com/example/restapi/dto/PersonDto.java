package com.example.restapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Validated
@NoArgsConstructor
public class PersonDto {

    @Min(value = 1, message = "Minimal id = 1")
    private long id;
    @NotBlank(message = "FullName must been blanked")
    private String fullName;
    @Min(value = 1, message = "Minimal value = 1")
    private int age;

    @JsonProperty("password")
    @NotNull
    @Size(min = 1, message = "Minimal size pass = 1")
    private String password;

    @Email
    private String email;

    private String[] hobbiesDto;

    private List<AddressDto> addressDto;

    private LocalDate birthday;

}
