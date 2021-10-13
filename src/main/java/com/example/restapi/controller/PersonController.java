package com.example.restapi.controller;

import com.example.restapi.dto.PersonDto;
import com.example.restapi.model.Person;
import com.example.restapi.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class PersonController {

    @Autowired
    private Random random;

    @Autowired
    private PersonService personService;

    private List<String> smartfones = Arrays.asList("XIOMI", "MEIZU");

    @GetMapping("/persons")
    public ResponseEntity<List<PersonDto>> getPersons() {
        return new ResponseEntity<>(personService.getPersons(), HttpStatus.OK);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable long id) {
        return new ResponseEntity<>(personService.getPerson(id), HttpStatus.OK);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable long id, @RequestBody PersonDto personDto) {
        personService.updatePerson(id, personDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/persons")
    public ResponseEntity<Void> createPerson(@RequestBody PersonDto personDto) {
        personService.createPerson(personDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }















    @GetMapping("/hello")
    public ResponseEntity<String> getSimpleOutput() {
        boolean condition = random.nextBoolean();
        if (condition) {
            return new ResponseEntity<>("Hello world", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/demo/{categoryName}/{count}")
    public ResponseEntity<List<String>> demo(@PathVariable String categoryName, @PathVariable int count,
                                             @RequestParam(required = false) String hl, @RequestParam String sl) {
        log.info("Sl is {}, hl is {}", sl, hl);
        log.info("Category name is : {} , count is {}", categoryName, count);
        if ("smartfon".equals(categoryName)) {
            return new ResponseEntity<>(smartfones, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/demo")
    public ResponseEntity<Void> postDemo(@RequestBody PersonDto personDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/demo1")
    public ResponseEntity<Void> postDemo1(@RequestBody List<String> names) {
        boolean condition = random.nextBoolean();
        log.info("We got such request {}", names);
        if (condition) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}