package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Calendar;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Person {

    private long id;
    private String firstName;
    private String lastName;
//    private int age;
    private String password;
    private Address address;

    private LocalDate birthday;
    // додати дату народження і вираховувати від теперішнього року скільки age має person

}
