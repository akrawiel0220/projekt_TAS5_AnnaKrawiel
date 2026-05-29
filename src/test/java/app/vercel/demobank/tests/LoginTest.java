package app.vercel.demobank.tests;

import app.vercel.demobank.pages.LoginPage;
import app.vercel.demobank.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends DemoBankBaseTest {

    @Test(description = "TC001 Skuteczne logowanie użytkownika poprawnymi danymi")
    public void TC001loginValidTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);
        MainPage mainPage = loginPage.loginValid(username, password);
        Assert.assertTrue(mainPage.isDisplayed());
    }

    @Test(description = "TC002 Niepoprawne logowanie użytkownika - za krótki login")
    public void TC002invalidLoginWithTooShortUsernameTest() {
        LoginPage loginPage = new LoginPage(driver).open(baseUrl).loginInvalidUsername("Ann");
        Assert.assertTrue(loginPage.getErrorMessageLogin().contains("identyfikator ma min. 8 znaków"));
    }

    @Test(description = "TC003 Niepoprawne logowanie użytkownika - za krótkie haslo")
    public void TC003invalidLoginWithTooShortPasswordTest() {
        LoginPage loginPage = new LoginPage(driver).open(baseUrl).loginInvalidPassword(username, "pass");
        Assert.assertTrue(loginPage.getErrorMessagePassword().contains("hasło ma min. 8 znaków"));
    }
}
