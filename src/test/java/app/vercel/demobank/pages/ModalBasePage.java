package app.vercel.demobank.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModalBasePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ModalBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        PageFactory.initElements(this.driver, this);
    }

    @Step("Pobiera cały tekst")
    public String getAllSummaryTexts(WebElement element) {
        return element.getText();
    }

    @Step("Kliknięcie przyciku")
    public void clickButton(WebElement element) {
        element.click();
    }

    @Step("Sprawdzenie czy okno modalne wyświetla sie")
    public boolean modalWindowIsDisplayed(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
