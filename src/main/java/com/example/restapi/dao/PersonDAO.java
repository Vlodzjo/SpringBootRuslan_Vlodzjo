package com.example.restapi.dao;

import com.example.restapi.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonDAO {

    void savePerson(Person person);

    Person getPerson(UUID id);

    void updatePerson(UUID id, Person person);

    List<Person> getPersons();

    void removePerson(Person person);
}
