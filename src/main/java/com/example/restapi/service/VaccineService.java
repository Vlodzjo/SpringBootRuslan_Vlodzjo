package com.example.restapi.service;

import com.example.restapi.dto.VaccineDto;

import java.util.List;
import java.util.UUID;

public interface VaccineService {

    List<VaccineDto> getVaccines();

    void createVaccine(VaccineDto vaccineDto);

    VaccineDto getVaccine(UUID id);

}
