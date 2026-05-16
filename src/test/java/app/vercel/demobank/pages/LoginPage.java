package app.vercel.demobank.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = ".logo.login")
    private WebElement loginLogo;

    @FindBy(id = "login_id")
    private WebElement username;

    @FindBy(css = "[data-testid='password-input']")
    private WebElement password;

    @FindBy(id = "login-btn")
    private WebElement button;

    @FindBy(id = "error_login_id")
    private WebElement errorMessageLogin;

    @FindBy(css = "[data-testid='error-login-password']")
    private WebElement errorMessagePassword;

    @FindBy(tagName = "body")
    private WebElement body;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    public LoginPage open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(loginLogo));
        return this;
    }

    @Step("Wyjdz ze skupienia(focus) na danym elemencie strony")
    public void clickOutside() {
        ((JavascriptExecutor) driver).executeScript("document.activeElement.blur();");
    }

    @Step("Użytkownik wpisuje poprawne dane logowania do konta bankowego")
    public MainPage loginValid(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        button.click();
        return new MainPage(driver);
    }

    @Step("Użytkownik wpisuje za krótką nazwe Identyfikatora")
    public LoginPage loginInvalidUsername(String username) {
        this.username.clear();
        this.username.sendKeys(username);
        clickOutside();
        return this;
    }

    @Step("Użytkownik wpisuje za krótkie hasło")
    public LoginPage loginInvalidPassword(String username, String passwd) {
        this.username.clear();
        this.username.sendKeys(username);
        password.clear();
        password.sendKeys(passwd);
        clickOutside();
        return this;
    }

    @Step("Pobiera komunikat błędu: {errorMessageLogin}")
    public String getErrorMessageLogin() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessageLogin)).getText();
    }

    @Step("Pobiera komunikat błędu: {errorMessagePassword}")
    public String getErrorMessagePassword() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessagePassword)).getText();
    }
}
