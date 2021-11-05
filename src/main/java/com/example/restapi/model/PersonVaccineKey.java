package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PersonVaccineKey implements Serializable {

    @Column(name = "person_id")
    private UUID personId;

    @Column(name = "vaccine_id")
    private UUID vaccineId;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        return object instanceof PersonVaccineKey;
    }

}
