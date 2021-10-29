package com.example.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;
import validation.ValidPassword;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Validated
public class Person {

    private UUID id;
    @NotBlank(message = "FirstName must been blanked")
    private String firstName;
    @NotBlank(message = "LastName must been blanked")
    private String lastName;
    @JsonProperty("password")
    @NotNull
    @NotBlank(message = "New password is mandatory")
    @ValidPassword
    private String password;
    @NotNull
    @NotEmpty
    private Address address;
    private LocalDate birthday;
    @NotBlank
    @Email
    private String email;
    private List<String> hobbies;
}
