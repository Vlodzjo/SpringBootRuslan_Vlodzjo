package com.example.restapi.converter;

import com.example.restapi.dao.PersonDAOImpl;
import com.example.restapi.dto.PersonDto;
import com.example.restapi.model.Address;
import com.example.restapi.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PersonDtoToPersonConverter implements Converter<PersonDto, Person> {

    private final PersonDAOImpl personDAO;

    @Override
    public Person convert(PersonDto personDto) {
        String stringName = personDto.getFullName();
        String[] resultStringName = stringName.split(" ");
        if (resultStringName.length != 2) {
            throw new RuntimeException("Incorrect Full Name");
        } else {
            Address address = new Address(personDto.getAddressDto().get(0).getId(),
                    personDto.getAddressDto().get(0).getCity(),
                    personDto.getAddressDto().get(0).getCountry(),
                    personDto.getAddressDto().get(0).getStreet(),
                    personDto.getAddressDto().get(0).getBuild());
            Person person;
            if (personDto.getId() == null) {
                person = new Person();
                person.setId(UUID.randomUUID());
            } else {
                person = personDAO.getPerson(personDto.getId());
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
    }
}
