package ru.ya.olganow.contrylist.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ya.olganow.contrylist.domain.Country;
import ru.ya.olganow.contrylist.dto.CreateCountryRequest;
import ru.ya.olganow.contrylist.dto.UpdateCountryNameRequest;
import ru.ya.olganow.contrylist.service.CountryService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/countries")
@CrossOrigin(origins = "*")  // Для тестирования из браузера/Postman
public class CountryController {

    private final CountryService countryService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CountryController(CountryService countryService, ObjectMapper objectMapper) {
        this.countryService = countryService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/all")
    public List<Country> getAllCountries() {
        return countryService.allCountries();
    }

    @GetMapping("/{isoCode}")
    public ResponseEntity<Country> getCountryByIsoCode(@PathVariable String isoCode) {
        Country country = countryService.countryByIsoCode(isoCode.toUpperCase());
        if (country != null) {
            return ResponseEntity.ok(country);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> addCountry(@RequestBody CreateCountryRequest request) {
        try {
            // Валидация
            if (request.name() == null || request.name().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Country name is required"));
            }
            if (request.isoCode() == null || request.isoCode().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("ISO code is required"));
            }

            // Преобразуем geometry в строку JSON, если она есть
            String geometryJson = null;
            if (request.geometry() != null) {
                geometryJson = objectMapper.writeValueAsString(request.geometry());
            }

            // Создаем Country объект
            Country country = new Country(
                    null,  // id будет сгенерирован в БД
                    request.name().trim(),
                    request.isoCode().toUpperCase().trim(),
                    new Date(),
                    geometryJson
            );

            Country created = countryService.addCountry(country);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();  // Для отладки
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid request: " + e.getMessage()));
        }
    }

    @PatchMapping("/{isoCode}")
    public ResponseEntity<?> updateCountryName(
            @PathVariable String isoCode,
            @RequestBody UpdateCountryNameRequest request) {

        if (request == null || request.newName() == null || request.newName().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("New name is required"));
        }

        Country updated = countryService.updateCountryName(
                isoCode.toUpperCase(),
                request.newName().trim()
        );

        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Country with ISO code " + isoCode + " not found"));
    }

    // DTO для ошибок
    public record ErrorResponse(String message) {}
}