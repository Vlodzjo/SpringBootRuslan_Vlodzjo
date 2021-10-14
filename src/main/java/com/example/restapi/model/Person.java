package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Person {

    private long id;
    private String firstName;
    private String lastName;
    @NotNull
    private String password;
    private Address address;
    private LocalDate birthday;

}
