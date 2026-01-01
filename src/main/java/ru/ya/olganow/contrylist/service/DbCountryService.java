package ru.ya.olganow.contrylist.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ya.olganow.contrylist.data.CountryEntity;
import ru.ya.olganow.contrylist.data.CountryRepository;
import ru.ya.olganow.contrylist.domain.Country;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
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
        return countryRepository.findByIsoCode(isoCode)
                .map(Country::fromEntity)
                .orElse(null);
    }

    @Override
    public Country addCountry(Country country) {
        // Проверяем, не существует ли уже страна с таким кодом
        if (countryRepository.existsByIsoCode(country.isoCode())) {
            throw new IllegalArgumentException("Country with ISO code " + country.isoCode() + " already exists");
        }

        // Создаем entity, geometry может быть null
        CountryEntity entity = new CountryEntity();
        entity.setName(country.name());
        entity.setIsoCode(country.isoCode());

        if (country.geometry() != null) {
            entity.setGeometry(country.geometry());
        }

        CountryEntity savedEntity = countryRepository.save(entity);
        return Country.fromEntity(savedEntity);
    }

    @Override
    public Country updateCountryName(String isoCode, String newName) {
        Optional<CountryEntity> entityOpt = countryRepository.findByIsoCode(isoCode);

        if (entityOpt.isPresent()) {
            CountryEntity entity = entityOpt.get();
            entity.setName(newName);
            CountryEntity updatedEntity = countryRepository.save(entity);
            return Country.fromEntity(updatedEntity);
        }
        return null;
    }

}