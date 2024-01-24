Мультимодульное приложение с разделением на 3 основных типа:
- App 
- Core
- Feature
  
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
-  Запуск детекта 
-  Запуск юниттестов
-  Загрузка билда на firebase app distribution

Подключить проект к firebase: analytics, crashlytics, performance, notifications.

Написать юнит-тесты на use-case.

Версионирование зависимостей через toml файл.

