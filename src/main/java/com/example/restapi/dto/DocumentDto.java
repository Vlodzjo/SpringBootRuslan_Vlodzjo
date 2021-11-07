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
public class DocumentDto {


    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "document_auto")
    private UUID id;


    private PassportDto passportDto;


    private IdentificationDto identificationDto;

}
