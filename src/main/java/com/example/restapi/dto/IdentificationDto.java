package com.example.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentificationDto {

    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "identification_auto")
    private UUID id;

    long identificationNumber;

}
