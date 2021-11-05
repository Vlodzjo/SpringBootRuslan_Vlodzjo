package com.example.restapi.service;

import com.example.restapi.dto.VaccineDto;

import java.util.List;

public interface VaccineService {

    List<VaccineDto> getVaccines();

    void createVaccine(VaccineDto vaccineDto);

}
