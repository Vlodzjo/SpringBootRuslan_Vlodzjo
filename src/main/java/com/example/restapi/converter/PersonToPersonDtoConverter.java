package com.example.restapi.converter;

import com.example.restapi.dto.*;
import com.example.restapi.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonToPersonDtoConverter implements Converter<Person, PersonDto> {

    @Override
    public PersonDto convert(Person person) {
        LocalDate dateNow = LocalDate.now();
        PersonDto personDto = new PersonDto();
        Address address = person.getAddress();
        Document document = person.getDocuments();
        Passport passport = person.getDocuments().getPassport();
        Identification identification = person.getDocuments().getIdentification();
        if (address != null) {
            AddressDto addressDto = new AddressDto(address.getId(), address.getCountry(),
                    address.getCity(), address.getStreet(), address.getBuild());
            personDto.setAddressDto(addressDto);
        }

        if (!person.getVaccines().isEmpty()) {
            List<PersonVaccineDto> personVaccineDtos = new ArrayList<>();
            List<PersonVaccine> vaccines = person.getVaccines();
            for (PersonVaccine personVaccine : vaccines) {
                PersonVaccineDto personVaccineDto = new PersonVaccineDto();
                personVaccineDto.setName(personVaccine.getVaccine().getName());
                personVaccineDto.setDateOfVaccine(personVaccine.getCreatedOn());
                personVaccineDtos.add(personVaccineDto);
            }
            personDto.setVaccines(personVaccineDtos);
        }

        DocumentDto documentDto = new DocumentDto(
                document.getId(),
                new PassportDto(
                        passport.getId(),
                        passport.getPassportNumberStr().toUpperCase(),
                        passport.getPassportNumberInt(),
                        passport.getSex(),
                        passport.getDateOfIssue(),
                        passport.getDateOfExpiry(),
                        passport.getAuthority()
                ),
                new IdentificationDto(
                        identification.getId(),
                        identification.getIdentificationNumber())
        );

        personDto.setId(person.getId());
        personDto.setFullName(person.getFirstName() + " " + person.getLastName());

        personDto.setAge(Period.between(person.getBirthday(), dateNow).getYears());
        personDto.setPassword(person.getPassword());
        personDto.setEmail("test@gmail.com");
        personDto.setBirthday(person.getBirthday());
        personDto.setEmail(person.getEmail());
        personDto.setDocumentDto(documentDto);

        return personDto;
    }
}
