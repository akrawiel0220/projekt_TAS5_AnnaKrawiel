package app.vercel.demobank.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopUpPhoneModalPage extends ModalBasePage {

    @FindBy(xpath = "//span[text()='Doładowanie wykonane']/following::div/p")
    private WebElement sumTexts;

    @FindBy(xpath = "//button[@data-testid='close-button']/span")
    private WebElement okButton;

    public TopUpPhoneModalPage(WebDriver driver) {
        super(driver);
    }

    @Step("Sprawdzenie czy w podsumowaniu widnieją wcześniej wprawdzone teksty")
    public String getAllSummaryText() {
        return getAllSummaryTexts(sumTexts);
    }

    @Step("Kliknięcie przyciku 'Ok'")
    public void clickOkButton() {
        clickButton(okButton);
    }

    @Step("Sprawdzenie czy okno modalne wyświetla sie")
    public boolean checkIfModalWindowDisplayed() {
        return modalWindowIsDisplayed(okButton);
    }
}

