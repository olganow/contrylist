package ru.ya.olganow.contrylist.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

// Аннотация @Configuration - указывает, что этот класс содержит конфигурационные настройки Spring
// Класс с такой аннотацией обрабатывается Spring'ом для создания и настройки бинов
@Configuration
public class AppConfig {

    // Аннотация @Bean - указывает, что метод создает и возвращает объект (бин),
    // который должен быть управляем Spring контейнеромPhotoController
    // Имя метода ("objectMapper") становится именем бина
    @Bean //Конфигурация
    //видео 37 минута
    public ObjectMapper objectMapper() {
        // Создание нового экземпляра ObjectMapper - библиотеки Jackson для работы с JSON
        ObjectMapper om = new ObjectMapper();
        // Настройка формата даты для ObjectMapper
        // Все даты при сериализации/десериализации JSON будут в формате "день-месяц-год"
        om.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));

        // Возврат настроенного ObjectMapper, Spring автоматически добавит его в контекст приложения
        return om;
    }
}