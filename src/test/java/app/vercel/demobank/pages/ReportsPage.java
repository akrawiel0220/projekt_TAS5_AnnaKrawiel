package app.vercel.demobank.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FileUtils;

import java.time.Duration;

public class ReportsPage {


    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//h1[@class='wborder'][contains(normalize-space(),'Raporty')]")
    private WebElement reportTab;

    @FindBy(xpath = "//input[@type='file'][@id='my_file_2']")
    private WebElement reportFileUploadInput;

    @FindBy(xpath = "//*[@id='uniform-my_file_2']/span[1]")
    private WebElement reportFileNameLoaded;

    @FindBy(id = "send_btn_2")
    private WebElement sendButton;

    @FindBy(id = "show_messages")
    private WebElement messageShow;


    public ReportsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(reportTab));
    }

    @Step("Wysyłamy plik json z raportem")
    public void uploadReportJsonFile(String pathReportFile) {
        String absolutePath = FileUtils.getTestResourcePath(pathReportFile);
        reportFileUploadInput.sendKeys(absolutePath);
    }

    @Step("Pobierz tytuł pliku który jest załadowany")
    public String getReportFileTitle() {
        return reportFileNameLoaded.getText();
    }

    @Step("Klikamy przycisk 'Prześlij'")
    public void clickSendButton() {
        sendButton.click();
    }

    @Step("Sprawdz czy wyświetla sie informacja o wysłanym pliku")
    public String checkIfInfoMessageIsDisplayed() {
        messageShow.isDisplayed();
        return messageShow.getText();
    }
}
