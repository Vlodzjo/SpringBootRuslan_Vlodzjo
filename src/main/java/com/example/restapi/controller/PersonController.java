package com.example.restapi.controller;

import com.example.restapi.dto.PersonDto;
import com.example.restapi.model.Person;
import com.example.restapi.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PersonController {

    public final PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<List<PersonDto>> getPersons() {
        return new ResponseEntity<>(personService.getPersons(), HttpStatus.OK);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable UUID id) {
        return new ResponseEntity<>(personService.getPerson(id), HttpStatus.OK);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable UUID id, @RequestBody PersonDto personDto) {
        personService.updatePerson(id, personDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/persons")
    public ResponseEntity<Void> createPerson(@RequestBody PersonDto personDto) {
        personService.createPerson(personDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
