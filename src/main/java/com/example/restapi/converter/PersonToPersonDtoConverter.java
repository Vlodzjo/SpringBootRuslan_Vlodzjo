package com.example.restapi.converter;

import com.example.restapi.dto.AddressDto;
import com.example.restapi.dto.PersonDto;
import com.example.restapi.dto.VaccineDto;
import com.example.restapi.model.Address;
import com.example.restapi.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PersonToPersonDtoConverter implements Converter<Person, PersonDto> {


    @Override
    public PersonDto convert(Person person) {
        LocalDate dateNow = LocalDate.now();
        PersonDto personDto = new PersonDto();
        Address address = person.getAddress();
        if (address != null) {
            AddressDto addressDto = new AddressDto(address.getId(), address.getCountry(),
                    address.getCity(), address.getStreet(), address.getBuild());
            personDto.setAddressDto(addressDto);
        }

       /* if (person.getPersonVaccines() != null) {
            List<VaccineDto> collect = person.getPersonVaccines().stream()
                    .map(v -> conversionService.convert(v.getVaccine(), VaccineDto.class))
                    .collect(Collectors.toList());
            personDto.setVaccines(collect);
        }*/

        personDto.setId(person.getId());
        personDto.setFullName(person.getFirstName() + " " + person.getLastName());

        personDto.setAge(Period.between(person.getBirthday(), dateNow).getYears());
        personDto.setPassword(person.getPassword());
        personDto.setEmail("test@gmail.com");
        personDto.setBirthday(person.getBirthday());
        personDto.setEmail(person.getEmail());

        return personDto;
    }
}
