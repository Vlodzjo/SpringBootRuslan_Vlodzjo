package com.example.restapi.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.example.restapi.dto.VaccineDto;
import com.example.restapi.model.Vaccine;

@Component
@RequiredArgsConstructor
public class VaccineDtoToVaccineConverter implements Converter<VaccineDto, Vaccine> {

    @Override
    public Vaccine convert(VaccineDto vaccineDto) {
        return new Vaccine(
                vaccineDto.getName(),
                vaccineDto.getDescription(),
                vaccineDto.getBasedDate(),
                vaccineDto.getMadeIn());
    }
}
