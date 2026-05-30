package app.vercel.demobank.tests;

import app.vercel.demobank.pages.LoginPage;
import app.vercel.demobank.pages.MainPage;
import app.vercel.demobank.pages.PhonePage;
import app.vercel.demobank.pages.TopUpPhoneModalPage;
import com.opencsv.CSVReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileReader;
import java.nio.file.Path;

public class PhonePageTest extends DemoBankBaseTest {

    @Test(description = "TC006 Poprawne doładowanie telefonu")
    public void TC006phoneTopUpValidTest() {
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

    @Test(description = "TC007 Walidacja pola kwoty w doładowaniu telefonu (pętla CSV)")

    public void TC007csvAmountValidationLoop() throws Exception {
        //na stronie jest błąd w komunikacie błędu "kwota musi być mniejsza od 50", ale zakładam, że kwota musi być mniejsza lub równa 50.00

        Path csvPath = Path.of("src/test/resources/phoneTopUp/amountValidation.csv");
        try (CSVReader csvReader = new CSVReader(new FileReader(csvPath.toFile()))) {
            String[] line;
            boolean skipHeader = true;
            while ((line = csvReader.readNext()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                String amount = line[0];
                boolean shouldShowError = Boolean.parseBoolean(line[1]);
                String expectedMessage = line[2];

                MainPage mainPage = new LoginPage(driver).open(baseUrl).loginValid(username, password);
                PhonePage phonePage = mainPage.clickPhoneTopUpTab();
                phonePage.setTopUpAmount(amount);

                if (shouldShowError) {
                    Assert.assertTrue(phonePage.isErrorDisplayed(), "Błąd powinien być widoczny dla kwoty: " + amount);
                    Assert.assertEquals(phonePage.getErrorMessageText(), expectedMessage,
                            "Treść komunikatu o błędzie jest niepoprawna dla kwoty: " + amount);
                } else {
                    Assert.assertFalse(phonePage.isErrorDisplayed(),
                            "Błąd nie powinien się pojawić dla poprawnej kwoty: " + amount);
                }
            }

        }
    }
}
