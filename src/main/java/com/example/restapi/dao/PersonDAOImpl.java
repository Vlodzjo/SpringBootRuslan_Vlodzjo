package com.example.restapi.dao;

import com.example.restapi.model.Address;
import com.example.restapi.model.Person;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class PersonDAOImpl implements PersonDAO {

    private List<Person> personList = new ArrayList<>(Arrays.asList(
            new Person(UUID.fromString("2a1e7834-5683-406e-831b-af37d94fbc6f"),
                    "John", "Stevenson", "123",
                    new Address(UUID.fromString("abbb11e0-e041-474f-ac86-fd65231467a5"),
                            "Canada", "Toronto", "Lawbest", 11),
                    LocalDate.of(2000, Month.JANUARY, 21),
                    "john@gmail.com",
                    Arrays.asList("soccer", "football")),

            //new String[]{"soccer", "football"})
            new Person(UUID.fromString("76b41909-64c3-4d69-b19d-db8ba926b0a8"),
                    "Mike", "Fabrigas", "1234",
                    new Address(UUID.fromString("309788cf-d56b-4e97-8497-48e00676e66c"),
                            "USA", "Los-Angeles", "Holywood", 22),
                    LocalDate.of(2001, Month.FEBRUARY, 22),
                    "mike@gmail.com",
                    Arrays.asList("soccer", "football")),

            new Person(UUID.fromString("0b77fb9c-1e95-4466-a281-038c3a9ed7b2"),
                    "Asafatii", "Aftanas", "1234",
                    new Address(UUID.fromString("3073e92e-6d06-4ddc-895d-0273da162d87"),
                            "Ukraine", "Kyiv", "Khrechatyk", 33),
                    LocalDate.of(2002, Month.MARCH, 23),
                    "asafatii@gmail.com",
                    Arrays.asList("soccer", "football")),

            new Person(UUID.fromString("860140d6-51f2-4aa3-86fb-e13ec7115f70"),
                    "Arnold", "Schwartzneger", "12345",
                    new Address(UUID.fromString("c49f7954-b576-4663-a48a-d408e84dd342"),
                            "USA", "Mayami", "Varchar", 44),
                    LocalDate.of(2003, Month.APRIL, 24),
                    "arnold@gmail.com",
                    Arrays.asList("soccer", "football")),

            new Person(UUID.fromString("7f7c7cd1-7d2f-4e0d-99b7-3c8cf21e21c3"),
                    "Nord", "Cotovich", "12345",
                    new Address(UUID.fromString("d995aa61-6cdf-481e-b1b6-d52072f91b06"),
                            "Ukraine", "Lviv", "Holovatskogo", 55),
                    LocalDate.of(2004, Month.MAY, 25),
                    "{}s`~gmailcom",
                    Arrays.asList("soccer", "football")),

            new Person(UUID.fromString("1f9648a4-2afe-48fd-9fcc-36e6fad4b080"),
                    "Chy", "Va", "123456",
                    new Address(UUID.fromString("50219672-73bb-43b9-99c3-456d8dcdc921"),
                            "China", "Shanghai", "Big China Wall", 66),
                    LocalDate.of(2005, Month.JUNE, 26),
                    "chy@gmail.com",
                    Arrays.asList("soccer", "football")),

            new Person(UUID.fromString("a8234530-3ac3-45d9-807e-2bfe8c6a0389"),
                    "Austina", "Trinkas", "1234567",
                    new Address(UUID.fromString("49c2a4fa-015f-4d84-a7fd-b5bd6a708795"),
                            "Doichland", "Drezden", "Krauter", 77),
                    LocalDate.of(2006, Month.JULY, 27),
                    "austina@gmail.com",
                    Arrays.asList("soccer", "football")),

            new Person(UUID.fromString("11c9945b-90b2-4866-9f40-1868d2653bbe"),
                    "Sebastian", "Developer", "12345678",
                    new Address(UUID.fromString("5d605467-ba74-45dc-90ce-9f1120f37aa5"),
                            "Australia", "Singapore", "Jasalinte", 88),
                    LocalDate.of(2007, Month.AUGUST, 28),
                    "sebastian@gmail.com",
                    Arrays.asList("soccer", "football")),

            new Person(UUID.fromString("65fedd84-28fb-4456-ab3a-6ff1b34f6a8a"),
                    "Katalina", "Tornado", "123456789",
                    new Address(UUID.fromString("f384e20c-07c2-4417-a652-1b8511ca550a"),
                            "Mexico", "Porkel", "Pleaser", 99),
                    LocalDate.of(2008, Month.SEPTEMBER, 29),
                    "katalina@gmail.com",
                    Arrays.asList("soccer", "football")),

            new Person(UUID.fromString("69be0700-e621-4dbf-b59a-826a7ae474c0"),
                    "Abracham", "Prshetko", "1234567890",
                    new Address(UUID.fromString("8043d899-dc78-469c-9e50-67753e5ac79a"),
                            "Poland", "Lublin", "Olerty", 100),
                    LocalDate.of(2009, Month.NOVEMBER, 30),
                    "abracham@gmail.com",
                    Arrays.asList("soccer", "football"))));

    @Override
    public void savePerson(Person person) {
        personList.add(person);
    }

    @Override
    public Person getPerson(UUID id) {
        return personList.stream().filter(person -> person
                        .getId()
                        .equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Person> getPersons() {
        return personList;
    }

    @Override
    public void removePerson(Person person) {
        personList.remove(person);
    }

    @Override
    public void updatePerson(UUID id, Person person) {
        int index = 0;
        for (Person pers : personList) {
            if (pers.getId().equals(id)) {
                personList.set(index, person);
            } else {
                index++;
            }
        }
    }
}
