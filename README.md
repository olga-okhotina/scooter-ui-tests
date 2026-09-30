# Yandex Scooter — UI Test Automation

UI tests for the [Yandex Scooter](https://qa-scooter.praktikum-services.ru) rental web service.

## Tech stack
Java 11 · Selenium 4 · JUnit 5 · WebDriverManager · Maven

## Test coverage
- **FAQ section** — each question in the accordion opens the correct answer (parameterized test)
- **Order flow** — end-to-end scooter order with different customer data, started from both "Order" buttons (top and bottom of the page)

## Architecture
- **Page Object pattern** (`MainPage`, `OrderPage`)
- **Parameterized tests** (`@CsvSource`, `@MethodSource`) — one test method covers many data sets
- **Driver factory** with a JUnit 5 extension for browser setup and teardown

## Run tests
bash
mvn clean test                     # Chrome (default)
mvn clean test -Dbrowser=firefox   # Firefox
