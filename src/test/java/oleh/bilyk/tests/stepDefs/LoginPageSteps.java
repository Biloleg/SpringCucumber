package oleh.bilyk.tests.stepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import oleh.bilyk.pages.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 23/05/2020
 * #Comments:
 */
public class LoginPageSteps {
    @Autowired
    private LoginPage loginPage;

    @When("^I type \"?([^\"]*)\"? to \"?(Username|Password)\"? field on Login page$")
    public void iTypeToUsernameFieldOnLoginPage(String text, String fieldName) {
        loginPage.getFieldByName(fieldName).setText(text);
    }

    @When("^I click 'Sign In' button on Login page$")
    public void iClickButtonOnLoginPage() {
        loginPage.getButtonSignIn().click();
    }

    @Then("^I check that Error Login message is \"?(displayed|not displayed)\"? on Login page$")
    public void iCheckThatErrorLoginMessageIsOnLoginPage(String state) {
        boolean expectedState = "displayed".equalsIgnoreCase(state);
        Assert.assertEquals(expectedState, loginPage.getLabelErrorMessage().isDisplayed(1000),
                String.format("Error Login message expected to be '%s'", state));
    }

    @Then("^I check that Error login message has text:$")
    public void iCheckThatErrorLoginMessageHasText(String expectedText) {
        String actualText = loginPage.getLabelErrorMessage().getText();
        Assert.assertEquals(expectedText, actualText,
                String.format("Actual error text is '%s' but expected '%s'", actualText, expectedText));
    }
}
