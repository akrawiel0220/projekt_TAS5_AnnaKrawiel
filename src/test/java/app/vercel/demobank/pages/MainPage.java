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


    @FindBy(css = "div.category.active > a[href='pulpit.html']")
    private WebElement pulpitTab;

    @FindBy(xpath = "//a[@href='phone.html'][contains(normalize-space(),'doładowanie telefonu')]")
    private WebElement phoneTab;

    @FindBy(xpath = "//a[@href='reports.html'][contains(normalize-space(),'raporty')]")
    private WebElement reportsTab;

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

    @FindBy(id = "show_messages")
    private WebElement messageAboutSuccessTransfer;

    @FindBy(id = "error_widget_1_transfer_receiver")
    private WebElement fastTransferFirstErrorMessage;

    @FindBy(id = "error_widget_1_transfer_amount")
    private WebElement fastTransferSecondErrorMessage;

    @FindBy(id = "error_widget_1_transfer_title")
    private WebElement fastTransferThirdErrorMessage;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(pulpitTab));
    }

    @Step("Klikamy w zakładke 'doładowanie telefonu'")
    public PhonePage clickPhoneTopUpTab() {
        phoneTab.click();
        return new PhonePage(driver);
    }

    @Step("Klikamy w zakładke 'raporty'")
    public ReportsPage clickReportTab() {
        reportsTab.click();
        return new ReportsPage(driver);
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

    @Step("Wpisujemy tytuł przelewu i zwracamy wpisany tekst")
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

    @Step("Weryfikacja wiadomości o przelewie")
    public String getInfoTextAboutSuccessTransfer() {
        return messageAboutSuccessTransfer.getText();
    }

    @Step("Sprawdzamy obowiązkowość uzupełnienia pól dla szybkiego przelewu")
    public boolean checkIfErrorMessagesVisible(long timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.visibilityOf(fastTransferFirstErrorMessage));
            wait.until(ExpectedConditions.visibilityOf(fastTransferSecondErrorMessage));
            wait.until(ExpectedConditions.visibilityOf(fastTransferThirdErrorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
