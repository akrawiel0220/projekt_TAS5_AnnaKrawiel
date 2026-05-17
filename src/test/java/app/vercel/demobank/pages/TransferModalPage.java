package app.vercel.demobank.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferModalPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//span[text()='Przelew wykonany']/following::div/p")
    private WebElement sumTexts;

    @FindBy(xpath = "//button[@data-testid='close-button']/span")
    private WebElement okButton;

    public TransferModalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @Step("Sprawdzenie czy w podsumowaniu widnieją wcześniej wprawdzone teksty")
    public String getAllSummaryTexts() {
        String zmienna = sumTexts.getText();
        return zmienna;
    }

    @Step("Kliknięcie przyciku 'Ok'")
    public void clickOkButton() {
        okButton.click();
    }

    @Step("Sprawdzenie czy okno modalne wyświetla sie")
    public boolean checkIfModalWindowIsDisplayed() {
        wait.until(ExpectedConditions.invisibilityOf(okButton));
        try {
            return okButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

