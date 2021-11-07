package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "PersonVaccine")
@Table(name = "person_vaccine")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonVaccine {

    @EmbeddedId
    private PersonVaccineId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    @ToString.Exclude
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vaccineId")
    @ToString.Exclude
    private Vaccine vaccine;

    @Column(name = "vaccinated_on")
    private LocalDate createdOn;

    public PersonVaccine(Person person, Vaccine vaccine) {
        this.person = person;
        this.vaccine = vaccine;
        this.id = new PersonVaccineId(person.getId(), vaccine.getId());
        this.createdOn = LocalDate.now();
    }

    //Getters and setters omitted for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PersonVaccine that = (PersonVaccine) o;
        return Objects.equals(person, that.person) &&
                Objects.equals(vaccine, that.vaccine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, vaccine);
    }

}
