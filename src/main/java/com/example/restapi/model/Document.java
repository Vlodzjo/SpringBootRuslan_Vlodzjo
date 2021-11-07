package com.example.restapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Document")
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "document_auto")
    @Column(updatable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identification_id", referencedColumnName = "id")
    private Identification identification;

}
