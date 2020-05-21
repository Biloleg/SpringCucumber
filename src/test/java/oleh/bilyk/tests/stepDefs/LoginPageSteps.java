package oleh.bilyk.tests.stepDefs;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import oleh.bilyk.helpers.EnumHelper;
import oleh.bilyk.pages.InitPage;
import oleh.bilyk.pages.LoginPage;
import oleh.bilyk.spring.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

public class LoginPageSteps {
    @Autowired
    private InitPage initPage;
    @Autowired
    private LoginPage loginPage;

    @When("^I type \"?([^\"]*)\"? to \"?(Username|Password)\"? field on Login page$")
    public void iTypeToUsernameFieldOnLoginPage(String text, String elementName) {
        loginPage.getElement(elementName).sendKeys(text);
    }

    @When("^I click \"?([^\"]*)\"? button on Login page$")
    public void iClickButtonOnLoginPage(String elementName) {
        loginPage.getElement(elementName).click();
    }

    @Then("^I check that Error Login message is \"?(displayed|not displayed)\"? on Login page$")
    public void iCheckThatErrorLoginMessageIsOnLoginPage(String state) {
        boolean expectedState="displayed".equalsIgnoreCase(state);
        Assert.assertEquals(expectedState, loginPage.isLoginErrorMessageDisplayed(),
                String.format("Error Login message expected to be '%s'", state));
    }
}
