package ru.ya.olganow.contrylist.service;


import ru.ya.olganow.contrylist.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> allCountries();

    Country countryByIsoCode (String isoCode );
}