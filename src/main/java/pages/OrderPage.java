package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderPage {

    private WebDriver driver;
    private By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressInput = By.xpath("//input[contains(@placeholder,'Адрес')]");
    private By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    private By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");
    private By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By rentPeriod = By.className("Dropdown-control");
    private By rentPeriodOption = By.xpath("//div[text()='сутки']");
    private By colorBlack = By.id("black");
    private By orderButton = By.xpath("//div[contains(@class,'Order_Buttons')]//button[text()='Заказать']");
    private By confirmButton = By.xpath("//button[text()='Да']");
    private By successPopup = By.xpath("//div[contains(@class,'Order_ModalHeader')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillPersonalData(String name, String surname, String address, String metro, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);

        driver.findElement(metroInput).click();

        By metroOptionDynamic = By.xpath("//div[@class='select-search__select']//*[text()='" + metro + "']");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(metroOptionDynamic))
                .click();

        driver.findElement(phoneInput).sendKeys(phone);
    }


    public void clickNextButton() {
        driver.findElement(nextButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(dateInput));
    }

    public void fillOrderDetails() {
        String tomorrow = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        driver.findElement(dateInput).sendKeys(tomorrow);
        driver.findElement(dateInput).sendKeys(Keys.ESCAPE);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("react-datepicker")));
        driver.findElement(rentPeriod).click();
        driver.findElement(rentPeriodOption).click();
        driver.findElement(colorBlack).click();
    }

    public void clickOrderButton() {
        WebElement button = driver.findElement(orderButton);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(button))
                .click();
    }


    public void clickConfirmButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(confirmButton))
                .click();
    }


    public boolean isOrderSuccessDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(successPopup))
                .isDisplayed();
    }

    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(nameInput));
    }
}
