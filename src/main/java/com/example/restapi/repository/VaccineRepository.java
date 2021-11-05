package com.example.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restapi.model.Vaccine;

import java.util.UUID;

public interface VaccineRepository extends JpaRepository<Vaccine, UUID> {



}
