package ru.ya.olganow.contrylist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ya.olganow.contrylist.domain.Country;
import ru.ya.olganow.contrylist.service.CountryService;
import ru.ya.olganow.contrylist.service.DbCountryService;

import java.util.List;

@RestController
@RequestMapping("api/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    //http://127.0.0.1:8283/api/countries/all
    @GetMapping("/all")
    public List<Country> getAllCountries() {
        return countryService.allCountries();
    }

    @GetMapping("/{isoCode}")
    public ResponseEntity<Country> getCountryByIsoCode(@PathVariable String isoCode) {
        Country country = countryService.countryByIsoCode(isoCode);
        if (country != null) {
            return ResponseEntity.ok(country);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        if (countryService instanceof DbCountryService) {
            Country created = ((DbCountryService) countryService).addCountry(country);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PatchMapping("/{isoCode}")
    public ResponseEntity<Country> updateCountryName(
            @PathVariable String isoCode,
            @RequestBody UpdateCountryNameRequest request) {

        if (countryService instanceof DbCountryService) {
            Country updated = ((DbCountryService) countryService)
                    .updateCountryName(isoCode, request.newName());

            if (updated != null) {
                return ResponseEntity.ok(updated);
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    public record UpdateCountryNameRequest(String newName) {}
}