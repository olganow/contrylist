package ru.ya.olganow.contrylist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ya.olganow.contrylist.domain.Country;
import ru.ya.olganow.contrylist.service.CountryService;

import java.util.List;

// Аннотация @RestController - комбинация @Controller + @ResponseBody
// Указывает, что этот класс является контроллером Spring MVC для обработки HTTP запросов
// Все методы автоматически возвращают данные в формате JSON (благодаря @ResponseBody)
@RestController //Это бин
// Аннотация @RequestMapping - задает базовый URL путь для всех методов контроллера
// Все URL в этом контроллере будут начинаться с "api/photo"
@RequestMapping("api/photo")
public class CountryController {
    // Приватное финальное поле для хранения ссылки на сервис работы с фотографиями
    // final гарантирует, что ссылка будет инициализирована в конструкторе и не изменится
    private final CountryService photoService;

    // Аннотация @Autowired - указывает Spring'у внедрить зависимость (Dependency Injection)
    // Spring автоматически найдет подходящий бин типа PhotoService и передаст его в конструктор
    @Autowired
    // Конструктор класса, принимающий PhotoService в качестве параметра
    // Это предпочтительный способ инъекции зависимостей (constructor injection)
    public CountryController(CountryService photoService){
        // Присвоение переданного сервиса полю класса
        this.photoService = photoService;
    }

    // Аннотация @GetMapping - указывает, что метод обрабатывает HTTP GET запросы
    // "/all" добавляется к базовому пути контроллера, итоговый путь: "/api/photo/all"
    //http://127.0.0.1:8283/api/countries/all
    @GetMapping("/all")
    // Метод обработки запроса, возвращает список объектов Photo
    // Spring автоматически преобразует List<Photo> в JSON
    public List<Country> all(){
        // Вызов метода сервиса для получения всех фотографий и возврат результата
        return photoService.allCountries();
    }

}
