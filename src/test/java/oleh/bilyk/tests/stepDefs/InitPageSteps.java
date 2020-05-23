package oleh.bilyk.tests.stepDefs;

import io.cucumber.java.en.When;
import oleh.bilyk.pages.InitPage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 23/05/2020
 * #Comments:
 */
public class InitPageSteps {
    @Autowired
    private InitPage initPage;

    @When("^I open browser and navigate by configured URL$")
    public void iOpenBrowserAndNavigateByURL() {
        initPage.invoke();
    }

    @When("^I navigate to Login page$")
    public void iNavigateToLoginPage() {
        initPage.navigateToLoginPage();
    }
}
