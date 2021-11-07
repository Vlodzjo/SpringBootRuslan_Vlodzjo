package com.example.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassportDto {

    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "passport_auto")
    private UUID id;

    private String passportNumberStr;

    private int passportNumberInt;

    @NotBlank
    private String sex;

    // Дата видачі
    private LocalDate dateOfIssue;

    // Дата дійсності
    private LocalDate dateOfExpiry;

    // Who authority this document
    @NotBlank
    private int authority;

}
