package com.example.restapi.repository;

import com.example.restapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    List<Person> findByEmail(String email);
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findByBirthdayBetween(LocalDate from, LocalDate to);
}
