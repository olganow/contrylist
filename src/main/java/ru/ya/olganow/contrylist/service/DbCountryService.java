package ru.ya.olganow.contrylist.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ya.olganow.contrylist.data.CountryRepository;
import ru.ya.olganow.contrylist.domain.Country;

import java.util.List;


@Component

public class DbCountryService implements CountryService {

    @Override
    public List<Country> allCountries() {
        return List.of();
    }

    @Override
    public Country countryByIsoCode(String isoCode) {
        return null;
    }
}