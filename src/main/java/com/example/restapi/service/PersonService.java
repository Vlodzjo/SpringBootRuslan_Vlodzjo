package com.example.restapi.service;

import com.example.restapi.dto.PersonDto;
import com.example.restapi.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    void updatePerson(long id, PersonDto personDto);

    boolean isPersonExist(long id);

    Optional<Person> getOptionalPersonById(long id);

    Person getPerson(long id);

    List<PersonDto> getPersons();

    void createPerson(PersonDto personDto);

    void deletePerson(long id);
}
