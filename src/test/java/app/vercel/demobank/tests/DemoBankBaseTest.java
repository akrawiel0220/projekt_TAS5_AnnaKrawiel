package app.vercel.demobank.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Core;

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
