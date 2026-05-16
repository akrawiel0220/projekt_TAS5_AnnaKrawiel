package app.vercel.demobank.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;


    @FindBy(css = "a[href='pulpit.html']")
    private WebElement pulpitTab;

    @FindBy(css = "user_name")
    private WebElement accountName;

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

}
