package ru.ya.olganow.contrylist.domain;

import java.util.Date;
/*record - это новый тип класса в Java (начиная с Java 14 как preview, стабильно с Java 16),
 который предназначен для создания неизменяемых (immutable) классов данных.
Преимущества record:
✅ Короткий синтаксис
✅ Неизменяемость по умолчанию - Все поля final, сеттеров нет
✅ Прозрачность данных - Отличные equals(), hashCode(), toString() "из коробки"
✅ Идеален для DTO, Value Objects
Ограничения record:
❌ Нельзя наследоваться (все records - final)
❌ Нельзя наследовать другие классы (кроме Record)
❌ Все поля - final (нельзя изменить после создания)*/

public record Country(String description, Date LastModifyDate, String content){

}