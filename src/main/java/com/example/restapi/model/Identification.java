package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Identification")
@Table(name = "identification")
public class Identification {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "identification_auto")
    @Column(updatable = false)
    private UUID id;

    long identificationNumber;

}
