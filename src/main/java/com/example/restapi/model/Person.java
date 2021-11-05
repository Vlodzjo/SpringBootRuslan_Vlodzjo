package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(
        name = "person",
        uniqueConstraints = {
                @UniqueConstraint(name = "person_email_unique", columnNames = "email")
        }
)
public class Person {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "person_auto")
    @Column(updatable = false)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "person")
    private Set<PersonVaccine> personVaccines;

}
