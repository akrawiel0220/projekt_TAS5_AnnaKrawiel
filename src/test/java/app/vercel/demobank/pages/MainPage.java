package app.vercel.demobank.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;


    @FindBy(css = "a[href='pulpit.html']")
    private WebElement pulpitTab;

    @FindBy(css = "user_name")
    private WebElement accountName;

    @FindBy(id = "widget_1_transfer_receiver")
    private WebElement receiverDropdown;

    @FindBy(id = "widget_1_transfer_amount")
    private WebElement transferAmount;

    @FindBy(id = "widget_1_transfer_title")
    private WebElement transferTitle;

    @FindBy(id = "execute_btn")
    private WebElement executeBtn;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(pulpitTab));
    }

    @Step("Strona główna wyświetla sie")
    public boolean isDisplayed() {
        return pulpitTab.isDisplayed();
    }

    @Step("Pobierana jest nazwa posiadacza konta")
    public String getAccountName() {
        return accountName.getText();
    }

    @Step("Wybierany jest odbiorca z listy rozwijaneh")
    public String selectReceiverName() {
        Select select = new Select(receiverDropdown);
        select.selectByValue("2");
        return select.getFirstSelectedOption().getText();
    }

    @Step("Wpisujemy kwote którą chcemy przekazać w przelewie")
    public void setTransferAmount(String amountToSent) {
        transferAmount.clear();
        transferAmount.sendKeys(amountToSent);
    }

    @Step("Pobierz kwote którą chcemy przekazać w przelewie")
    public String getTransferAmount() {
        ((JavascriptExecutor) driver).executeScript("document.activeElement.blur();");
        return transferAmount.getAttribute("value");
    }

    @Step("Wpisujemy tytuł przelewu i zwracamy wpisany tekstu")
    public String setAndGetTransferTitle(String titleTransferText) {
        transferTitle.clear();
        transferTitle.sendKeys(titleTransferText);
        return transferTitle.getAttribute("value");
    }

    @Step("Klikamy w przycisk 'wykonaj'")
    public TransferModalPage clickExecuteButton() {
        executeBtn.click();
        return new TransferModalPage(driver);
    }

    @Step("Sprawdzamy obowiązkowość uzupełnienia pól dla szybkiego przelewu")
    public TransferModalPage clickExecu() {
        executeBtn.click();
        return new TransferModalPage(driver);
    }

}
