package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Passport")
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "passport_auto")
    @Column(updatable = false)
    private UUID id;

    private String passportNumberStr;

    private int passportNumberInt;

    private String sex;

    // Дата видачі
    private LocalDate dateOfIssue;

    // Дата дійсності
    private LocalDate dateOfExpiry;

    // Who authority this document
    private int authority;

}
