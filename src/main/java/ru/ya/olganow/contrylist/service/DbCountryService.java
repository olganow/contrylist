package ru.ya.olganow.contrylist.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ya.olganow.contrylist.data.CountryEntity;
import ru.ya.olganow.contrylist.data.CountryRepository;
import ru.ya.olganow.contrylist.domain.Country;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class DbCountryService implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public DbCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        System.out.println("Repository class: " + countryRepository.getClass());
        System.out.println("Total countries in DB: " + countryRepository.count());
    }

    @Override
    public List<Country> allCountries() {
        return countryRepository.findAll().stream()
                .map(Country::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Country countryByIsoCode(String isoCode) {
        Optional<CountryEntity> entity = countryRepository.findAll().stream()
                .filter(country -> isoCode.equals(country.getIsoCode()))
                .findFirst();

        return entity.map(Country::fromEntity).orElse(null);
    }

    // Можно добавить дополнительные методы:

    public Country addCountry(Country country) {
        CountryEntity entity = new CountryEntity(
                country.name(),
                country.isoCode(),
                country.geometry()
        );
        CountryEntity savedEntity = countryRepository.save(entity);
        return Country.fromEntity(savedEntity);
    }

    public Country updateCountryName(String isoCode, String newName) {
        Optional<CountryEntity> entityOpt = countryRepository.findAll().stream()
                .filter(country -> isoCode.equals(country.getIsoCode()))
                .findFirst();

        if (entityOpt.isPresent()) {
            CountryEntity entity = entityOpt.get();
            entity.setName(newName);
            CountryEntity updatedEntity = countryRepository.save(entity);
            return Country.fromEntity(updatedEntity);
        }
        return null;
    }
}