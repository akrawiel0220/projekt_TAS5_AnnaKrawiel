package app.vercel.demobank.tests;

import app.vercel.demobank.pages.LoginPage;
import app.vercel.demobank.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

@Listeners
public class LoginTest extends DemoBankBaseTest {

    @Test(description = "Skuteczne logowanie użytkownika poprawnymi danymi")
    public void loginValidTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);
        MainPage mainPage = loginPage.loginValid(username, password);
        Assert.assertTrue(mainPage.isDisplayed());
    }

    @Test(description = "Niepoprawne logowanie użytkownika - za krótki login")
    public void invalidLoginWithTooShortUsernameTest() {
        LoginPage loginPage = new LoginPage(driver).open(baseUrl).loginInvalidUsername("Ann");
        Assert.assertTrue(loginPage.getErrorMessageLogin().contains("identyfikator ma min. 8 znaków"));
    }

    @Test(description = "Niepoprawne logowanie użytkownika - za krótkie haslo")
    public void invalidLoginWithTooShortPasswordTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver).open(baseUrl).loginInvalidPassword(username, "pass");
        Assert.assertTrue(loginPage.getErrorMessagePassword().contains("hasło ma min. 8 znaków"));
    }
}
