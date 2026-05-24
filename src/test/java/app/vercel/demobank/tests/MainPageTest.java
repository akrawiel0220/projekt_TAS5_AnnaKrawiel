package app.vercel.demobank.tests;

import app.vercel.demobank.pages.LoginPage;
import app.vercel.demobank.pages.MainPage;
import app.vercel.demobank.pages.TransferModalPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners
public class MainPageTest extends DemoBankBaseTest {

    @Test(description = "Użytkownik wykonuje szybki przelew z zakładki 'mój pulpit'")
    public void fastBankTransferValidTest() {
        String expectedReceiver = "Chuck Demobankowy";
        String amountToSend = "120,00";
        String titleTransferText = "Zwykły przelew";
        String successMessageAfterTransfer = "Przelew wykonany! " + expectedReceiver + " - " + amountToSend + "PLN - " + titleTransferText;

        MainPage mainPage = new LoginPage(driver).open(baseUrl).loginValid(username, password);
        mainPage.selectReceiverName();
        Assert.assertEquals(mainPage.selectReceiverName(), expectedReceiver);
        mainPage.setTransferAmount(amountToSend);
        Assert.assertEquals(mainPage.getTransferAmount(), amountToSend);
        String actualTransferText = mainPage.setAndGetTransferTitle(titleTransferText);
        Assert.assertEquals(actualTransferText, titleTransferText);

        TransferModalPage transferModalPage = mainPage.clickExecuteButton();
        String textsFromTransferModalWindow = transferModalPage.getAllSummaryTexts();
        String expectedTextsTransferModalWindow = "Przelew wykonany!\n\nOdbiorca: " + expectedReceiver + "\nKwota: " + amountToSend + "PLN\nNazwa: " + titleTransferText;
        Assert.assertEquals(textsFromTransferModalWindow, expectedTextsTransferModalWindow);
        transferModalPage.clickOkButton();
        Assert.assertFalse(transferModalPage.checkIfModalWindowIsDisplayed());
        Assert.assertEquals(mainPage.getInfoTextAboutSuccessTransfer(), successMessageAfterTransfer);
    }

    @Test(description = "Użytkownik wykonuje szybki przelew z zakładki 'mój pulpit, nie wpisując danych - test negatywny'")
    public void fastBankTransferWithoutSetDataInValidTest() {
        MainPage mainPage = new LoginPage(driver).open(baseUrl).loginValid(username, password);
        mainPage.clickExecuteButton();
        Assert.assertTrue(mainPage.checkIfErrorMessagesVisible(5));
    }
}
