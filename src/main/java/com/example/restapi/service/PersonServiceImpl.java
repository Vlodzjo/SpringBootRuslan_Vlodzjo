package com.example.restapi.service;

import com.example.restapi.dao.PersonDAO;
import com.example.restapi.dto.AddressDto;
import com.example.restapi.dto.PersonDto;
import com.example.restapi.exception.UserAlreadyExistsException;
import com.example.restapi.model.Address;
import com.example.restapi.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    @Autowired
    public PersonDAO personDAO;
    @Autowired
    public ConversionService conversionService;

    private Person setPersonProperties(PersonDto personDto) {
        return setPersonProperties(null, personDto);
    }

    private Person setPersonProperties(Long id, PersonDto personDto) {
        String[] resultStringName = isFullNameCorrect(personDto);
        Address address = getAddressDto(personDto);
        Person person;
        if (id == null) {
            person = new Person();
            person.setId(personDto.getId());
        } else {
            person = this.getPerson(id);
        }
        person.setPassword(personDto.getPassword());
        person.setFirstName(resultStringName[0]);
        person.setLastName(resultStringName[1]);
        person.setBirthday(personDto.getBirthday());
        person.setHobbies(personDto.getHobbiesDto());
        person.setEmail(personDto.getEmail());
        person.setAddress(address);
        return person;
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
    }

    @Override
    public void updatePerson(@NotNull long id, PersonDto personDto) {
//        final Optional<Person> personById = this.getPersonById(id);
        if (this.isPersonExist(id)) {

            isAddressSpecify(personDto);

            log.info("We got such person {}", personDto);

//            setPersonProperties(id, personDto);
            personDto.setId(id);
            conversionService.convert(personDto, Person.class);

        } else {
            throw new RuntimeException("Abra kadabra exception person is not present");
        }
    }

    @Override
    public Optional<Person> getOptionalPersonById(@NotNull long id) {
        return personDAO.getPersons().stream().filter(x -> x.getId() == id).findFirst();
    }

    @Override
    public Person getPerson(@NotNull long id) {
        if (isPersonExist(id)) {
            return personDAO.getPerson(id);
        } else {
            throw new RuntimeException(String.format("Client was not found with id [%d] ", id));
        }
    }

    @Override
    public List<PersonDto> getPersons() {
        List<Person> persons = personDAO.getPersons();
        List<PersonDto> result = new ArrayList<>();
        for (Person person : persons) {
            PersonDto converted = conversionService.convert(person, PersonDto.class);
            result.add(converted);
        }
        return result;
    }

    @Override
    public void createPerson(PersonDto personDto) {
        if (isPersonExist(personDto.getId())) {
            throw new UserAlreadyExistsException(String.format("Person with id [%d] is present", personDto.getId()));
        } else {

            isAddressSpecify(personDto);
            log.info("We got such person {}", personDto);

//            Person person = setPersonProperties(personDto);
            personDto.setId(null);
            Person person = conversionService.convert(personDto, Person.class);

            personDAO.savePerson(person);
        }
    }

    @Override
    public void deletePerson(@NotNull long id) {
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
