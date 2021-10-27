package com.example.restapi.dao;

import com.example.restapi.model.Address;
import com.example.restapi.model.Person;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO {

    private List<Person> personList = new ArrayList<>(Arrays.asList(
            new Person(0, "John", "Stevenson", "123",
                    new Address(0, "Canada", "Toronto", "Lawbest", 11),
                    LocalDate.of(2000, Month.JANUARY, 21),
                    "john@gmail.com",
                    new String[]{"soccer", "football"}),

            new Person(1, "Mike", "Fabrigas", "1234",
                    new Address(1, "USA", "Los-Angeles", "Holywood", 22),
                    LocalDate.of(2001, Month.FEBRUARY, 22),
                    "mike@gmail.com",
                    new String[]{"soccer2", "football2"}),

            new Person(2, "Asafatii", "Aftanas", "1234",
                    new Address(2, "Ukraine", "Kyiv", "Khrechatyk", 33),
                    LocalDate.of(2002, Month.MARCH, 23),
                    "asafatii@gmail.com",
                    new String[]{"soccer3", "football3"}),

            new Person(3, "Arnold", "Schwartzneger", "12345",
                    new Address(3, "USA", "Mayami", "Varchar", 44),
                    LocalDate.of(2003, Month.APRIL, 24),
                    "arnold@gmail.com",
                    new String[]{"soccer4", "football4"}),

            new Person(4, "Nord", "Cotovich", "12345",
                    new Address(4, "Ukraine", "Lviv", "Holovatskogo", 55),
                    LocalDate.of(2004, Month.MAY, 25),
                    "{}s`~gmailcom",
                    new String[]{"soccer5", "football5"}),

            new Person(5, "Chy", "Va", "123456",
                    new Address(5, "China", "Shanghai", "Big China Wall", 66),
                    LocalDate.of(2005, Month.JUNE, 26),
                    "chy@gmail.com",
                    new String[]{"soccer6", "football6"}),

            new Person(6, "Austina", "Trinkas", "1234567",
                    new Address(6, "Doichland", "Drezden", "Krauter", 77),
                    LocalDate.of(2006, Month.JULY, 27),
                    "austina@gmail.com",
                    new String[]{"soccer7", "football7"}),

            new Person(7, "Sebastian", "Developer", "12345678",
                    new Address(7, "Australia", "Singapore", "Jasalinte", 88),
                    LocalDate.of(2007, Month.AUGUST, 28),
                    "sebastian@gmail.com",
                    new String[]{"soccer8", "football8"}),

            new Person(8, "Katalina", "Tornado", "123456789",
                    new Address(8, "Mexico", "Porkel", "Pleaser", 99),
                    LocalDate.of(2008, Month.SEPTEMBER, 29),
                    "katalina@gmail.com",
                    new String[]{"soccer9", "football9"}),

            new Person(9, "Abracham", "Prshetko", "1234567890",
                    new Address(9, "Poland", "Lublin", "Olerty", 100),
                    LocalDate.of(2009, Month.NOVEMBER, 30),
                    "abracham@gmail.com",
                    new String[]{"soccer10", "football10"})));

    @Override
    public void savePerson(Person person) {
        personList.add((int) person.getId(), person);
    }

    @Override
    public Person getPerson(long id) {
        return personList.get((int) id);
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
    public void updatePerson(long id, Person person) {
        personList.set((int) id, person);
    }
}
