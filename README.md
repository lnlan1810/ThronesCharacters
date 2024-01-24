Мультимодульное приложение с разделением на 3 основных типа:
 — App
 — Core
 — Feature

API: https://thronesapi.com/ 
Приложение подключается к API персонажей Игры престолов, чтобы получить список персонажей Игры престолов.

- Чистая архитектура с шаблоном MVI на уровне представления

Библиотеки использовала:
- Compose
- Kotlin Coroutines & Kotlin Flow
- Room
- Retrofit + okhttp
- Hilt 
- Coil
- Detekt 

Cicd (GitHub Actions):
— запуск детекта
— запуск юниттестов
— загрузка билда на firebase app distribution

- подключить проект к firebase: analytics, crashlytics, performance, notifications

- написать юнит-тесты на use-case

- версионирование зависимостей через toml файл

