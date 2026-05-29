package app.vercel.demobank.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PhonePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//h1[@class='wborder'][contains(normalize-space(),'doładowanie telefonu')]")
    private WebElement phoneTab;

    @FindBy(id = "widget_1_topup_receiver")
    private WebElement receiverNumberDropdown;

    @FindBy(css = "input#widget_1_topup_amount")
    private WebElement topUpAmount;

    @FindBy(id = "widget_1_topup_agreement")
    private WebElement selectCheckboxOfRegulations;

    @FindBy(id = "execute_btn")
    private WebElement clickPhoneTopUp;

    @FindBy(id = "error_widget_1_topup_amount")
    private WebElement errorMessageAlert;

    public PhonePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(phoneTab));
    }

    @Step("Wybierany jest odbiorca (jego numer) doładowania telefonu, z listy rozwijanej")
    public String selectReceiverNumber() {
        Select select = new Select(receiverNumberDropdown);
        select.selectByValue("502 xxx xxx");
        return select.getFirstSelectedOption().getText();
    }

    @Step("Wpisujemy kwote którą chcemy doładować telefon")
    public String setTopUpAmount(String amountToSent) {
        topUpAmount.clear();
        topUpAmount.sendKeys(amountToSent);
        ((JavascriptExecutor) driver).executeScript("document.activeElement.blur();");
        return topUpAmount.getAttribute("value");
    }

    @Step("Zaznaczamy checkbox 'zapoznałem się z regulaminem i akceptuję warunki'")
    public WebElement clickCheckboxAboutRegulationsAndAgreement() {
        selectCheckboxOfRegulations.click();
        return selectCheckboxOfRegulations;
    }

    @Step("Klikamy przycisk 'doładuj telefon'")
    public TopUpPhoneModalPage clickBtnPhoneTopUp() {
        clickPhoneTopUp.click();
        return new TopUpPhoneModalPage(driver);
    }

    public boolean isErrorDisplayed() {
        try {
            return errorMessageAlert.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        if (isErrorDisplayed()) {
            return errorMessageAlert.getText().trim();
        } else {
            return "";
        }
    }
}
