package tests;

import config.DriverExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.OrderPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {

    @RegisterExtension
    final DriverExtension extension = new DriverExtension();

    static Stream<Object[]> orderData() {
        return Stream.of(
                new Object[]{"Ольга", "Самсонова", "Москва", "Сокольники", "89111111111", 0},
                new Object[]{"Иван", "Иванов", "Санкт-Петербург", "Черкизовская", "89222222222", 1}
        );
    }

    @ParameterizedTest
    @MethodSource("orderData")
    void testOrderFlow(String name, String surname, String address, String metro, String phone, int buttonType) {

        WebDriver driver = extension.getDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        var mainPage = new MainPage(driver);
        mainPage.acceptCookies();

        if (buttonType == 0) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonMiddle();
        }

        OrderPage orderPage = new OrderPage(driver);
        orderPage.waitForLoad();

        // Заполняем персональные данные
        orderPage.fillPersonalData(name, surname, address, metro, phone);
        orderPage.clickNextButton();

        // Заполняем детали заказа (дата, срок аренды, цвет)
        orderPage.fillOrderDetails();

        // Кликаем "Заказать" и подтверждаем в модальном окне
        orderPage.clickOrderButton();
        orderPage.clickConfirmButton();

        // Проверяем, что заказ успешно создан
        assertTrue(orderPage.isOrderSuccessDisplayed());
    }
}
