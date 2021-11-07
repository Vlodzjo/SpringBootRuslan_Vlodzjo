package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonVaccineId implements Serializable {

    @Column(name = "person_id")
    private UUID personId;

    @Column(name = "vaccine_id")
    private UUID vaccineId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PersonVaccineId that = (PersonVaccineId) o;
        return Objects.equals(personId, that.personId) &&
                Objects.equals(vaccineId, that.vaccineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, vaccineId);
    }

}
