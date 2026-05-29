package app.vercel.demobank.tests;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.Core;

import java.io.ByteArrayInputStream;

public class DemoBankBaseTest extends Core {

    protected String baseUrl;
    protected String username;
    protected String password;

    @BeforeMethod
    public void setUp() {
        this.baseUrl = "https://demo-bank.vercel.app/";
        this.username = "AniaUser";
        this.password = "BasePassword1!";
        driver = createDriver("chrome");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        quitDriver();
    }
}
