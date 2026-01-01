-- Создание таблицы для хранения информации о странах
CREATE TABLE IF NOT EXISTS countries
(
    id                  UUID UNIQUE     NOT NULL DEFAULT uuid_generate_v1() PRIMARY KEY,
    -- Название страны
    name                VARCHAR(255)    NOT NULL,
    -- Двухбуквенный код страны (например: US, RU, CN)
    iso_code            VARCHAR(2)      NOT NULL UNIQUE,
    -- Дата последнего изменения записи
    last_modify_date    DATE            NOT NULL DEFAULT CURRENT_DATE,
    -- Геометрические данные страны (полигоны) в формате JSON
    -- Содержит координаты границ страны для отображения на карте
    geometry            JSONB
);

-- Создание индекса для быстрого поиска по коду страны
CREATE INDEX idx_countries_iso_code ON countries(iso_code);

-- Создание индекса для полнотекстового поиска по названию страны
CREATE INDEX idx_countries_name ON countries(name);