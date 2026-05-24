package app.vercel.demobank.tests;

import app.vercel.demobank.pages.LoginPage;
import app.vercel.demobank.pages.MainPage;
import app.vercel.demobank.pages.ReportsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReportsTest extends DemoBankBaseTest {

    @Test(description = "Raporty - przesłanie pliku json")
    public void uploadingReportFileValidTest() {
        String fileName = "report.json";
        String successInfoMessage = "Plik przesłany! " + fileName;
        MainPage mainPage = new LoginPage(driver).open(baseUrl).loginValid(username, password);
        ReportsPage reportsPage = mainPage.clickReportTab();
        reportsPage.uploadReportJsonFile("reports/" + fileName);
        Assert.assertEquals(reportsPage.getReportFileTitle(), fileName);
        reportsPage.clickSendButton();
        Assert.assertEquals(reportsPage.checkIfInfoMessageIsDisplayed(), successInfoMessage);
    }
}
