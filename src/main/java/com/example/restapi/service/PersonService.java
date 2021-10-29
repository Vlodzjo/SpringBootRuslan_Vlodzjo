package com.example.restapi.service;

import com.example.restapi.dto.PersonDto;
import com.example.restapi.model.Person;
import java.util.List;
import java.util.UUID;

public interface PersonService {

    void updatePerson(UUID id, PersonDto personDto);

    boolean isPersonExist(UUID id);

    Person getPerson(UUID id);

    List<PersonDto> getPersons();

    void createPerson(PersonDto personDto);

    void deletePerson(UUID id);
}
