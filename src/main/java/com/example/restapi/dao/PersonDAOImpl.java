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
            new Person(1, "John", "", "123",
                    new Address(1, "Canada", "Toronto", "Lawbest", 11),
                    LocalDate.of(2000, Month.JANUARY, 21)),

            new Person(2, "Mike", "Fabrigas", "1234",
                    new Address(2, "USA", "Los-Angeles", "Holywood", 22),
                    LocalDate.of(2001, Month.FEBRUARY, 22)),

            new Person(3, "Asafatii", "Aftanas", "1234",
                    new Address(3, "Ukraine", "Kyiv", "Khrechatyk", 33),
                    LocalDate.of(2002, Month.MARCH, 23)),

            new Person(4, "Arnold", "Schwartzneger", "12345",
                    new Address(4, "USA", "Mayami", "Varchar", 44),
                    LocalDate.of(2003, Month.APRIL, 24)),

            new Person(5, "Nord", "Cotovich", "12345",
                    new Address(5, "Ukraine", "Lviv", "Holovatskogo", 55),
                    LocalDate.of(2004, Month.MAY, 25)),

            new Person(6, "Chy", "Va", "123456",
                    new Address(6, "China", "Shanghai", "Big China Wall", 66),
                    LocalDate.of(2005, Month.JUNE, 26)),

            new Person(7, "Austina", "Trinkas", "1234567",
                    new Address(7, "Doichland", "Drezden", "Krauter", 77),
                    LocalDate.of(2006, Month.JULY, 27)),

            new Person(8, "Sebastian", "Developer", "12345678",
                    new Address(8, "Australia", "Singapore", "Jasalinte", 88),
                    LocalDate.of(2007, Month.AUGUST, 28)),

            new Person(9, "Katalina", "Tornado", "123456789",
                    new Address(9, "Mexico", "Porkel", "Pleaser", 99),
                    LocalDate.of(2008, Month.SEPTEMBER, 29)),

            new Person(10, "Abracham", "Prshetko", "1234567890",
                    new Address(10, "Poland", "Lublin", "Olerty", 100),
                    LocalDate.of(2009, Month.NOVEMBER, 30))));

    @Override
    public void savePerson(Person person) {
        personList.add(person);
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
}
