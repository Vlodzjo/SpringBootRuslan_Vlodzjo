package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    private String name;

    @Column(name = "description", updatable = false)
    private String description;

    @Column(name = "based_date", updatable = false)
    private LocalDate basedDate;

    @Column(name = "made_in", updatable = false)
    private String madeIn;

    @OneToMany(mappedBy = "vaccine")
    private Set<PersonVaccine> vaccinationDays;

    public Vaccine(String name, String description, LocalDate basedDate, String madeIn) {
        this.name = name;
        this.description = description;
        this.basedDate = basedDate;
        this.madeIn = madeIn;
    }
}
