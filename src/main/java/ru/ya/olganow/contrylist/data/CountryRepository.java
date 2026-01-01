package ru.ya.olganow.contrylist.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
//Это тоже бин
public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {

    Optional<CountryEntity> findByIsoCode(String isoCode);

    boolean existsByIsoCode(String isoCode);

    @Modifying
    @Transactional
    @Query("UPDATE CountryEntity c SET c.name = :name WHERE c.isoCode = :isoCode")
    int updateNameByIsoCode(@Param("isoCode") String isoCode, @Param("name") String name);

}