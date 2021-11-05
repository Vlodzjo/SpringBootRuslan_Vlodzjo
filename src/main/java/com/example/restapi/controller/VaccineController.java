package com.example.restapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.restapi.dto.VaccineDto;
import com.example.restapi.service.VaccineService;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class VaccineController {

    public final VaccineService vaccineService;

    @PostMapping("/vaccine")
    public ResponseEntity<Void> createPerson(@RequestBody VaccineDto vaccineDto) {
        vaccineService.createVaccine(vaccineDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/vaccines")
    public ResponseEntity<List<VaccineDto>> getVaccines() {
        return new ResponseEntity <>(vaccineService.getVaccines(), HttpStatus.OK);
    }

}
