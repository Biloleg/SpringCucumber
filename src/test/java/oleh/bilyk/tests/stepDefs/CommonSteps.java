package oleh.bilyk.tests.stepDefs;

import io.cucumber.java.en.Then;
import oleh.bilyk.pages.BasePage;
import oleh.bilyk.pages.PageFactory;
import oleh.bilyk.spring.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 23/05/2020
 * #Comments:
 */
@ContextConfiguration(classes = ApplicationConfig.class)
public class CommonSteps {
    @Autowired
    private PageFactory pageFactory;

    @Then("^I check that \"?([^\"]*)\"? is \"?(invoked|not invoked)\"?$")
    public void iCheckThatLoginPageIsInvoked(String pageName, String expectedState) {
        boolean invokeException = "invoked".equals(expectedState);
        BasePage basePage = pageFactory.getPageByName(pageName);
        Assert.assertEquals(invokeException, basePage.verify(),
                String.format("Page '%s' expect to '%s'", pageName, expectedState));
    }
}
