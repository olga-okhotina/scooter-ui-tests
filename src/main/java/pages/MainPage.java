package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;


        private By logo = By.className("Header_LogoScooter__3lsAR");
        private By orderButtonTop = By.xpath("//button[text()='Заказать']");
        private By orderStatusButton = By.className("Header_Link__1TAG7");
        private By orderButtonMiddle = By.xpath("(//button[text()='Заказать'])[2]");
        private By cookieButton = By.id("rcc-confirm-button");


    private By question = By.xpath("//div[contains(@id,'accordion__heading')]");


    private By questionByIndex(int index) {
        return By.id("accordion__heading-" + index);
    }


    private By answerByIndex(int index) {
        return By.id("accordion__panel-" + index);
    }
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickQuestion(int index) {
        WebElement question = driver.findElement(questionByIndex(index));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(question)).click();
    }

    public String getAnswerText(int index) {
        return driver.findElement(answerByIndex(index)).getText();
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonMiddle() {
        WebElement button = driver.findElement(orderButtonMiddle);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", button);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(button))
                .click();
    }
    public void acceptCookies() {
        if (driver.findElements(cookieButton).size() > 0) {
            driver.findElement(cookieButton).click();
        }
    }
}
