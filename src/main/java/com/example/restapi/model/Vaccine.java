package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Vaccine")
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(
        name = "vaccine",
        uniqueConstraints = {@UniqueConstraint(name = "vaccine_name_unique", columnNames = "name")})
public class Vaccine {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "vaccine_auto")
    @Column(updatable = false)
    private UUID id;

    @Column(name = "name", updatable = false)
    @NaturalId
    private String name;

    @Column(name = "description", updatable = false)
    private String description;

    @Column(name = "based_date", updatable = false)
    private LocalDate basedDate;

    @Column(name = "made_in", updatable = false)
    private String madeIn;

    public Vaccine(String name, String description, LocalDate basedDate, String madeIn) {
        this.name = name;
        this.description = description;
        this.basedDate = basedDate;
        this.madeIn = madeIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaccine vaccine = (Vaccine) o;
        return Objects.equals(name, vaccine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
