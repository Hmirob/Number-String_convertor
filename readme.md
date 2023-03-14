# Number-String convertor
## Настройка окружения
Для подготовки бд нужно выполнить скрипт `createDB.sql` и ввести логин и пароль при необходимости в `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost/text_number_converter
spring.datasource.username=postgres
spring.datasource.password=postgres
```

Приложение использует `Java 17`.

Для запуска тестового стенда нужно настроить в _Idea_ `npm 9.2` версии и `node.js 18.2.0` версии. 

Для сборки готового приложения они будут подтянуты отдельно и в настройке не нуждаются.
## Запуск тестовой среды
Для запуска тестового стенда с бэком и фронтов на отдельных портах:

Для бэка:
```
mvn clean spring-boot:run -Pdev
```

Для фронта:
```
cd app
```
```
npm start
```

или так же можно использовать сохраненные конфигурации для _Idea_.

## Запуск готового приложения
Сборка проекта через maven с `prod` профилем _Spring_ и _React_ соберутся в один `jar`

Для сборки `jar`:
```
mvn clean install -Pprod
```

Для сборки и запуска спринга
```
mvn clean spring-boot:run -Pdev
```

## Rest API
Рест для конвертации `type` либо `NumberToString` либо `StringToNumber`. 
Ответы: 
* 200 - всё ок
* 400 - опечатка в конвертируемой строке
* 500 - всё плохо
```http request
/convert?type=${type}&value=${value}
```

Возвращает react-форму для логина
реализованы только _inMemory_ пользователи 

Логин-пароль:
* _user user_
* _user2 user2_
```http request
http://localhost:8080/morda/login
```

Возвращает react-форму для конвертации
```http request
http://localhost:8080/morda
```