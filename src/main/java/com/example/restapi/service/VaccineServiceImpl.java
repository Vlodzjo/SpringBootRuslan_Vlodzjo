package com.example.restapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import com.example.restapi.dto.VaccineDto;
import com.example.restapi.model.Vaccine;
import com.example.restapi.repository.VaccineRepository;
import validation.CustomValidator;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VaccineServiceImpl implements VaccineService, CustomValidator {

    private final Validator validator;
    public final VaccineRepository vaccineRepository;
    public final ConversionService conversionService;

    @Override
    public void createVaccine(VaccineDto vaccineDto) {
        validate(vaccineDto);
        if (vaccineDto != null) {
            log.info("We got such vaccine {}", vaccineDto);
            vaccineDto.setId(null);
            Vaccine vaccine = conversionService.convert(vaccineDto, Vaccine.class);
            vaccineRepository.save(vaccine);
        } else {
            throw new IllegalArgumentException("Vaccine properties is mandatory");
        }
    }

    @Override
    public List<VaccineDto> getVaccines() {
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        return vaccineList.stream()
                .map(v -> conversionService.convert(v, VaccineDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Validator getValidator() {
        return validator;
    }
}
