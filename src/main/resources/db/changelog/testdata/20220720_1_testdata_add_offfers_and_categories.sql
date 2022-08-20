INSERT INTO category (name, description)
VALUES ('Zabawki', 'Super prezenty dla dla wszyskich dzieci'),
       ('Elektronika', 'Najlepszy sprzęt elektroniczny po przystępnych cenach'),
       ('Meble', 'Ładne i funkcjonalne porzedmioty do domu'),
       ('Moto', 'Auta i części samochodowe');

INSERT INTO offer (title, description, img_url, price, category_id)
VALUES ('Telewizor', 'Telewizor Samsung LED 55 cali 4K', 'http://ladnytelewizor1.jpg', 4599.00, 2),
       ('Kino domowe', 'Super kino domowe marki WbijamCięWFotel - produkt unikatowy', 'http://woow.jpg', 23999.00, 2),
       ('Fotel kinomana', 'Mega fotel dla wszyskich lubiących wygodnie spędzać czas przed TV-xem', 'http://miekko.jpg', 430, 3),
       ('Klocki lego - kapitan bomba #3', 'unikalny zestaw klocków lego "pojadz asenizacyjny Łęciny - czyli samo gęste"', 'http://samogeste.jpg', 500.00, 1),
       ('Zapach - prosto z salonu', 'odświerzacz powietrza do samochodu - zapach nowości', 'http://noweeauto.jpg', 4.99, 4);