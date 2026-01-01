package ru.ya.olganow.contrylist.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;


@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        // Используем формат даты, совместимый с ISO и базой данных
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return om;
    }

}