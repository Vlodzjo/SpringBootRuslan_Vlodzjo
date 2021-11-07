package com.example.restapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SearchPersonDto {

    @Email
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
