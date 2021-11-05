package com.example.restapi.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.example.restapi.dto.VaccineDto;
import com.example.restapi.model.Vaccine;

@Component
public class VaccineToVaccineDtoConverter implements Converter<Vaccine, VaccineDto> {

    @Override
    public VaccineDto convert(Vaccine vaccine) {
        return new VaccineDto(
                vaccine.getId(),
                vaccine.getName(),
                vaccine.getDescription(),
                vaccine.getBasedDate(),
                vaccine.getMadeIn()
        );
    }
}
