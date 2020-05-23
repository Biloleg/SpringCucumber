package oleh.bilyk.tests.stepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import oleh.bilyk.pages.MainPage;
import org.openqa.selenium.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 23/05/2020
 * #Comments:
 */
public class MainPageSteps {
    @Autowired
    private MainPage mainPage;

    @When("^I type \"?([^\"]*)\"? to Search field on Main page and confirm$")
    public void iTypeToSearchFieldOnMainPageAndConfirm(String text) {
        mainPage.getFieldSearch().setText(text);
        mainPage.getFieldSearch().setText(Keys.ENTER);
    }

    @Then("^I check that 'Not Found' message is \"?(displayed|not displayed)\"? on Main page$")
    public void iCheckThatNotFoundMessageIsOnMainPage(String state) {
        boolean expectedState = "displayed".equalsIgnoreCase(state);
        Assert.assertEquals(expectedState, mainPage.getLabelNotFound().isDisplayed(1000),
                String.format("Not Found message expected to be '%s'", state));
    }

    @Then("^I check that 'Not Found' message has text:$")
    public void iCheckThatNotFoundMessageHasText(String message) {
        String actualText = mainPage.getLabelNotFound().getText();
        Assert.assertEquals(message, actualText,
                String.format("Actual error text is '%s' but expected '%s'", actualText, message));
    }

    @Then("^I check that repo with link \"?([^\"]*)\"? is \"?(present|not present)\"? in repo list on Main page$")
    public void iCheckThatRepoWithLinkIsInRepoListOnMainPage(String linkText, String state) {
        boolean expectedState = "present".equalsIgnoreCase(state);
        Assert.assertEquals(expectedState, mainPage.isRepoListItemWithLinkTextExist(linkText),
                String.format("Repository Item with link text '%s' is not present in repo list", linkText));
    }

    @Then("^I check that repo with link \"?([^\"]*)\"? has \"?([^\"]*)\"? program language in repo list on Main page$")
    public void iCheckThatRepoWithLinkHasProgramLanguageInRepoListOnMainPage(String linkText, String language) {
        String actualLang = mainPage.getRepoListItemByLinkText(linkText).getElementRepoProgramLanguage().getText();
        Assert.assertEquals(language, actualLang,
                String.format("Expected Repository Item language is '%s' but found '%s'", language, actualLang));
    }
}
