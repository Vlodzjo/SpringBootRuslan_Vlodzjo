package com.example.restapi.converter;

import com.example.restapi.dto.IdentificationDto;
import com.example.restapi.dto.PassportDto;
import com.example.restapi.dto.PersonDto;
import com.example.restapi.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonDtoToPersonConverter implements Converter<PersonDto, Person> {

    @Override
    public Person convert(PersonDto personDto) {
        String stringName = personDto.getFullName();
        String[] resultStringName = stringName.split(" ");
        if (resultStringName.length != 2) {
            throw new RuntimeException("Incorrect Full Name");
        } else {
            Address address = new Address(personDto.getAddressDto().getId(),
                    personDto.getAddressDto().getCity(),
                    personDto.getAddressDto().getCountry(),
                    personDto.getAddressDto().getStreet(),
                    personDto.getAddressDto().getBuild());
            PassportDto passportDto = personDto.getDocumentDto().getPassportDto();
            IdentificationDto identificationDto = personDto.getDocumentDto().getIdentificationDto();
            Document document = new Document(
                    personDto.getDocumentDto().getId(),
                    new Passport(
                            passportDto.getId(),
                            passportDto.getPassportNumberStr().toUpperCase(),
                            passportDto.getPassportNumberInt(),
                            passportDto.getSex(),
                            passportDto.getDateOfIssue(),
                            passportDto.getDateOfIssue().plusYears(10L),
                            passportDto.getAuthority()),
                    new Identification(
                            identificationDto.getId(),
                            identificationDto.getIdentificationNumber())
            );
            Person person = new Person();
            person.setId(personDto.getId());
            person.setPassword(personDto.getPassword());
            person.setFirstName(resultStringName[0]);
            person.setLastName(resultStringName[1]);
            person.setBirthday(personDto.getBirthday());
            person.setEmail(personDto.getEmail());
            person.setAddress(address);
            person.setDocuments(document);
            return person;
        }
    }
}
