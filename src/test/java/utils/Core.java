package utils;

import org.openqa.selenium.WebDriver;

public class Core {

    protected WebDriver driver;

    protected WebDriver createDriver(String browser) {
        driver = DriverFactory.createDriver(browser);
        return driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    protected void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
