package com.example.restapi.service;

import com.example.restapi.dao.PersonDAO;
import com.example.restapi.dto.AddressDto;
import com.example.restapi.dto.PersonDto;
import com.example.restapi.model.Address;
import com.example.restapi.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

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
        personDto.setBirthday(person.getBirthday());
        personDto.setHobbiesDto(person.getHobbies());
        personDto.setEmail(person.getEmail());
        personDto.setAddressDto(List.of(addressDto));

        return personDto;
    }

    private Person setPersonProperties(PersonDto personDto) {

        String[] resultStringName = isFullNameCorrect(personDto);
        Address address = getAddressDto(personDto);

        Person person = new Person();
        person.setId(personDto.getId());
        person.setPassword(personDto.getPassword());
        person.setFirstName(resultStringName[0]);
        person.setLastName(resultStringName[1]);
        person.setBirthday(personDto.getBirthday());
        person.setHobbies(personDto.getHobbiesDto());
        person.setEmail(personDto.getEmail());
        person.setAddress(address);

        return person;
    }

    private void setPersonProperties(long id, PersonDto personDto) {

        String[] resultStringName = isFullNameCorrect(personDto);
        Address address = getAddressDto(personDto);

        Person person = this.getPerson(id);
//        Person person = new Person();
//        person.setId(personDto.getId());
        person.setPassword(personDto.getPassword());
        person.setFirstName(resultStringName[0]);
        person.setLastName(resultStringName[1]);
        person.setBirthday(personDto.getBirthday());
        person.setHobbies(personDto.getHobbiesDto());
        person.setEmail(personDto.getEmail());
        person.setAddress(address);
    }

    private Address getAddressDto(PersonDto personDto) {
        return new Address(personDto.getAddressDto().get(0).getId(),
                personDto.getAddressDto().get(0).getCity(),
                personDto.getAddressDto().get(0).getCountry(),
                personDto.getAddressDto().get(0).getStreet(),
                personDto.getAddressDto().get(0).getBuild());
    }

    @NotNull
    private String[] isFullNameCorrect(PersonDto personDto) {
        String stringName = personDto.getFullName();
        String[] resultStringName = stringName.split(" ");
        if (resultStringName.length != 2) {
            throw new RuntimeException("Incorrect Full Name");
        }
        return resultStringName;
    }

    private void isAddressSpecify(PersonDto personDto) {
        List<AddressDto> addressDto = personDto.getAddressDto();
        if (addressDto == null || addressDto.isEmpty()) {
            throw new RuntimeException("Please specify address");
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
    public void updatePerson(@Min(1) long id, PersonDto personDto) {
        //final Optional<Person> personById = this.getPersonById(id);
        if (this.isPersonExist(id)) {

            isAddressSpecify(personDto);

            log.info("We got such person {}", personDto);

            setPersonProperties(id, personDto);

        } else {
            throw new RuntimeException("Abra kadabra exception");
        }
    }

    @Override
    public Optional<Person> getOptionalPersonById(@Min(1) long id) {
        return personDAO.getPersons().stream().filter(x -> x.getId() == id).findFirst();
    }

    @Override
    public Person getPerson(@Min(1) long id) {
        List<Person> persons = personDAO.getPersons();
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        throw new RuntimeException(String.format("Client was not found with id [%d] ", id));
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

            isAddressSpecify(personDto);
            log.info("We got such person {}", personDto);

            Person person = setPersonProperties(personDto);
//            person.setId(personDto.getId());
//            person.setPassword(personDto.getPassword());
//            person.setFirstName(resultStringName[0]);
//            person.setLastName(resultStringName[1]);
//            person.setBirthday(personDto.getBirthday());
            personDAO.savePerson(person);
        }
    }

    @Override
    public void deletePerson(@Min(1) long id) {
        if (isPersonExist(id)) {
            List<Person> persons = personDAO.getPersons();
            for (Person result : persons) {
                if (result.getId() == id) {
                    personDAO.removePerson(result);
                    return;
                }
            }
        } else {
            throw new RuntimeException("Can`t delete... this Person don`t exist");
        }
    }
}
