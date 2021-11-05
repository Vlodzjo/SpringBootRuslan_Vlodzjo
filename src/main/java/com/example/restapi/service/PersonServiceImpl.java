package com.example.restapi.service;

import com.example.restapi.dto.PersonDto;
import com.example.restapi.dto.SearchPersonDto;
import com.example.restapi.exception.UserAlreadyExistsException;
import com.example.restapi.exception.UserNotFoundException;
import com.example.restapi.model.Person;
import com.example.restapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import validation.CustomValidator;

import javax.validation.Validator;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CustomValidator {

    public final PersonRepository personRepository;
    public final ConversionService conversionService;
    private final Validator validator;

    @Override
    public boolean isPersonExist(String email) {
        List<Person> personList = personRepository.findByEmail(email);
        return !personList.isEmpty();
    }

    @Override
    public void updatePerson(UUID id, PersonDto personDto) {
        validate(personDto);
        if (this.isPersonExist(personDto.getEmail())) {
            log.info("We got such person {}", personDto);
            personDto.setId(id);
            Person person = conversionService.convert(personDto, Person.class);
            personRepository.save(person);
        } else {
            throw new RuntimeException("Abra kadabra exception person is not present");
        }
    }

    @Override
    public Person getPerson(UUID id) {
        Optional<Person> byId = personRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new UserNotFoundException(String.format("Client was not found with id [%d] ", id));
        }
    }

    @Override
    public List<PersonDto> getPersons() {
        List<Person> persons = personRepository.findAll();
        return persons.stream()
                .map(person -> conversionService.convert(person, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createPerson(PersonDto personDto) {
        validate(personDto);
        if (isPersonExist(personDto.getEmail())) {
            throw new UserAlreadyExistsException(String.format("Person with email [%s] is present", personDto.getEmail()));
        } else {
            log.info("We got such person {}", personDto);
            personDto.setId(null);
            Person person = conversionService.convert(personDto, Person.class);
            personRepository.save(person);
        }
    }

    @Override
    public void deletePerson(UUID id) {
        Optional<Person> byId = personRepository.findById(id);
        if (byId.isPresent()) {
            personRepository.delete(byId.get());
        } else {
            throw new UserNotFoundException("Can`t delete... this Person don`t exist");
        }
    }

    @Override
    public javax.validation.Validator getValidator() {
        return validator;
    }

    @Override
    public List<PersonDto> searchPersonByEmail(SearchPersonDto searchPersonDto) {
        validate(searchPersonDto);
        if (!StringUtils.isEmpty(searchPersonDto.getEmail())) {
            return personRepository.findByEmail(searchPersonDto.getEmail()).stream()
                    .map(p -> conversionService.convert(p, PersonDto.class))
                    .collect(Collectors.toList());
        } else {
            String firstName = searchPersonDto.getFirstName();
            String lastName = searchPersonDto.getLastName();
            if (firstName != null & lastName != null) {
                firstName = firstName.trim();
                lastName = lastName.trim();
                if (!firstName.isBlank() & !lastName.isBlank()) {
                    return personRepository
                            .findByFirstNameAndLastName(firstName, lastName)
                            .stream()
                            .map(p -> conversionService.convert(p, PersonDto.class))
                            .collect(Collectors.toList());
                } else {
                    throw new IllegalArgumentException(String.format(
                            "One of required fields is empty! First name is: {%s}. Last name is: {%s}.",
                            searchPersonDto.getFirstName(), searchPersonDto.getLastName()));
                }
            } else {
                LocalDate fromDate = searchPersonDto.getDateFrom();
                LocalDate toDate = searchPersonDto.getDateTo();
                if (fromDate != null & toDate != null) {
                    List<PersonDto> collect = personRepository.findByBirthdayBetween(fromDate, toDate)
                            .stream()
                            .map(p -> conversionService.convert(p, PersonDto.class))
                            .collect(Collectors.toList());
                    return collect;
                } else {
                    throw new IllegalArgumentException(String.format(
                            "Required fields is empty! First name is: [%s]. Last name is: [%s].",
                            searchPersonDto.getFirstName(), searchPersonDto.getLastName()) +
                            "Date from: " + searchPersonDto.getDateFrom() +
                            ", date to: " + searchPersonDto.getDateTo());
                }
            }
        }
    }

}
