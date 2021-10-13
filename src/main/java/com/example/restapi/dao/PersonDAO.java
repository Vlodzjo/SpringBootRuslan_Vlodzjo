package com.example.restapi.dao;

import com.example.restapi.model.Person;

import java.util.List;

public interface PersonDAO {

    void savePerson(Person person);

//    void haveIndex(long id);

    Person getPerson(long id);

    List<Person> getPersons();

    void removePerson(Person person);
}
