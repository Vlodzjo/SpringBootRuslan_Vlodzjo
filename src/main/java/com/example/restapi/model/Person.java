package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Validated
public class Person {

    @Min(value = 1, message = "Minimal id = 1")
    private long id;
    @NotBlank(message = "FirstName must been blanked")
    private String firstName;
    @NotBlank(message = "LastName must been blanked")
    private String lastName;
    @NotNull
    @Size(min = 1, message = "Minimal pass size = 1")
    private String password;
    private Address address;
    private LocalDate birthday;

    private String[] hobbies;

}
