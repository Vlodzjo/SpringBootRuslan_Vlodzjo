package com.example.restapi.converter;

import com.example.restapi.dto.AddressDto;
import com.example.restapi.dto.PersonDto;
import com.example.restapi.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PersonToPersonDtoConverter implements Converter<Person, PersonDto> {

    @Override
    public PersonDto convert(Person person) {
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
}
