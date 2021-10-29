package com.example.restapi.service;

import com.example.restapi.dao.PersonDAO;
import com.example.restapi.dto.AddressDto;
import com.example.restapi.dto.PersonDto;
import com.example.restapi.exception.UserAlreadyExistsException;
import com.example.restapi.exception.UserNotFoundException;
import com.example.restapi.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import validation.CustomValidator;

import javax.validation.Validator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CustomValidator<PersonDto> {

    public final PersonDAO personDAO;
    public final ConversionService conversionService;
    private final Validator validator;

    @Override
    public boolean isPersonExist(UUID id) {
        List<Person> persons = personDAO.getPersons();
        return persons.stream()
                .anyMatch(result -> result
                        .getId()
                        .equals(id));
    }

    @Override
    public void updatePerson(UUID id, PersonDto personDto) {
        validate(personDto);
        if (this.isPersonExist(id)) {

            log.info("We got such person {}", personDto);

            personDto.setId(id);
            Person person = conversionService.convert(personDto, Person.class);

            personDAO.updatePerson(id, person);

        } else {
            throw new RuntimeException("Abra kadabra exception person is not present");
        }
    }

    @Override
    public Person getPerson(UUID id) {
        if (isPersonExist(id)) {
            return personDAO.getPerson(id);
        } else {
            throw new RuntimeException(String.format("Client was not found with id [%d] ", id));
        }
    }

    @Override
    public List<PersonDto> getPersons() {
        List<Person> persons = personDAO.getPersons();
        return persons.stream()
                .map(person -> conversionService.convert(person, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createPerson(PersonDto personDto) {
        validate(personDto);
        if (isPersonExist(personDto.getId())) {
            throw new UserAlreadyExistsException(String.format("Person with id [%d] is present", personDto.getId().hashCode()));
        } else {
            log.info("We got such person {}", personDto);

            personDto.setId(null);
            Person person = conversionService.convert(personDto, Person.class);

            personDAO.savePerson(person);
        }
    }

    @Override
    public void deletePerson(UUID id) {
        if (isPersonExist(id)) {
            personDAO.getPersons().stream()
                    .filter(result -> result.getId().equals(id))
                    .findFirst()
                    .ifPresent(personDAO::removePerson);
        } else {
            throw new UserNotFoundException("Can`t delete... this Person don`t exist");
        }
    }

    @Override
    public javax.validation.Validator getValidator() {
        return validator;
    }
}
