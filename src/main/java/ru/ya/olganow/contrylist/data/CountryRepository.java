package ru.ya.olganow.contrylist.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
//Это тоже бин

public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {

}
