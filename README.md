Інструкція по використанню:

1. Для створення API використовувались: Java 8, REST API, Spring Boot 2.2.1 , H2DB, Maven.
2. Після запуску сервісу - заповнити  базу даних необхідно через будь який інструмент тестування API (PostmanInsomnia)
    в body, методом POST (http://localhost:8080/registry/save), шаблони даних є в файлі data.json, або ж
    стоворити свої json файли.
3. За адресою http://localhost:8080/data/login.jsp є графічне відображення бази даних. Дані для входу є в файлі
     src/main/resources - application.properties
     http://prntscr.com/qarfge
4. Функції сортування або фільтрації перевіряти в тому ж Postman, або ж в браузері (GET методи).
