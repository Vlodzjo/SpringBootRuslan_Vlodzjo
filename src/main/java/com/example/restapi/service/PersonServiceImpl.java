package com.example.restapi.service;

import com.example.restapi.dao.PersonDAO;
import com.example.restapi.dto.AddressDto;
import com.example.restapi.dto.PersonDto;
import com.example.restapi.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    DateFormat df = new SimpleDateFormat("yyyy");
    DateFormat dfOutPut = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    public PersonDAO personDAO;

    private PersonDto setPersonDtoProperties(Person person) {
        LocalDate dateNow = LocalDate.now();

        PersonDto personDto = new PersonDto();
        AddressDto addressDto = new AddressDto(
                person.getAddress().getId(),
                person.getAddress().getCountry(),
                person.getAddress().getCity(),
                person.getAddress().getStreet(),
                person.getAddress().getBuild());

        personDto.setAge(dateNow.getYear() - person.getBirthday().getYear());
        personDto.setPassword(person.getPassword());
        personDto.setEmail("test@gmail.com");
        personDto.setFullName(person.getFirstName() + " " + person.getLastName());
        personDto.setId(person.getId());
        personDto.setBirthday(person.getBirthday()); // change it
        personDto.setAddressDto(List.of(addressDto));

        return personDto;
    }

    @Override
    public void updatePerson(long id, PersonDto personDto) {
        //final Optional<Person> personById = this.getPersonById(id);
        if (this.isPersonExist(id)) {
            String stringName = personDto.getFullName();
            String[] resultStringName = stringName.split(" ");
            if (resultStringName.length != 2) {
                throw new RuntimeException("Incorrect Full Name");
            }
            log.info("We got such person {}", personDto);
            if (personDto.getPassword() == null) {
                throw new RuntimeException("PASSWORD IS REQUIRED");
            }

            List<AddressDto> addressDto = personDto.getAddressDto();
            if (addressDto == null || addressDto.isEmpty()) {
                throw new RuntimeException("Please specify address");
            }

            Person person = this.getPerson(id);
            person.setPassword(personDto.getPassword());
            person.setFirstName(resultStringName[0]);
            person.setLastName(resultStringName[1]);
            person.setBirthday(personDto.getBirthday());

            person.getAddress().setCity(personDto.getAddressDto().get(0).getCity());
            person.getAddress().setCountry(personDto.getAddressDto().get(0).getCountry());
            person.getAddress().setBuild(personDto.getAddressDto().get(0).getBuild());
            person.getAddress().setStreet(personDto.getAddressDto().get(0).getStreet());

        } else {
            throw new RuntimeException("Abra kadabra exception");
        }
    }

    @Override
    public boolean isPersonExist(long id) {
        List<Person> persons = personDAO.getPersons();
        for (Person result : persons) {
            if (result.getId() == id) {
                return true;
            }
        }
        return false;
        //return personDAO.getPersons().stream().anyMatch(x -> x.getId() == 0);
    }

    @Override
    public Optional<Person> getOptionalPersonById(long id) {
        return personDAO.getPersons().stream().filter(x -> x.getId() == id).findFirst();
    }

    @Override
    public Person getPerson(long id) {
        List<Person> persons = personDAO.getPersons();
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        throw new RuntimeException(String.format("Client was not found with id %d ", id));
    }

    @Override
    public List<PersonDto> getPersons() {
        List<Person> persons = personDAO.getPersons();
        List<PersonDto> result = new ArrayList<>();
        for (Person person : persons) {
            result.add(setPersonDtoProperties(person));
        }
        return result;
    }

    @Override
    public void createPerson(PersonDto personDto) {
        if (isPersonExist(personDto.getId())) {
            throw new RuntimeException(String.format("Person with id [%d] is present", personDto.getId()));
        } else {
            String stringName = personDto.getFullName();
            String[] resultStringName = stringName.split(" ");
            if (resultStringName.length != 2) {
                throw new RuntimeException("Incorrect Full Name");
            }
            log.info("We got such person {}", personDto);
            if (personDto.getPassword() == null) {
                throw new RuntimeException("PASSWORD IS REQUIRED");
            }

            List<AddressDto> addressDto = personDto.getAddressDto();
            if (addressDto == null || addressDto.isEmpty()) {
                throw new RuntimeException("Please specify address");
            }

            Person person = new Person();
            person.setId(personDto.getId());
            person.setPassword(personDto.getPassword());
            person.setFirstName(resultStringName[0]);
            person.setLastName(resultStringName[1]);
            person.setBirthday(personDto.getBirthday());


            personDAO.savePerson(person);
        }
    }

    @Override
    public void deletePerson(long id) {
        if (isPersonExist(id)){
            List<Person> persons = personDAO.getPersons();
            for (Person result : persons) {
                if (result.getId() == id) {
                    personDAO.removePerson(result);
                    return;
                }
            }
        }
        else {
            throw new RuntimeException("Can`t delete... this Person don`t exist");
        }
    }
}