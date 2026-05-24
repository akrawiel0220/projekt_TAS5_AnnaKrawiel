package app.vercel.demobank.tests;

import app.vercel.demobank.pages.LoginPage;
import app.vercel.demobank.pages.MainPage;
import app.vercel.demobank.pages.PhonePage;
import app.vercel.demobank.pages.TopUpPhoneModalPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PhonePageTest extends DemoBankBaseTest {

    @Test(description = "Doładowanie telefonu")
    public void phoneTopUpValidTest() {

        String receiverNumberExpected = "502 xxx xxx";
        String amountTopUpToSend = "50,00";
        String successMessageAfterTopUp = "Doładowanie wykonane! " + amountTopUpToSend + "PLN na numer " + receiverNumberExpected;

        MainPage mainPage = new LoginPage(driver).open(baseUrl).loginValid(username, password);
        PhonePage phonePage = mainPage.clickPhoneTopUpTab();
        Assert.assertEquals(phonePage.selectReceiverNumber(), receiverNumberExpected);
        Assert.assertEquals(phonePage.setTopUpAmount(amountTopUpToSend), amountTopUpToSend);
        WebElement selectCheckboxOfRegulations = phonePage.clickCheckboxAboutRegulationsAndAgreement();
        Assert.assertTrue(selectCheckboxOfRegulations.isSelected(), "Checkbox nie jest zaznaczony!");
        TopUpPhoneModalPage topUpPhoneModalPage = phonePage.clickBtnPhoneTopUp();

        String textsFromTransferModalWindow = topUpPhoneModalPage.getAllSummaryText();
        String expectedTextsTransferModalWindow = "Doładowanie wykonane!\nKwota: 50,00PLN\nNumer: 502 xxx xxx";
        Assert.assertEquals(textsFromTransferModalWindow, expectedTextsTransferModalWindow);
        topUpPhoneModalPage.clickOkButton();
        Assert.assertFalse(topUpPhoneModalPage.checkIfModalWindowDisplayed());
        Assert.assertEquals(mainPage.getInfoTextAboutSuccessTransfer(), successMessageAfterTopUp);
    }
}
