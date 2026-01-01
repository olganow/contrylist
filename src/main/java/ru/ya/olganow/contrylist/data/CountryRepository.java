package ru.ya.olganow.contrylist.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;
//Это тоже бин

public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {

    // Кастомный метод для поиска по ISO коду
    @Query("SELECT c FROM CountryEntity c WHERE c.isoCode = :isoCode")
    Optional<CountryEntity> findByIsoCode(@Param("isoCode") String isoCode);

    // Метод для проверки существования страны по ISO коду
    boolean existsByIsoCode(String isoCode);
}