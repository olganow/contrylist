package ru.ya.olganow.contrylist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public record CreateCountryRequest(
        String name,

        @JsonProperty("isoCode")
        String isoCode,

        JsonNode geometry  // Принимаем JsonNode, а не String
) {
}