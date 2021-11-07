package com.example.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineDto {

    private UUID id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private LocalDate basedDate;

    @NotNull
    private String madeIn;

}
